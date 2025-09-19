package deer.ski.hexctionary.api.casting.iota

import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.utils.*
import deer.ski.hexctionary.interop.inline.InlineEmiRecipeData
import dev.emi.emi.api.EmiApi
import dev.emi.emi.api.recipe.EmiRecipe
import net.minecraft.client.Minecraft
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.Container
import net.minecraft.world.item.crafting.CraftingRecipe
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeType

class RecipeIota(recipe: Recipe<*>) : Iota(TYPE, recipe) {
    val recipe get() = payload as Recipe<*>
    override fun isTruthy() = true

    override fun toleratesOther(that: Iota): Boolean {
        return typesMatch(this, that)
            && that is RecipeIota
            && this.recipe.id.equals(that.recipe.id)
    }

    override fun serialize(): Tag {
        val out = CompoundTag()
        out.putString("id", this.recipe.id.toString())
        return out
    }

    companion object {
        var TYPE: IotaType<RecipeIota> = object : IotaType<RecipeIota>() {
            override fun deserialize(
                tag: Tag,
                world: ServerLevel
            ): RecipeIota {
                val recTag = tag.downcast(CompoundTag.TYPE)
                val recId = ResourceLocation(recTag.getString("id").substringBefore(":"), recTag.getString("id").substringAfter(":"))
                var recipe = world.recipeManager.byKey(recId)
                return RecipeIota(recipe.get())
            }

            fun display(recipeId: ResourceLocation): Component {
                val text = InlineEmiRecipeData(EmiApi.getRecipeManager().getRecipe(recipeId) as EmiRecipe).asText(true)
                return text.copy()
            }

            override fun display(tag: Tag): Component {
                val recTag = tag.downcast(CompoundTag.TYPE)
                val recId = ResourceLocation(recTag.getString("id").substringBefore(":"), recTag.getString("id").substringAfter(":"))
                return display(recId)
            }

            override fun color(): Int {
                return 0x42f578
            }
        }
    }


}