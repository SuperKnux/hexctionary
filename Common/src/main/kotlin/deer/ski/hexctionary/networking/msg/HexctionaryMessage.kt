package deer.ski.hexctionary.networking.msg

import dev.architectury.networking.NetworkChannel
import dev.architectury.networking.NetworkManager.PacketContext
import deer.ski.hexctionary.Hexctionary
import deer.ski.hexctionary.networking.HexctionaryNetworking
import deer.ski.hexctionary.networking.handler.applyOnClient
import deer.ski.hexctionary.networking.handler.applyOnServer
import net.fabricmc.api.EnvType
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerPlayer
import java.util.function.Supplier

sealed interface HexctionaryMessage

sealed interface HexctionaryMessageC2S : HexctionaryMessage {
    fun sendToServer() {
        HexctionaryNetworking.CHANNEL.sendToServer(this)
    }
}

sealed interface HexctionaryMessageS2C : HexctionaryMessage {
    fun sendToPlayer(player: ServerPlayer) {
        HexctionaryNetworking.CHANNEL.sendToPlayer(player, this)
    }

    fun sendToPlayers(players: Iterable<ServerPlayer>) {
        HexctionaryNetworking.CHANNEL.sendToPlayers(players, this)
    }
}

sealed interface HexctionaryMessageCompanion<T : HexctionaryMessage> {
    val type: Class<T>

    fun decode(buf: FriendlyByteBuf): T

    fun T.encode(buf: FriendlyByteBuf)

    fun apply(msg: T, supplier: Supplier<PacketContext>) {
        val ctx = supplier.get()
        when (ctx.env) {
            EnvType.SERVER, null -> {
                Hexctionary.LOGGER.debug("Server received packet from {}: {}", ctx.player.name.string, this)
                when (msg) {
                    is HexctionaryMessageC2S -> msg.applyOnServer(ctx)
                    else -> Hexctionary.LOGGER.warn("Message not handled on server: {}", msg::class)
                }
            }
            EnvType.CLIENT -> {
                Hexctionary.LOGGER.debug("Client received packet: {}", this)
                when (msg) {
                    is HexctionaryMessageS2C -> msg.applyOnClient(ctx)
                    else -> Hexctionary.LOGGER.warn("Message not handled on client: {}", msg::class)
                }
            }
        }
    }

    fun register(channel: NetworkChannel) {
        channel.register(type, { msg, buf -> msg.encode(buf) }, ::decode, ::apply)
    }
}
