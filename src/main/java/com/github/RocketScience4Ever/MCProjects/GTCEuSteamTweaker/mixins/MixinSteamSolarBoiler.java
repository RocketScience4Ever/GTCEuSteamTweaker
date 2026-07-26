package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;

import gregtech.common.metatileentities.steam.boiler.SteamBoiler;
import gregtech.common.metatileentities.steam.boiler.SteamSolarBoiler;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**Mixin to {@link SteamSolarBoiler} to tweak the base steam output of the single block solar boilers.
 */
@Mixin(SteamSolarBoiler.class)
public abstract class MixinSteamSolarBoiler extends SteamBoiler {
    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinSteamSolarBoiler() {
        super(null,false,null);
    }

    /**Short circuits {@link SteamBoiler#getBaseSteamOutput()} to tweak the base steam output of the GTCEu singleblock solar boiler with the values from the config of GTCEU Steam Tweaker.
     * This injection will do nothing if {@code tweakSingleblockBoilerSteam} is set to {@code false} in the mod's config file.
     * @param cir (CallbackInfoReturnable&lt;Integer&gt;) Allows this method to short circuit the getBaseSteamOutput in {@link SteamSolarBoiler} and hijack the return value with our own logic
     */
    @Inject(method = "getBaseSteamOutput", at = @At("HEAD"), cancellable = true)
    private void onGetBaseSteamOutput(CallbackInfoReturnable<Integer> cir) {
        if (GTCEuSteamTweakerConfig.singleblockBoilerOutputConfig.tweakSingleblockBoilerSteam) {
            cir.setReturnValue((this.isHighPressure ? GTCEuSteamTweakerConfig.singleblockBoilerOutputConfig.highPressureSolarSteam : GTCEuSteamTweakerConfig.singleblockBoilerOutputConfig.lowPressureSolarSteam) * 20);
        }
    }
}