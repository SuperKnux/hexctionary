package deer.ski.hexctionary.networking.msg

import deer.ski.hexctionary.config.HexctionaryConfig
import net.minecraft.network.FriendlyByteBuf

data class MsgSyncConfigS2C(val serverConfig: HexctionaryConfig.ServerConfig) : HexctionaryMessageS2C {
    companion object : HexctionaryMessageCompanion<MsgSyncConfigS2C> {
        override val type = MsgSyncConfigS2C::class.java

        override fun decode(buf: FriendlyByteBuf) = MsgSyncConfigS2C(
            serverConfig = HexctionaryConfig.ServerConfig.decode(buf),
        )

        override fun MsgSyncConfigS2C.encode(buf: FriendlyByteBuf) {
            serverConfig.encode(buf)
        }
    }
}
