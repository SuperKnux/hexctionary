package deer.ski.hexctionary.interop.inline

import com.samsthenerd.inline.api.client.InlineClientAPI
import com.samsthenerd.inline.api.matching.InlineMatch
import com.samsthenerd.inline.api.matching.MatcherInfo
import com.samsthenerd.inline.api.matching.RegexMatcher.Standard
import deer.ski.hexctionary.Hexctionary
import dev.emi.emi.api.EmiApi
import dev.emi.emi.api.recipe.EmiRecipe
import net.minecraft.network.chat.Style
import net.minecraft.resources.ResourceLocation


object InlineHexctionaryClient {
    fun initClient() {
        InlineClientAPI.INSTANCE.addRenderer(InlineEmiRecipeRenderer)
        val emiRecipeMatcherID = Hexctionary.id("recipe")
        InlineClientAPI.INSTANCE.addMatcher(com.samsthenerd.inline.api.matching.RegexMatcher.Standard("recipe", Standard.IDENTIFIER_REGEX_INSENSITIVE, emiRecipeMatcherID,
            { recipeId: String ->
                val namespace = recipeId.substringBefore(":"); val path = recipeId.substringAfter(":")
                val recipe = EmiApi.getRecipeManager().getRecipe(ResourceLocation(namespace, path)) ?: return@Standard null

                InlineMatch.DataMatch(InlineEmiRecipeData(recipe), Style.EMPTY)
            }, MatcherInfo.fromId(emiRecipeMatcherID)))

    }
}