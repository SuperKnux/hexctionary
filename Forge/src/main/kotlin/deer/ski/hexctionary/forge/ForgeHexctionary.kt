package deer.ski.hexctionary.forge

import dev.architectury.platform.forge.EventBuses
import deer.ski.hexctionary.Hexctionary
import net.minecraft.data.DataProvider
import net.minecraft.data.DataProvider.Factory
import net.minecraft.data.PackOutput
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(Hexctionary.MODID)
class HexctionaryForge {
    init {
        MOD_BUS.apply {
            EventBuses.registerModEventBus(Hexctionary.MODID, this)
            addListener(ForgeHexctionaryClient::init)
            addListener(::gatherData)
        }
        Hexctionary.init()
    }

    private fun gatherData(event: GatherDataEvent) {
        event.apply {
            // TODO: add datagen providers here
        }
    }
}

fun <T : DataProvider> GatherDataEvent.addProvider(run: Boolean, factory: (PackOutput) -> T) =
    generator.addProvider(run, Factory { factory(it) })
