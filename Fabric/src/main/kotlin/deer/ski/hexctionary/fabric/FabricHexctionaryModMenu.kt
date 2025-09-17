package deer.ski.hexctionary.fabric

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import deer.ski.hexctionary.HexctionaryClient

object FabricHexctionaryModMenu : ModMenuApi {
    override fun getModConfigScreenFactory() = ConfigScreenFactory(HexctionaryClient::getConfigScreen)
}
