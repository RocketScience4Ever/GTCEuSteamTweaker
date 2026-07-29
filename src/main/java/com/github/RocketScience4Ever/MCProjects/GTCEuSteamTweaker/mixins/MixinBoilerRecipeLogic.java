package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakableMultiblockBoiler;

import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.capability.impl.BoilerRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.unification.material.Materials;
import gregtech.common.ConfigHolder;
import gregtech.common.metatileentities.multi.MetaTileEntityLargeBoiler;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import org.jetbrains.annotations.NotNull;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BoilerRecipeLogic.class)
public abstract class MixinBoilerRecipeLogic extends AbstractRecipeLogic {
    /**Variable for duck typing the {@link MetaTileEntityLargeBoiler} to call methods from {@link ITweakableMultiblockBoiler}.
     */
    @Unique
    private ITweakableMultiblockBoiler gTCEuSteamTweaker$tweakableMultiblockBoilerMTE;

    /**The amount of water that was available in the boiler's input tanks after the most recent call to {@code this#updateRecipeProgress()}.
     */
    @Unique
    private int gTCEuSteamTweaker$lastTickWater = 0;

    @Shadow
    private int currentHeat;

    @Shadow
    private int excessWater;

    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinBoilerRecipeLogic(MetaTileEntity tileEntity, RecipeMap<?> recipeMap) {
        super(tileEntity, recipeMap);
    }

    @Shadow
    public abstract @NotNull MetaTileEntityLargeBoiler getMetaTileEntity();

    @Shadow
    protected abstract int getMaximumHeatFromMaintenance();

    @Shadow
    protected abstract int getMaximumHeat();

    @Shadow
    public abstract void setHeat(int heat);

    @Shadow
    public abstract void setLastTickSteam(int steam);

    /**Returns the amount of water that was available in the boiler's input tanks after the most recent call to {@code this#updateRecipeProgress()}.
     * @return (int) The amount of water that was available in the boiler's input tanks after the most recent call to {@code this#updateRecipeProgress()} ({@code this.gTCEuSteamTweaker$lastTickWater})
     */
    @Unique
    private int gTCEuSteamTweaker$getLastTickWater() {
        return this.gTCEuSteamTweaker$lastTickWater;
    }

    /**Calculates the amount of water, distilled water, and other valid boiler fluids (specified by the GTCEu config) currently available in this boiler's input tanks
     * and stores the result to {@code this.gTCEuSteamTweaker$lastTickWater}.
     * <p>This method is called at the end of each call to {@code this#updateRecipeProgress()} to let the boiler determine whether it needs to explode if it successfully drains water on the next tick.
     * The boiler will explode on a given tick if it successfully drains water AND {@code this.gTCEuSteamTweaker$lastTickWater} was set to {@code 0} by this method on the previous tick.</p>
     */
    @Unique
    private void gTCEuSteamTweaker$setLastTickWater() {
        this.gTCEuSteamTweaker$lastTickWater = 0;

        //Loop through each input tank on the large boiler and take the sum of any water, distilled water, or other valid boiler fluid contained in the input tanks
        for (IMultipleTankHandler.MultiFluidTankEntry tank : this.getInputTank().getFluidTanks()) {
            if (tank.getFluid() != null && tank.getFluidAmount() > 0) {
                if (tank.getFluid().isFluidEqual(Materials.Water.getFluid(1)) || tank.getFluid().isFluidEqual(Materials.DistilledWater.getFluid(1))) {
                    this.gTCEuSteamTweaker$lastTickWater += tank.getFluidAmount(); //Count water and distilled water towards the available water
                } else {
                    for(String boilerFluidName : ConfigHolder.machines.boilerFluids) {
                        Fluid boilerFluid = FluidRegistry.getFluid(boilerFluidName); //Get each fluid listed in the GTCEu config as a valid fluid to turn into steam
                        if (boilerFluid != null && tank.getFluid().isFluidEqual(new FluidStack(boilerFluid,1))) {
                            this.gTCEuSteamTweaker$lastTickWater += tank.getFluidAmount(); //Count other valid boiler fluids towards the available water
                            break;
                        }
                    }
                }
            }
        }
    }

