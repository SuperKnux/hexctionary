@file:JvmName("HexctionaryAbstractions")

package deer.ski.hexctionary

import dev.architectury.injectables.annotations.ExpectPlatform
import deer.ski.hexctionary.registry.HexctionaryRegistrar

fun initRegistries(vararg registries: HexctionaryRegistrar<*>) {
    for (registry in registries) {
        initRegistry(registry)
    }
}

@ExpectPlatform
fun <T : Any> initRegistry(registrar: HexctionaryRegistrar<T>) {
    throw AssertionError()
}
