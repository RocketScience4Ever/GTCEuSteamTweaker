package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakableSteamMachine;

import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.metatileentities.steam.SteamAlloySmelter;

import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;

/**Mixin to {@link SteamAlloySmelter} to apply the {@link ITweakableSteamMachine} interface.
 */
@Mixin(SteamAlloySmelter.class)
public abstract class MixinSteamAlloySmelter extends SteamMetaTileEntity implements ITweakableSteamMachine {
    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinSteamAlloySmelter(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer, boolean isHighPressure) {
        super(metaTileEntityId, recipeMap, renderer, isHighPressure);
    }

    @Override
    public double gTCEuSteamTweaker$getEUPermB() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.alloySmelterEUPmB > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.alloySmelterEUPmB : GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.singleblockMachineEUPmBMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getLowPressureDurationMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.lowPressureAlloySmelterRecipeDurationMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.lowPressureAlloySmelterRecipeDurationMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.lowPressureMachineRecipeDurationMultiplierMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getHighPressurePowerMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.highPressureAlloySmelterRecipePowerMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.highPressureAlloySmelterRecipePowerMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.highPressureMachineRecipePowerMultiplierMaster;
    }
}