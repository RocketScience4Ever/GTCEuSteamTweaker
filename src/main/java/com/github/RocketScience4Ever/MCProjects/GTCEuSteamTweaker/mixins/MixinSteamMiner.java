package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakableSteamMachine;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.common.metatileentities.steam.SteamMiner;

import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**Mixin to {@link SteamMiner} to apply the {@link ITweakableSteamMachine} interface.
 */
@Mixin(SteamMiner.class)
public abstract class MixinSteamMiner extends MetaTileEntity {
    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinSteamMiner(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    /**Tweaks the mB/t of GTCEu steam consumed by this {@link SteamMiner} using the value specified by the GTCEu Steam Tweaker Config.
     * This method exists to reflect the state of the {@code minermBPt} option when a new {@code SteamMiner} is initialized.
     */
    @WrapOperation(method = "drainEnergy", at = @At(value = "FIELD", target = "Lgregtech/common/metatileentities/steam/SteamMiner;energyPerTick:I"))
    private int onDrainEnergyGetEnergyPerTick(SteamMiner miner, Operation<Integer> original) {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.minermBPt;
    }

    /**Updates the tooltip for the {@link SteamMiner} to reflect changes made by {@link MixinSteamMiner#onDrainEnergyGetEnergyPerTick(SteamMiner, Operation)}.
     */
    @WrapOperation(method = "addInformation", at = @At(value = "FIELD", target = "Lgregtech/common/metatileentities/steam/SteamMiner;energyPerTick:I"))
    private int onAddInformationGetEnergyPerTick(SteamMiner miner, Operation<Integer> original) {
        return GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.minermBPt;
    }
}