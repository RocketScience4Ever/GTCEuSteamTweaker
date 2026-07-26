package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakeableSteamEfficiency;

import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.metatileentities.steam.SteamExtractor;

import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;

/**Mixin to {@link SteamExtractor} to apply the {@link ITweakeableSteamEfficiency} interface.
 */
@Mixin(SteamExtractor.class)
public abstract class MixinSteamExtractor extends SteamMetaTileEntity implements ITweakeableSteamEfficiency {
    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinSteamExtractor(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer, boolean isHighPressure) {
        super(metaTileEntityId, recipeMap, renderer, isHighPressure);
    }

    @Override
    public double gTCEuSteamTweaker$getEUPermB() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockEUPmBConfig.extractorEUPmB > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockEUPmBConfig.extractorEUPmB : GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockEUPmBConfig.singleblockMachineEUPmBMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getLowPressureDurationMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockLowPressureDurationMultiplierConfig.lowPressureExtractorRecipeDurationMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockLowPressureDurationMultiplierConfig.lowPressureExtractorRecipeDurationMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockLowPressureDurationMultiplierConfig.lowPressureMachineRecipeDurationMultiplierMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getHighPressurePowerMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockHighPressurePowerMultiplierConfig.highPressureExtractorRecipePowerMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockHighPressurePowerMultiplierConfig.highPressureExtractorRecipePowerMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockHighPressurePowerMultiplierConfig.highPressureMachineRecipePowerMultiplierMaster;
    }
}