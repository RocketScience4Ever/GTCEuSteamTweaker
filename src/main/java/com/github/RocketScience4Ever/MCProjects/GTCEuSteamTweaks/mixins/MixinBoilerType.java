package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaks.mixins;

import gregtech.common.metatileentities.multi.BoilerType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaks.config.GTCEuSteamTweakerConfig;

@Mixin(BoilerType.class)
public abstract class MixinBoilerType {
    /**Overrides the base steam output of the GTCEu multiblock boilers with the values from the config of GTCEU Steam Tweaker.
     * This injection will do nothing if {@code tweakMultiblockBoilerSteam} is set to {@code false} in the mod's config file.
     * @param cir (CallbackInfoReturnable&lt;Integer&gt;) Allows this method to short circuit the steamPerTick in the {@link BoilerType} enum and hijack the return value with our own logic
     */
    @Inject(method = "steamPerTick", at = @At("HEAD"), cancellable = true)
    private void onSteamPerTick(CallbackInfoReturnable<Integer> cir) {
        if (GTCEuSteamTweakerConfig.multiblockBoilerOutputConfig.tweakMultiblockBoilerSteam) {
            switch ((BoilerType)(Object)this) {
                case BRONZE -> cir.setReturnValue(GTCEuSteamTweakerConfig.multiblockBoilerOutputConfig.bronzeMultiBoilerSteam);
                case STEEL -> cir.setReturnValue(GTCEuSteamTweakerConfig.multiblockBoilerOutputConfig.steelMultiBoilerSteam);
                case TITANIUM -> cir.setReturnValue(GTCEuSteamTweakerConfig.multiblockBoilerOutputConfig.titaniumMultiBoilerSteam);
                case TUNGSTENSTEEL -> cir.setReturnValue(GTCEuSteamTweakerConfig.multiblockBoilerOutputConfig.tungstensteelMultiBoilerSteam);
                default -> cir.setReturnValue(0);
            }
        }
    }
}