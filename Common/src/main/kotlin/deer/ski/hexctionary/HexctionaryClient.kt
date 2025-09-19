package deer.ski.hexctionary

import deer.ski.hexctionary.config.HexctionaryConfig
import deer.ski.hexctionary.config.HexctionaryConfig.GlobalConfig
import deer.ski.hexctionary.interop.inline.InlineHexctionaryClient
import me.shedaniel.autoconfig.AutoConfig
import net.minecraft.client.gui.screens.Screen

object HexctionaryClient {
    fun init() {
        HexctionaryConfig.initClient()
        InlineHexctionaryClient.initClient()
    }

    fun getConfigScreen(parent: Screen): Screen {
        return AutoConfig.getConfigScreen(GlobalConfig::class.java, parent).get()
    }
}
