package deer.ski.hexctionary.fabric

import deer.ski.hexctionary.HexctionaryClient
import net.fabricmc.api.ClientModInitializer

object FabricHexctionaryClient : ClientModInitializer {
    override fun onInitializeClient() {
        HexctionaryClient.init()
    }
}
