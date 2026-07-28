package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakableSteamMachine;

import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.metatileentities.steam.SteamHammer;

import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;

/**Mixin to {@link SteamHammer} to apply the {@link ITweakableSteamMachine} interface.
 */
@Mixin(SteamHammer.class)
public abstract class MixinSteamForgeHammer extends SteamMetaTileEntity implements ITweakableSteamMachine {
    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinSteamForgeHammer(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer, boolean isHighPressure) {
        super(metaTileEntityId, recipeMap, renderer, isHighPressure);
    }

    @Override
    public double gTCEuSteamTweaker$getEUPermB() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.forgeHammerEUPmB > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.forgeHammerEUPmB : GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.singleblockMachineEUPmBMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getLowPressureDurationMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.lowPressureForgeHammerRecipeDurationMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.lowPressureForgeHammerRecipeDurationMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.lowPressureMachineRecipeDurationMultiplierMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getHighPressurePowerMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.highPressureForgeHammerRecipePowerMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.highPressureForgeHammerRecipePowerMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.highPressureMachineRecipePowerMultiplierMaster;
    }
}