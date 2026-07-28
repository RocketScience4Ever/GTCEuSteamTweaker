package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces;

/**Interface to house the methods that allow water consumption tweaks to be individual to each steam boiler if desired.
 * <p>Used to apply duck typing to single block steam boilers.</p>
 */
public interface ITweakableSteamBoiler {
    /**Returns the constant amount of water (in mB) that this type of steam boiler should consume for each steam generation cycle if the {@code singleblockWaterLogicType == 1}.
     * @return (int) The constant water consumption rate which should be used for this type of single block boiler
     */
    int gTCEuSteamTweaker$getConstantWaterConsumptionRate();

    /**Returns the amount of steam (in mB) that this type of steam boiler should produce for each mB of water consumed by a steam generation cycle if the {@code singleblockWaterLogicType == 2}.
     * @return (double) The steam per water ratio which should be used for this type of single block boiler
     */
    double gTCEuSteamTweaker$getSteamPWaterRatio();
}