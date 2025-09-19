package deer.ski.hexctionary.interop.inline

import com.mojang.authlib.minecraft.client.MinecraftClient
import com.samsthenerd.inline.api.client.InlineRenderer
import com.samsthenerd.inline.api.matching.InlineMatch
import com.samsthenerd.inline.api.matching.MatchContext
import com.samsthenerd.inline.api.matching.MatcherInfo
import com.samsthenerd.inline.api.matching.RegexMatcher
import deer.ski.hexctionary.Hexctionary
import dev.emi.emi.EmiRenderHelper
import dev.emi.emi.api.stack.EmiIngredient
import dev.emi.emi.runtime.EmiDrawContext
import dev.emi.emi.screen.tooltip.EmiTooltipComponent
import dev.emi.emi.screen.tooltip.RecipeTooltipComponent
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.network.chat.Style
import net.minecraft.resources.ResourceLocation
import org.joml.Vector2f
import java.util.regex.MatchResult
import java.util.regex.Pattern

object InlineEmiRecipeRenderer : InlineRenderer<InlineEmiRecipeData> {

    val INSTANCE = InlineEmiRecipeRenderer
    override fun getId(): ResourceLocation {
       return Hexctionary.id("recipe")
    }

    override fun render(
        data: InlineEmiRecipeData,
        context: GuiGraphics,
        index: Int,
        style: Style,
        codepoint: Int,
        trContext: InlineRenderer.TextRenderingContext
    ): Int {
        val tooltipToRender = RecipeTooltipComponent(data.recipeData)
        val client = Minecraft.getInstance()
        val font = client.font
        val itemRenderer = client.itemRenderer
        val gui = client.gui.chat

        val widthScale = (client.gui.font.lineHeight) / tooltipToRender.height.toFloat()
        val heightScale = (client.gui.font.lineHeight) / tooltipToRender.height.toFloat()


        val matrices = context.pose()
        matrices.pushPose()
        matrices.scale(widthScale, heightScale, 1F)
        tooltipToRender.drawTooltip(EmiDrawContext.wrap(context), EmiTooltipComponent.TooltipRenderData(font, itemRenderer, 0, 0))
        matrices.popPose()
        return 8
    }

    override fun charWidth(
        data: InlineEmiRecipeData,
        style: Style,
        codepoint: Int
    ): Int {
        return 8
    }
}