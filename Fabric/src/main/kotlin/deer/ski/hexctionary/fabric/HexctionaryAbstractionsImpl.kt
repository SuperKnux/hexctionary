@file:JvmName("HexctionaryAbstractionsImpl")

package deer.ski.hexctionary.fabric

import at.petrak.hexcasting.api.casting.iota.Iota
import deer.ski.hexctionary.registry.HexctionaryRegistrar
import net.minecraft.core.Registry

fun <T : Any> initRegistry(registrar: HexctionaryRegistrar<T>) {
    val registry = registrar.registry
    registrar.init { id, value -> Registry.register(registry, id, value) }
}

fun <T : Iota> getPlatformIota(iota: Iota) {
    TODO("dumb shit")
}
