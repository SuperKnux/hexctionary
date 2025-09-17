package deer.ski.hexctionary.networking

import dev.architectury.networking.NetworkChannel
import deer.ski.hexctionary.Hexctionary
import deer.ski.hexctionary.networking.msg.HexctionaryMessageCompanion

object HexctionaryNetworking {
    val CHANNEL: NetworkChannel = NetworkChannel.create(Hexctionary.id("networking_channel"))

    fun init() {
        for (subclass in HexctionaryMessageCompanion::class.sealedSubclasses) {
            subclass.objectInstance?.register(CHANNEL)
        }
    }
}
