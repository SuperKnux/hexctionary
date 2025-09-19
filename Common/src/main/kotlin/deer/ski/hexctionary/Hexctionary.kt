package deer.ski.hexctionary

import at.petrak.hexcasting.common.lib.hex.HexIotaTypes
import deer.ski.hexctionary.api.casting.iota.RecipeIota
import net.minecraft.resources.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import deer.ski.hexctionary.config.HexctionaryConfig
import deer.ski.hexctionary.interop.inline.InlineHexctionary
import deer.ski.hexctionary.networking.HexctionaryNetworking
import deer.ski.hexctionary.registry.HexctionaryActions
import deer.ski.hexctionary.registry.HexctionaryIotas
import net.minecraft.core.Registry

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
            HexctionaryIotas,
        )
        HexctionaryNetworking.init()
        InlineHexctionary.init()
    }
}
