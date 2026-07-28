package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakableSteamBoiler;

import gregtech.common.metatileentities.steam.boiler.SteamBoiler;
import gregtech.common.metatileentities.steam.boiler.SteamCoalBoiler;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**Mixin to {@link SteamCoalBoiler} to tweak the base steam output of the single block solid fuel boilers.
 */
@Mixin(SteamCoalBoiler.class)
public abstract class MixinSteamCoalBoiler extends SteamBoiler implements ITweakableSteamBoiler {
    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinSteamCoalBoiler() {
        super(null,false,null);
    }

    /**Short circuits {@link SteamBoiler#getBaseSteamOutput()} to tweak the base steam output of the GTCEu singleblock solid fuel boiler with the values from the config of GTCEU Steam Tweaker.
     * This injection will do nothing if {@code tweakSingleblockBoilerSteam} is set to {@code false} in the mod's config file.
     * @param cir (CallbackInfoReturnable&lt;Integer&gt;) Allows this method to short circuit the getBaseSteamOutput in {@link SteamCoalBoiler} and hijack the return value with our own logic
     */
    @Inject(method = "getBaseSteamOutput", at = @At("HEAD"), cancellable = true)
    private void onGetBaseSteamOutput(CallbackInfoReturnable<Integer> cir) {
        if (GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleBlockBoilerSteamConfig.tweakSingleblockBoilerSteam) {
            cir.setReturnValue((this.isHighPressure ? GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleBlockBoilerSteamConfig.highPressureSolidFuelSteam : GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleBlockBoilerSteamConfig.lowPressureSolidFuelSteam) * 20);
        }
    }

    @Override
    public int gTCEuSteamTweaker$getConstantWaterConsumptionRate() {
        if (this.isHighPressure) {
            return GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.highPressureSolidFuelConstantWaterRate > 0 ? GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.highPressureSolidFuelConstantWaterRate : GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.singleblockConstantWaterRateMaster;
        } else {
            return GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.lowPressureSolidFuelConstantWaterRate > 0 ? GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.lowPressureSolidFuelConstantWaterRate : GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.singleblockConstantWaterRateMaster;
        }
    }

    @Override
    public double gTCEuSteamTweaker$getSteamPWaterRatio() {
        if (this.isHighPressure) {
            return GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.highPressureSolidFuelSteamPWaterRatio > 0.005 ? GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.highPressureSolidFuelSteamPWaterRatio : GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.singleblockSteamPWaterRatioMaster;
        } else {
            return GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.lowPressureSolidFuelSteamPWaterRatio > 0.005 ? GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.lowPressureSolidFuelSteamPWaterRatio : GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.singleblockSteamPWaterRatioMaster;
        }
    }
}