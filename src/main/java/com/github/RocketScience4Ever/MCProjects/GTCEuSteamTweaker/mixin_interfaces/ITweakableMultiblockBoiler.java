package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces;

/**Interface to house the methods that allow water consumption tweaks to be individual to each steam boiler if desired.
 * <p>Used to apply duck typing to multiblock steam boilers.</p>
 */
public interface ITweakableMultiblockBoiler {
    /**Returns the amount of steam (in mB) that this type of multiblock steam boiler should produce for each mB of water consumed by a steam generation cycle if the {@code multiblockWaterLogicType == 2}.
     * @return (double) The steam per water ratio which should be used for this type of multiblock boiler
     */
    double gTCEuSteamTweaker$getMultiblockBoilerSteamPWaterRatio();
}