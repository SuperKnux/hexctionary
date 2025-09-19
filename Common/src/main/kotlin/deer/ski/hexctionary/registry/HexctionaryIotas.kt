package deer.ski.hexctionary.registry

import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.common.lib.HexRegistries
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes
import deer.ski.hexctionary.api.casting.iota.RecipeIota


object HexctionaryIotas : HexctionaryRegistrar<IotaType<*>>(
    HexRegistries.IOTA_TYPE,
    { HexIotaTypes.REGISTRY }
) {
    val RECIPE = register("recipe") { RecipeIota.TYPE }
}