package deer.ski.hexctionary.forge.mixin;

import deer.ski.hexctionary.Hexctionary;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

// disable MixinDatagenMain if we're not running the datagen task, since it's not necessary at any other time
public class ForgeMixinConfigPlugin implements IMixinConfigPlugin {
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.equals("deer.ski.hexctionary.forge.mixin.MixinDatagenMain")) {
            var shouldApply = System.getProperty("hexctionary.apply-datagen-mixin", "false").equals("true");
            if (shouldApply) {
                Hexctionary.LOGGER.warn("Applying scuffed datagen mixin. This should not happen if not running datagen!");
            }
            return shouldApply;
        }
        return true;
    }

    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() { return null; }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() { return null; }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