    /**An improved version of {@code BoilerRecipeLogic#getBoilerFluidFromContainer(IFluidHandler,int)} which attempts to drain multiple types of boiler fluids
     * if it cannot drain the full amount of fluid required to produce steam from just a single one of its input tanks.
     * @param amountToDrain (int) The maximum amount (in mB) of water/distilled water/other boiler fluids that this method should attempt to drain
     * @param doDrain (boolean) {@code true} if this method should actually drain the boiler's input tanks of fluid; {@code false} if this method should calculate the amount of fluid that can be drained (up to a maximum of {@code amountToDrain}) without actually draining the boiler's input fluid tanks
     * @return (int) The total amount of fluid that was successfully drained by this method call, should be at most equal to {@code amountToDrain}
     */
    @Unique
    private int gTCEuSteamTweaker$drainBoilerFluid(int amountToDrain, boolean doDrain) {
        if (amountToDrain != 0) {
            IFluidHandler boilerInputTanks = this.getInputTank();
            int waterDrainedAmount = 0; //The amount of water that has been successfully drained so far

            //Attempt to drain water
            FluidStack waterDrainedStack = boilerInputTanks.drain(Materials.Water.getFluid(amountToDrain),doDrain);
            waterDrainedAmount += waterDrainedStack != null ? waterDrainedStack.amount : 0; //Add to the amount of fluid successfully drained by this method

            if (waterDrainedAmount >= amountToDrain) {
                return waterDrainedAmount; //Return if there was enough water
            }

            //Attempt to drain distilled water to make up the remaining portion of the amountToDrain
            waterDrainedStack = boilerInputTanks.drain(Materials.DistilledWater.getFluid(amountToDrain - waterDrainedAmount),doDrain);
            waterDrainedAmount += waterDrainedStack != null ? waterDrainedStack.amount : 0;

            if (waterDrainedAmount >= amountToDrain) {
                return waterDrainedAmount; //Return if there was enough water and distilled water
            }

            //Attempt to drain any other boiler fluids specified by the GTCEu config to make up the remaining portion of the amountToDrain
            for(String boilerFluidName : ConfigHolder.machines.boilerFluids) {
                Fluid boilerFluid = FluidRegistry.getFluid(boilerFluidName); //For each valid boiler fluid in the GTCEu config
                if (boilerFluid != null) {
                    //Attempt to drain the current boiler fluid in the loop
                    waterDrainedStack = boilerInputTanks.drain(new FluidStack(boilerFluid,amountToDrain - waterDrainedAmount),doDrain);
                    waterDrainedAmount += waterDrainedStack != null ? waterDrainedStack.amount : 0;

                    if (waterDrainedAmount >= amountToDrain) {
                        return waterDrainedAmount; //Return if there was enough water/distilled water/other boiler fluids
                    }
                }
            }

            return waterDrainedAmount; //Return the total amount of fluid drained by this method call (will be less than the amountToDrain)
        } else {
            return 0; //Successfully drained 0 mB of fluid
        }
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        this.gTCEuSteamTweaker$tweakableMultiblockBoilerMTE = this.metaTileEntity instanceof ITweakableMultiblockBoiler ? (ITweakableMultiblockBoiler)this.metaTileEntity : null;
    }

    /**Short circuit {@code BoilerRecipeLogic#updateRecipeProgress()} to allow for tweaked water and steam logic for multiblock boilers.
     * @param ci (CallbackInfo) Used to short circuit the target method after performing the GTCEu Steam Tweaker logic
     */
    @Inject(method = "updateRecipeProgress", at = @At("HEAD"), cancellable = true)
    private void onUpdateRecipeProgress(CallbackInfo ci) {
        if (GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.multiblockWaterLogicType > 0) {
            if (this.canRecipeProgress) {
                int steamToProduce = this.recipeEUt * this.getMaximumHeatFromMaintenance() / this.getMaximumHeat(); //Determine the amount of steam to produce based on the maintenance state of the boiler
                int waterToDrain;

                if (steamToProduce > 0) {
                    this.excessWater = 0; //This value is not used for the tweaked water logic
                    switch (GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.multiblockWaterLogicType) {
                        case 1 -> waterToDrain = gTCEuSteamTweaker$tweakableMultiblockBoilerMTE.gTCEuSteamTweaker$getMultiblockBoilerConstantWaterRate(); //Constant water logic
                        case 2 -> waterToDrain = (int)Math.ceil(Math.max(1.0,steamToProduce / gTCEuSteamTweaker$tweakableMultiblockBoilerMTE.gTCEuSteamTweaker$getMultiblockBoilerSteamPWaterRatio())); //Variable water logic
                        default -> { return; } //Use default GTCEu water logic
                    }  //Calculate the water consumption for this cycle according to the type of water logic selected in the GTCEu Steam Tweaker config

                    int waterDrained = gTCEuSteamTweaker$drainBoilerFluid(waterToDrain,true); //Drain water from the boiler

                    if (waterDrained > 0) {
                        //Explode if the boiler drained water after being out of water
                        if (this.gTCEuSteamTweaker$getLastTickWater() == 0) {
                            this.getMetaTileEntity().explodeMultiblock(((float) this.currentHeat / (float) this.getMaximumHeat()) * 8.0F);
                        }

                        //Attempt to produce steam
                        int availableSteam = (int)Math.ceil(Math.max(1.0,waterDrained * gTCEuSteamTweaker$tweakableMultiblockBoilerMTE.gTCEuSteamTweaker$getMultiblockBoilerSteamPWaterRatio()));
                        if (availableSteam < steamToProduce) {
                            steamToProduce = GTCEuSteamTweakerConfig.multiblockBoilerConfig.multiblockBoilerWaterConfig.multiblockWaterLogicType == 2 ? availableSteam : 0; //Handle insufficient water according to the selected water logic
                        }

                        this.setLastTickSteam(steamToProduce);
                        this.getOutputTank().fill(Materials.Steam.getFluid(steamToProduce), true);
                    } else {
                        this.setLastTickSteam(0); //No water was available to generate steam
                    }
                }

                if (this.currentHeat < this.getMaximumHeat()) {
                    this.setHeat(this.currentHeat + 1); //Increase heat while the boiler is warming up
                }

                if (++this.progressTime > this.maxProgressTime) {
                    this.completeRecipe(); //Complete fuel burning recipe when the current fuel runs out of burn time
                }
            }

            this.gTCEuSteamTweaker$setLastTickWater(); //Let the boiler know how much water/distilled water/other boiler fluid is left in the input tanks
            ci.cancel(); //Short circuit the target method to disable the original GTCEu logic
        }
    }
}