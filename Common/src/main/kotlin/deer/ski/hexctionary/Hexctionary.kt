package deer.ski.hexctionary

import net.minecraft.resources.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import deer.ski.hexctionary.config.HexctionaryConfig
import deer.ski.hexctionary.networking.HexctionaryNetworking
import deer.ski.hexctionary.registry.HexctionaryActions

object Hexctionary {
    const val MODID = "hexctionary"

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MODID)

    @JvmStatic
    fun id(path: String) = ResourceLocation(MODID, path)

    fun init() {
        HexctionaryConfig.init()
        initRegistries(
            HexctionaryActions,
        )
        HexctionaryNetworking.init()
    }
}
