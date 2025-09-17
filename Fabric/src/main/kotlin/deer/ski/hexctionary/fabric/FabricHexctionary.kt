package deer.ski.hexctionary.fabric

import deer.ski.hexctionary.Hexctionary
import net.fabricmc.api.ModInitializer

object FabricHexctionary : ModInitializer {
    override fun onInitialize() {
        Hexctionary.init()
    }
}
