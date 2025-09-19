package deer.ski.hexctionary.casting.actions.recipes

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import deer.ski.hexctionary.getPlatformIota

object OpRecipeIdentify : ConstMediaAction {
    override val argc = 1
    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val iotaToProcess = getPlatformIota<Iota>(args[0])
        TODO("Not done yet")
    }
}