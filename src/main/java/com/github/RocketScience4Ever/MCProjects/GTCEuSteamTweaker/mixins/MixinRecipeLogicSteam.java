package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixins;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config.GTCEuSteamTweakerConfig;
import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces.ITweakableSteamMachine;

import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.capability.impl.RecipeLogicSteam;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;

import net.minecraftforge.fluids.IFluidTank;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nonnull;

/**Mixin to the {@link RecipeLogicSteam} class to inject logic which replaces the hard-coded GTCEu steam efficiency of single-block steam machines with configurable logic from GTCEu Steam Tweaker.
 */
@Mixin(RecipeLogicSteam.class)
public abstract class MixinRecipeLogicSteam extends AbstractRecipeLogic {
    @Unique
    private ITweakableSteamMachine gTCEuSteamTweaker$tweakableSteamEfficiencyMTE; //Variable for duck typing

    @Shadow
    @Final
    private IFluidTank steamFluidTank; //Tank from which the steam machine draws steam for energy

    @Shadow
    @Final
    private boolean isHighPressure; //true if this is a high pressure machine; false otherwise

    /**Do not call this; it only exists to make the compiler happy.
     */
    private MixinRecipeLogicSteam(MetaTileEntity tileEntity, RecipeMap<?> recipeMap) {
        super(tileEntity, recipeMap);
    }

    /**Method to duck type the {@link MetaTileEntity} in the {@link RecipeLogicSteam} if it implements {@link ITweakableSteamMachine}.
     * This duck typing is applied during the {@code RecipeLogicSteam} constructor to prevent having to re-cast the {@code MetaTileEntity} every time it is used to access a method from {@code ITweakeableSteamEfficiency}.
     * @param ci (CallbackInfo) Unused, because this is not a cancellable injector
     */
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        gTCEuSteamTweaker$tweakableSteamEfficiencyMTE = this.metaTileEntity instanceof ITweakableSteamMachine ? (ITweakableSteamMachine)this.metaTileEntity : null;
    }

    /**Short circuits {@code RecipeLogicSteam#getEnergyStored()} to reflect changes to the EU/mB ratio made by GTCEu Steam Tweaker.
     * <p>This injection will do nothing if {@code tweakSingleblockMachineEfficiency} is set to {@code false} in the mod's config file.</p>
     * @param cir (CallbackInfoReturnable&lt;Long&gt;) Used to short circuit the target method and return the correct value when tweaks are applied
     */
    @Inject(method = "getEnergyStored", at = @At("HEAD"), cancellable = true)
    private void onGetEnergyStored(CallbackInfoReturnable<Long> cir) {
        if (GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.tweakSingleblockMachineEfficiency && gTCEuSteamTweaker$tweakableSteamEfficiencyMTE != null) {
            cir.setReturnValue((long)Math.ceil((double)this.steamFluidTank.getFluidAmount() * this.gTCEuSteamTweaker$tweakableSteamEfficiencyMTE.gTCEuSteamTweaker$getEUPermB()));
        }
    }

    /**Short circuits {@code RecipeLogicSteam#getEnergyCapacity()} to reflect changes to the EU/mB ratio made by GTCEu Steam Tweaker.
     * <p>This injection will do nothing if {@code tweakSingleblockMachineEfficiency} is set to {@code false} in the mod's config file.</p>
     * @param cir (CallbackInfoReturnable&lt;Long&gt;) Used to short circuit the target method and return the correct value when tweaks are applied
     */
    @Inject(method = "getEnergyCapacity", at = @At("HEAD"), cancellable = true)
    private void onGetEnergyCapacity(CallbackInfoReturnable<Long> cir) {
        if (GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.tweakSingleblockMachineEfficiency && gTCEuSteamTweaker$tweakableSteamEfficiencyMTE != null) {
            cir.setReturnValue((long)Math.floor((double)this.steamFluidTank.getCapacity() * this.gTCEuSteamTweaker$tweakableSteamEfficiencyMTE.gTCEuSteamTweaker$getEUPermB()));
        }
    }

    /**Short circuits {@code RecipeLogicSteam#calculateOverclock(Recipe)} to replace the hard-coded EU/mB, low pressure duration multiplier,
     * and high pressure power multiplier in vanilla GTCEu with values from the config for GTCEu Steam Tweaker.
     * <p>This injection will do nothing if {@code tweakSingleblockMachineEfficiency} is set to {@code false} in the mod's config file.</p>
     * @param recipe (Recipe) The recipe the machine is attempting to perform, inherited from the arguments of the target method
     * @param cir (CallbackInfoReturnable&lt;int[]&gt;) Used to short circuit the target method and return the values for EU/t and recipe duration calculated with tweak from GTCEu Steam Tweaker applied
     */
    @Inject(method = "calculateOverclock", at = @At("HEAD"), cancellable = true)
    private void onCalculateOverclock(@Nonnull Recipe recipe, CallbackInfoReturnable<int[]> cir) {
        if (GTCEuSteamTweakerConfig.singleblockMachineEfficiencyConfig.tweakSingleblockMachineEfficiency && gTCEuSteamTweaker$tweakableSteamEfficiencyMTE != null) {
            int[] result = new int[2];
            double baseEUt = (recipe.getEUt() / this.gTCEuSteamTweaker$tweakableSteamEfficiencyMTE.gTCEuSteamTweaker$getEUPermB()); //Tweak EU/mB ratio

            result[0] = (int)Math.max(1.0,this.isHighPressure ? baseEUt * this.gTCEuSteamTweaker$tweakableSteamEfficiencyMTE.gTCEuSteamTweaker$getHighPressurePowerMultiplier() : baseEUt); //Tweak high pressure power multiplier
            result[1] = (int)Math.max(1.0,this.isHighPressure ? recipe.getDuration() : recipe.getDuration() * this.gTCEuSteamTweaker$tweakableSteamEfficiencyMTE.gTCEuSteamTweaker$getLowPressureDurationMultiplier()); //Tweak low pressure duration multiplier
            cir.setReturnValue(result); //Short circuit the target method with the result from the GTCEu Steam Tweaker logic
        }
    }
}