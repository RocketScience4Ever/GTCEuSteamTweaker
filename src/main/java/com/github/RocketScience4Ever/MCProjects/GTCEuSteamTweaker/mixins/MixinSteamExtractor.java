package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakableSteamMachine;

import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.metatileentities.steam.SteamExtractor;

import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;

/**Mixin to {@link SteamExtractor} to apply the {@link ITweakableSteamMachine} interface.
 */
@Mixin(SteamExtractor.class)
public abstract class MixinSteamExtractor extends SteamMetaTileEntity implements ITweakableSteamMachine {
    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinSteamExtractor(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer, boolean isHighPressure) {
        super(metaTileEntityId, recipeMap, renderer, isHighPressure);
    }

    @Override
    public double gTCEuSteamTweaker$getEUPermB() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.extractorEUPmB > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.extractorEUPmB : GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.singleblockMachineEUPmBMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getLowPressureDurationMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.lowPressureExtractorRecipeDurationMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.lowPressureExtractorRecipeDurationMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.lowPressureMachineRecipeDurationMultiplierMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getHighPressurePowerMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.highPressureExtractorRecipePowerMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.highPressureExtractorRecipePowerMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.highPressureMachineRecipePowerMultiplierMaster;
    }
}