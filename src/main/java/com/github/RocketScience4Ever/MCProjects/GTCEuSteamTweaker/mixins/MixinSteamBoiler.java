package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakableSteamBoiler;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.unification.material.Materials;
import gregtech.common.ConfigHolder;
import gregtech.common.metatileentities.steam.boiler.SteamBoiler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SteamBoiler.class)
public abstract class MixinSteamBoiler extends MetaTileEntity {
    @Unique
    ITweakableSteamBoiler gTCEuSteamTweaker$tweakableSteamBoiler; //Variable for duck typing

    @Shadow
    private int currentTemperature; //The current temperature of the boiler in centigrade

    @Shadow
    private boolean hasNoWater; //True if the boiler ran out of water on this tick

    @Shadow
    protected FluidTank waterFluidTank; //The fluid tank which stores the water for this boiler

    @Shadow
    protected FluidTank steamFluidTank; //The fluid tank which stores the steam for this boiler

    @Shadow
    public int getTotalSteamOutput() {
        throw new AssertionError();
    }

    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinSteamBoiler(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        this.gTCEuSteamTweaker$tweakableSteamBoiler = this instanceof ITweakableSteamBoiler ? (ITweakableSteamBoiler)this : null;
    }

    /**Short circuit {@code SteamBoiler#generateSteam()} to allow for tweaked water and steam logic.
     * @param ci (CallbackInfo) Used to short circuit the target method after performing the GTCEu Steam Tweaker logic
     */
    @Inject(method = "generateSteam", at = @At(value = "HEAD"), cancellable = true)
    private void onGenerateSteam(CallbackInfo ci) {
        if (GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.singleblockWaterLogicType > 0) {
            if (this.currentTemperature >= 100) {
                int steamToProduce = this.getTotalSteamOutput(); //Calculate the steam production for this cycle
                int waterToDrain;
                switch (GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.singleblockWaterLogicType) {
                    case 1 -> waterToDrain = this.gTCEuSteamTweaker$tweakableSteamBoiler.gTCEuSteamTweaker$getConstantWaterConsumptionRate(); //Constant water logic
                    case 2 -> waterToDrain = (int)Math.ceil(Math.max(1.0,steamToProduce / this.gTCEuSteamTweaker$tweakableSteamBoiler.gTCEuSteamTweaker$getSteamPWaterRatio())); //Variable water logic
                    default -> { return; } //Use default GTCEu water logic
                } //Calculate the water consumption for this cycle according to the type of water logic selected in the GTCEu Steam Tweaker config

                FluidStack waterDrained = this.waterFluidTank.drain(waterToDrain,true); //Drain water from the boiler's water tank

                //Perform the steam generation/water consumption logic if there is water available to consume
                if (waterDrained != null) {
                    //Explode the boiler if it received water while it was still hot after running out of water
                    if (this.hasNoWater) {
                        this.doExplosion(2.0f);
                    }

                    //Reduce the amount of steam to produce if there is insufficient water available to produce the original amount of steam
                    if (waterDrained.amount < waterToDrain) {
                        steamToProduce = GTCEuSteamTweakerConfig.singleblockBoilerConfig.singleblockBoilerWaterConfig.singleblockWaterLogicType == 2 ? (int)Math.floor(Math.max(1.0,waterDrained.amount * this.gTCEuSteamTweaker$tweakableSteamBoiler.gTCEuSteamTweaker$getSteamPWaterRatio())) : 0;
                    }

                    //Produce steam and vent the boiler's steam tank if full
                    if (this.steamFluidTank.fill(Materials.Steam.getFluid(steamToProduce),true) < steamToProduce) {
                        float x = (float)this.getPos().getX() + 0.5F;
                        float y = (float)this.getPos().getY() + 0.5F;
                        float z = (float)this.getPos().getZ() + 0.5F;
                        ((WorldServer)this.getWorld()).spawnParticle(EnumParticleTypes.CLOUD,(double)x + (double)this.getFrontFacing().getXOffset() * 0.6,(double)y + (double)this.getFrontFacing().getYOffset() * 0.6,(double)z + (double)this.getFrontFacing().getZOffset() * 0.6,7 + GTValues.RNG.nextInt(3),(double)this.getFrontFacing().getXOffset() / 2.0,(double)this.getFrontFacing().getYOffset() / 2.0,(double)this.getFrontFacing().getZOffset() / 2.0,0.1,new int[0]);
                        if (ConfigHolder.machines.machineSounds && !this.isMuffled()) {
                            this.getWorld().playSound((EntityPlayer)null,(double)x,(double)y,(double)z,SoundEvents.BLOCK_LAVA_EXTINGUISH,SoundCategory.BLOCKS,1.0F,1.0F);
                        }

                        this.steamFluidTank.drain(4000, true);
                    }
                }
            }

            this.hasNoWater = this.waterFluidTank.getFluidAmount() <= 0; //If there is 0 or less water, the boiler has no water
            ci.cancel();
        }
    }
}