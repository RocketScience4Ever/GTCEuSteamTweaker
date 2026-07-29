package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakableMultiblockBoiler;

import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.common.metatileentities.multi.BoilerType;
import gregtech.common.metatileentities.multi.MetaTileEntityLargeBoiler;

import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MetaTileEntityLargeBoiler.class)
public abstract class MixinMetaTileEntityLargeBoiler extends MultiblockWithDisplayBase implements ITweakableMultiblockBoiler {
    @Shadow
    @Final
    public BoilerType boilerType;

    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinMetaTileEntityLargeBoiler(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    @Unique
    public int gTCEuSteamTweaker$getMultiblockBoilerConstantWaterRate() {
        return switch (this.boilerType) {
            case BRONZE -> GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.bronzeMultiblockConstantWaterRate > 0 ? GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.bronzeMultiblockConstantWaterRate : GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.multiblockConstantWaterRateMaster;
            case STEEL -> GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.steelMultiblockConstantWaterRate > 0 ? GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.steelMultiblockConstantWaterRate : GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.multiblockConstantWaterRateMaster;
            case TITANIUM -> GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.titaniumMultiblockConstantWaterRate > 0 ? GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.titaniumMultiblockConstantWaterRate : GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.multiblockConstantWaterRateMaster;
            case TUNGSTENSTEEL -> GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.tungstensteelMultiblockConstantWaterRate > 0 ? GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.tungstensteelMultiblockConstantWaterRate : GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.multiblockConstantWaterRateMaster;
        };
    }

    @Unique
    public double gTCEuSteamTweaker$getMultiblockBoilerSteamPWaterRatio() {
        return switch (this.boilerType) {
            case BRONZE -> GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.bronzeMultiblockSteamPWaterRatio > 0.005 ? GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.bronzeMultiblockSteamPWaterRatio : GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.multiblockSteamPWaterRatioMaster;
            case STEEL -> GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.steelMultiblockSteamPWaterRatio > 0.005 ? GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.steelMultiblockSteamPWaterRatio : GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.multiblockSteamPWaterRatioMaster;
            case TITANIUM -> GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.titaniumMultiblockSteamPWaterRatio > 0.005 ? GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.titaniumMultiblockSteamPWaterRatio : GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.multiblockSteamPWaterRatioMaster;
            case TUNGSTENSTEEL -> GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.tungstensteelMultiblockSteamPWaterRatio > 0.005 ? GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.tungstensteelMultiblockSteamPWaterRatio : GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.multiblockSteamPWaterRatioMaster;
        };
    }
}