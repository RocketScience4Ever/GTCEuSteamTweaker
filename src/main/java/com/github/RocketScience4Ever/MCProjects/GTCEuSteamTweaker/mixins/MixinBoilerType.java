package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import gregtech.common.metatileentities.multi.BoilerType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;

/**Mixin to {@link BoilerType} to tweak the base steam output of GTCEu multiblock boilers.
 */
@Mixin(BoilerType.class)
public abstract class MixinBoilerType {
    /**Short circuits {@link BoilerType#steamPerTick()} to tweak the base steam output of the GTCEu multiblock boilers using the values from the config of GTCEU Steam Tweaker.
     * <p>This injection will do nothing if {@code tweakMultiblockBoilerSteam} is set to {@code false} in the mod's config file.</p>
     * @param cir (CallbackInfoReturnable&lt;Integer&gt;) Allows this method to short circuit the steamPerTick in the {@link BoilerType} enum and hijack the return value with our own logic
     */
    @Inject(method = "steamPerTick", at = @At("HEAD"), cancellable = true)
    private void onSteamPerTick(CallbackInfoReturnable<Integer> cir) {
        if (GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerSteamConfig.tweakMultiblockBoilerSteam) {
            switch ((BoilerType)(Object)this) {
                case BRONZE -> cir.setReturnValue(GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerSteamConfig.bronzeMultiBoilerSteam);
                case STEEL -> cir.setReturnValue(GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerSteamConfig.steelMultiBoilerSteam);
                case TITANIUM -> cir.setReturnValue(GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerSteamConfig.titaniumMultiBoilerSteam);
                case TUNGSTENSTEEL -> cir.setReturnValue(GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerSteamConfig.tungstensteelMultiBoilerSteam);
            }
        }
    }
}