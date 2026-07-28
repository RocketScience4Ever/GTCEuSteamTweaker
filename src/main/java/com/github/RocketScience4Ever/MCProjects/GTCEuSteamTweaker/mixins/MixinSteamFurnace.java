package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakableSteamMachine;

import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.metatileentities.steam.SteamFurnace;

import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;

/**Mixin to {@link SteamFurnace} to apply the {@link ITweakableSteamMachine} interface.
 */
@Mixin(SteamFurnace.class)
public abstract class MixinSteamFurnace extends SteamMetaTileEntity implements ITweakableSteamMachine {
    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinSteamFurnace(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer, boolean isHighPressure) {
        super(metaTileEntityId, recipeMap, renderer, isHighPressure);
    }

    @Override
    public double gTCEuSteamTweaker$getEUPermB() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.furnaceEUPmB > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.furnaceEUPmB : GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.singleblockMachineEUPmBMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getLowPressureDurationMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.lowPressureFurnaceRecipeDurationMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.lowPressureFurnaceRecipeDurationMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.lowPressureMachineRecipeDurationMultiplierMaster;
    }

    @Override
    public double gTCEuSteamTweaker$getHighPressurePowerMultiplier() {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.highPressureFurnaceRecipePowerMultiplier > 0 ? GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.highPressureFurnaceRecipePowerMultiplier : GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.highPressureMachineRecipePowerMultiplierMaster;
    }
}