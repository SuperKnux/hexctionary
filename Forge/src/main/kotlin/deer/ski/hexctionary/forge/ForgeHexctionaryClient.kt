package deer.ski.hexctionary.forge

import deer.ski.hexctionary.HexctionaryClient
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import thedarkcolour.kotlinforforge.forge.LOADING_CONTEXT

object ForgeHexctionaryClient {
    fun init(event: FMLClientSetupEvent) {
        HexctionaryClient.init()
        LOADING_CONTEXT.registerExtensionPoint(ConfigScreenFactory::class.java) {
            ConfigScreenFactory { _, parent -> HexctionaryClient.getConfigScreen(parent) }
        }
    }
}
