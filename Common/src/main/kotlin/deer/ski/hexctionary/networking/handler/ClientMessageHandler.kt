package deer.ski.hexctionary.networking.handler

import dev.architectury.networking.NetworkManager.PacketContext
import deer.ski.hexctionary.config.HexctionaryConfig
import deer.ski.hexctionary.networking.msg.*

fun HexctionaryMessageS2C.applyOnClient(ctx: PacketContext) = ctx.queue {
    when (this) {
        is MsgSyncConfigS2C -> {
            HexctionaryConfig.onSyncConfig(serverConfig)
        }

        // add more client-side message handlers here
    }
}
