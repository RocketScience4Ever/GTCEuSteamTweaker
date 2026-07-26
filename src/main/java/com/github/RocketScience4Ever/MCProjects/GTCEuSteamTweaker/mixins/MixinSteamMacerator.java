package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakeableSteamEfficiency;

import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.metatileentities.steam.SteamMacerator;

import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;

/**Mixin to {@link SteamMacerator} to apply the {@link ITweakeableSteamEfficiency} interface.
 */
@Mixin(SteamMacerator.class)
public abstract class MixinSteamMacerator extends SteamMetaTileEntity implements ITweakeableSteamEfficiency {
    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinSteamMacerator(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer, boolean isHighPressure) {
        super(metaTileEntityId, recipeMap, renderer, isHighPressure);
    }

    @Override
    public double gTCEuSteamTweaker$getEUPermB() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockEUPmBConfig.maceratorEUPmB > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockEUPmBConfig.maceratorEUPmB : GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockEUPmBConfig.singleblockMachineEUPmBMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getLowPressureDurationMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockLowPressureDurationMultiplierConfig.lowPressureMaceratorRecipeDurationMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockLowPressureDurationMultiplierConfig.lowPressureMaceratorRecipeDurationMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockLowPressureDurationMultiplierConfig.lowPressureMachineRecipeDurationMultiplierMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getHighPressurePowerMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockHighPressurePowerMultiplierConfig.highPressureMaceratorRecipePowerMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockHighPressurePowerMultiplierConfig.highPressureMaceratorRecipePowerMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockHighPressurePowerMultiplierConfig.highPressureMachineRecipePowerMultiplierMaster;
    }
}