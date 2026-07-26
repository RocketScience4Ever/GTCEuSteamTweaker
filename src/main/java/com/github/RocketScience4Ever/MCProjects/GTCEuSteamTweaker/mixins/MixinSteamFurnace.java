package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakeableSteamEfficiency;

import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.metatileentities.steam.SteamFurnace;

import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;

/**Mixin to {@link SteamFurnace} to apply the {@link ITweakeableSteamEfficiency} interface.
 */
@Mixin(SteamFurnace.class)
public abstract class MixinSteamFurnace extends SteamMetaTileEntity implements ITweakeableSteamEfficiency {
    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinSteamFurnace(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer, boolean isHighPressure) {
        super(metaTileEntityId, recipeMap, renderer, isHighPressure);
    }

    @Override
    public double gTCEuSteamTweaker$getEUPermB() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockEUPmBConfig.furnaceEUPmB > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockEUPmBConfig.furnaceEUPmB : GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockEUPmBConfig.singleblockMachineEUPmBMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getLowPressureDurationMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockLowPressureDurationMultiplierConfig.lowPressureFurnaceRecipeDurationMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockLowPressureDurationMultiplierConfig.lowPressureFurnaceRecipeDurationMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockLowPressureDurationMultiplierConfig.lowPressureMachineRecipeDurationMultiplierMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getHighPressurePowerMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockHighPressurePowerMultiplierConfig.highPressureFurnaceRecipePowerMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockHighPressurePowerMultiplierConfig.highPressureFurnaceRecipePowerMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockHighPressurePowerMultiplierConfig.highPressureMachineRecipePowerMultiplierMaster;
    }
}