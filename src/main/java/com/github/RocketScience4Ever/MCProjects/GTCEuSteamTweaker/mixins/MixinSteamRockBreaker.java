package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakeableSteamEfficiency;

import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.metatileentities.steam.SteamRockBreaker;

import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;

/**Mixin to {@link SteamRockBreaker} to apply the {@link ITweakeableSteamEfficiency} interface.
 */
@Mixin(SteamRockBreaker.class)
public abstract class MixinSteamRockBreaker extends SteamMetaTileEntity implements ITweakeableSteamEfficiency {
    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinSteamRockBreaker(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer, boolean isHighPressure) {
        super(metaTileEntityId, recipeMap, renderer, isHighPressure);
    }

    @Override
    public double gTCEuSteamTweaker$getEUPermB() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockEUPmBConfig.rockBreakerEUPmB > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockEUPmBConfig.rockBreakerEUPmB : GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockEUPmBConfig.singleblockMachineEUPmBMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getLowPressureDurationMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockLowPressureDurationMultiplierConfig.lowPressureRockBreakerRecipeDurationMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockLowPressureDurationMultiplierConfig.lowPressureRockBreakerRecipeDurationMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockLowPressureDurationMultiplierConfig.lowPressureMachineRecipeDurationMultiplierMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getHighPressurePowerMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockHighPressurePowerMultiplierConfig.highPressureRockBreakerRecipePowerMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockHighPressurePowerMultiplierConfig.highPressureRockBreakerRecipePowerMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiency.singleblockHighPressurePowerMultiplierConfig.highPressureMachineRecipePowerMultiplierMaster;
    }
}