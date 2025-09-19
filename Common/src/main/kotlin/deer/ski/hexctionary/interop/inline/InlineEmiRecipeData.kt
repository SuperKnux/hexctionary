package deer.ski.hexctionary.interop.inline

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.samsthenerd.inline.Inline
import com.samsthenerd.inline.api.InlineData
import deer.ski.hexctionary.Hexctionary
import deer.ski.hexctionary.Hexctionary.MODID
import dev.emi.emi.api.EmiApi
import dev.emi.emi.api.recipe.BasicEmiRecipe
import dev.emi.emi.api.recipe.EmiRecipe
import dev.emi.emi.api.recipe.EmiRecipeManager
import net.minecraft.network.chat.Style
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeSerializer
import org.intellij.lang.annotations.Identifier

class InlineEmiRecipeData(recipe: EmiRecipe) : InlineData<InlineEmiRecipeData> {
    val recipeData = recipe

    override fun getType(): InlineData.InlineDataType<InlineEmiRecipeData> {
        return InlineEmiRecipeDataType
    }

    override fun getRendererId(): ResourceLocation {
        return Hexctionary.id("recipe")
    }

    override fun getExtraStyle(): Style {
        return Style.EMPTY
    }

    object InlineEmiRecipeDataType : InlineData.InlineDataType<InlineEmiRecipeData> {
        private val ID = ResourceLocation(MODID, "recipe")
        override fun getId(): ResourceLocation {
            return ID
        }

        override fun getCodec(): Codec<InlineEmiRecipeData> {
            val codec: Codec<EmiRecipe> = RecordCodecBuilder.create { instance ->
                instance.group(
                    ResourceLocation.CODEC.fieldOf("recipe").forGetter(EmiRecipe::getId)
                ).apply(instance, EmiApi.getRecipeManager()::getRecipe)

            }

            return codec.xmap(::InlineEmiRecipeData) { data -> data.recipeData }
        }
    }
}