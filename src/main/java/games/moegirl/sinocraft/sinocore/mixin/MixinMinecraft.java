package games.moegirl.sinocraft.sinocore.mixin;

import games.moegirl.sinocraft.sinocore.api.SinoCoreAPI;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {

    /**
     * Add ": Mixin is loaded" when debugging, ensure mixin is loading
     */
    @Inject(method = "createTitle", at = @At("RETURN"), cancellable = true)
    private void injectCreateTitle(CallbackInfoReturnable<String> cir) {
        if (SinoCoreAPI.DEBUG_MODE) {
            cir.setReturnValue(cir.getReturnValue() + ": Mixin is loaded");
        }
    }
}
