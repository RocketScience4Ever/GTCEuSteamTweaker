package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakableSteamBoiler;

import gregtech.common.metatileentities.steam.boiler.SteamBoiler;
import gregtech.common.metatileentities.steam.boiler.SteamLavaBoiler;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**Mixin to {@link SteamLavaBoiler} to tweak the base steam output of the single block liquid fuel boilers.
 */
@Mixin(SteamLavaBoiler.class)
public abstract class MixinSteamLavaBoiler extends SteamBoiler implements ITweakableSteamBoiler {
    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinSteamLavaBoiler() {
        super(null,false,null);
    }

    /**Short circuits {@link SteamBoiler#getBaseSteamOutput()} to tweak the base steam output of the GTCEu singleblock liquid fuel boiler with the values from the config of GTCEU Steam Tweaker.
     * This injection will do nothing if {@code tweakSingleblockBoilerSteam} is set to {@code false} in the mod's config file.
     * @param cir (CallbackInfoReturnable&lt;Integer&gt;) Allows this method to short circuit the getBaseSteamOutput in {@link MixinSteamLavaBoiler} and hijack the return value with our own logic
     */
    @Inject(method = "getBaseSteamOutput", at = @At("HEAD"), cancellable = true)
    private void onGetBaseSteamOutput(CallbackInfoReturnable<Integer> cir) {
        if (GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleBlockBoilerSteamConfig.tweakSingleblockBoilerSteam) {
            cir.setReturnValue((this.isHighPressure ? GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleBlockBoilerSteamConfig.highPressureLiquidFuelSteam : GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleBlockBoilerSteamConfig.lowPressureLiquidFuelSteam) * 20);
        }
    }

    @Override
    public int gTCEuSteamTweaker$getConstantWaterConsumptionRate() {
        if (this.isHighPressure) {
            return GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.highPressureLiquidFuelConstantWaterRate > 0 ? GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.highPressureLiquidFuelConstantWaterRate : GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.singleblockConstantWaterRateMaster;
        } else {
            return GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.lowPressureLiquidFuelConstantWaterRate > 0 ? GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.lowPressureLiquidFuelConstantWaterRate : GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.singleblockConstantWaterRateMaster;
        }
    }

    @Override
    public double gTCEuSteamTweaker$getSteamPWaterRatio() {
        if (this.isHighPressure) {
            return GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.highPressureLiquidFuelSteamPWaterRatio > 0.005 ? GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.highPressureLiquidFuelSteamPWaterRatio : GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.singleblockSteamPWaterRatioMaster;
        } else {
            return GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.lowPressureLiquidFuelSteamPWaterRatio > 0.005 ? GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.lowPressureLiquidFuelSteamPWaterRatio : GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.singleblockSteamPWaterRatioMaster;
        }
    }
}