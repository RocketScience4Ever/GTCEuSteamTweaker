package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakableSteamMachine;

import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.metatileentities.steam.SteamMacerator;

import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;

/**Mixin to {@link SteamMacerator} to apply the {@link ITweakableSteamMachine} interface.
 */
@Mixin(SteamMacerator.class)
public abstract class MixinSteamMacerator extends SteamMetaTileEntity implements ITweakableSteamMachine {
    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinSteamMacerator(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer, boolean isHighPressure) {
        super(metaTileEntityId, recipeMap, renderer, isHighPressure);
    }

    @Override
    public double gTCEuSteamTweaker$getEUPermB() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.maceratorEUPmB > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.maceratorEUPmB : GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.singleblockMachineEUPmBMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getLowPressureDurationMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.lowPressureMaceratorRecipeDurationMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.lowPressureMaceratorRecipeDurationMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.lowPressureMachineRecipeDurationMultiplierMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getHighPressurePowerMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.highPressureMaceratorRecipePowerMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.highPressureMaceratorRecipePowerMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.highPressureMachineRecipePowerMultiplierMaster;
    }
}