package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.gtceusteamtweaker.Tags;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakeableSteamEfficiency;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.common.metatileentities.steam.SteamMiner;

import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**Mixin to {@link SteamMiner} to apply the {@link ITweakeableSteamEfficiency} interface.
 */
@Mixin(SteamMiner.class)
public abstract class MixinSteamMiner extends MetaTileEntity {
    @Mutable
    @Final
    @Shadow
    private int energyPerTick;

    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinSteamMiner(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    /**Tweaks the mB/t of GTCEu steam consumed by this {@link SteamMiner} using the value specified by the GTCEu Steam Tweaker Config.
     * This method exists to reflect the state of the {@code minermBPt} option when a new {@code SteamMiner} is initialized.
     */
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        this.energyPerTick = GTCEuSteamTweakerConfig.singleblockMachineEfficiency.minermBPt;
    }

    /**Updates the mB/t of GTCEu steam consumed by this {@link SteamMiner} using the value specified by the GTCEu Steam Tweaker Config.
     * This method exists to reflect changes the {@code minermBPt} option using the in-game mod config menu.
     */
    @Unique
    @SubscribeEvent
    private void gTCEuSteamTweaker$onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Tags.MOD_ID)) {
            this.energyPerTick = GTCEuSteamTweakerConfig.singleblockMachineEfficiency.minermBPt;
        }
    }
}