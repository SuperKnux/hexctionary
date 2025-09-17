@file:JvmName("HexctionaryAbstractionsImpl")

package deer.ski.hexctionary.forge

import deer.ski.hexctionary.registry.HexctionaryRegistrar
import net.minecraftforge.registries.RegisterEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

fun <T : Any> initRegistry(registrar: HexctionaryRegistrar<T>) {
    MOD_BUS.addListener { event: RegisterEvent ->
        event.register(registrar.registryKey) { helper ->
            registrar.init(helper::register)
        }
    }
}
