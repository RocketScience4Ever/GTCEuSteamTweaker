package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakeableSteamEfficiency;

import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.metatileentities.steam.SteamAlloySmelter;

import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;

/**Mixin to {@link SteamAlloySmelter} to apply the {@link ITweakeableSteamEfficiency} interface.
 */
@Mixin(SteamAlloySmelter.class)
public abstract class MixinSteamAlloySmelter extends SteamMetaTileEntity implements ITweakeableSteamEfficiency {
    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinSteamAlloySmelter(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer, boolean isHighPressure) {
        super(metaTileEntityId, recipeMap, renderer, isHighPressure);
    }

    @Override
    public double gTCEuSteamTweaker$getEUPermB() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockEUPmBConfig.alloySmelterEUPmB > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockEUPmBConfig.alloySmelterEUPmB : GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockEUPmBConfig.singleblockMachineEUPmBMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getLowPressureDurationMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockLowPressureDurationMultiplierConfig.lowPressureAlloySmelterRecipeDurationMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockLowPressureDurationMultiplierConfig.lowPressureAlloySmelterRecipeDurationMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockLowPressureDurationMultiplierConfig.lowPressureMachineRecipeDurationMultiplierMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getHighPressurePowerMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockHighPressurePowerMultiplierConfig.highPressureAlloySmelterRecipePowerMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockHighPressurePowerMultiplierConfig.highPressureAlloySmelterRecipePowerMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockHighPressurePowerMultiplierConfig.highPressureMachineRecipePowerMultiplierMaster;
    }
}