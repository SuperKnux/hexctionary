package deer.ski.hexctionary.interop.inline

import com.samsthenerd.inline.api.InlineAPI

object InlineHexctionary {
    fun init() {
        InlineAPI.INSTANCE.addDataType(InlineEmiRecipeData.InlineEmiRecipeDataType)
    }
}