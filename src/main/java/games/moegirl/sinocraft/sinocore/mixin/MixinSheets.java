package games.moegirl.sinocraft.sinocore.mixin;

import games.moegirl.sinocraft.sinocore.api.tree.Tree;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.client.renderer.Sheets.SIGN_SHEET;

@Mixin(Sheets.class)
public abstract class MixinSheets {

    /**
     * Inject in createSignMaterial, set custom tree material if tree existed
     */
    @Inject(method = "createSignMaterial", at = @At("HEAD"), cancellable = true)
    private static void injectCreateSignMaterial(WoodType woodType, CallbackInfoReturnable<Material> cir) {
        Tree tree = Tree.get(woodType);
        if (tree != null) {
            Material material = new Material(SIGN_SHEET,
                    new ResourceLocation(tree.name.getNamespace(), "entity/signs/" + tree.name.getPath()));
            cir.setReturnValue(material);
            cir.cancel();
        }
    }
}
