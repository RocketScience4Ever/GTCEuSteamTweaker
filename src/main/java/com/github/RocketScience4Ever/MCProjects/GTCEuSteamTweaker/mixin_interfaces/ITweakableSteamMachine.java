package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.mixin_interfaces;

/**Interface to house the methods that allow steam efficiency tweaks to be individual to each machine if desired.
 * <p>Used to apply duck typing to single block steam machines.</p>
 */
public interface ITweakableSteamMachine {
    /**Returns the EU/mB of GTCEu steam for the machine implementing this interface.
     * @return (double) The amount of EU that each mB of GTCEu steam should be worth in this machine
     */
    double gTCEuSteamTweaker$getEUPermB();

    /**Returns the value by which to multiply the duration of recipes for the machine implementing this interface if the machine is a low pressure machine.
     * @return (double) The multiplier to the duration of recipes performed in the low pressure variant of this machine
     */
    double gTCEuSteamTweaker$getLowPressureDurationMultiplier();

    /**Returns the value by which to multiply the steam consumption of recipes for the machine implementing this interface if the machine is a high pressure machine.
     * @return (double) The multiplier to the steam consumption of recipes performed in the high pressure variant of this machine
     */
    double gTCEuSteamTweaker$getHighPressurePowerMultiplier();
}
