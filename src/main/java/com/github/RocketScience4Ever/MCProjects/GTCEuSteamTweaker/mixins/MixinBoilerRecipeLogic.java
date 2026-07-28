package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakableMultiblockBoiler;

import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.capability.impl.BoilerRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.common.metatileentities.multi.MetaTileEntityLargeBoiler;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BoilerRecipeLogic.class)
public abstract class MixinBoilerRecipeLogic extends AbstractRecipeLogic {
    /**Variable for duck typing the {@link MetaTileEntityLargeBoiler} to call methods from {@link ITweakableMultiblockBoiler}.
     */
    @Unique
    private ITweakableMultiblockBoiler gTCEuSteamTweaker$tweakableMultiblockBoilerMTE;

    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinBoilerRecipeLogic(MetaTileEntity tileEntity, RecipeMap<?> recipeMap) {
        super(tileEntity, recipeMap);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        this.gTCEuSteamTweaker$tweakableMultiblockBoilerMTE = this.metaTileEntity instanceof ITweakableMultiblockBoiler ? (ITweakableMultiblockBoiler)this.metaTileEntity : null;
    }

    /**Replace the constant steam per water ratio in GTCEu multiblock boilers with the value from the GTCEu Steam Tweaker config.
     * @param originalSteamPWaterRatio (long) The original value of the steam per water ratio from the {@link BoilerRecipeLogic} class
     */
    @ModifyConstant(method = "updateRecipeProgress", constant = @Constant(longValue = 160L))
    private long onUpdateRecipeProgress(long originalSteamPWaterRatio) {
        if (GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.multiblockWaterLogicType == 2) {
            return (long)gTCEuSteamTweaker$tweakableMultiblockBoilerMTE.gTCEuSteamTweaker$getMultiblockBoilerSteamPWaterRatio();
        } else {
            return originalSteamPWaterRatio;
        }
    }
}