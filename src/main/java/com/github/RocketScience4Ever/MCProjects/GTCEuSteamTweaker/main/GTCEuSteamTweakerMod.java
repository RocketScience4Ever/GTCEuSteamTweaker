package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.main;

import gregtech.common.metatileentities.steam.SteamMiner;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

/**Mod class. Right now only exists to make Forge recognize this mod as a mod
 */
@Mod(modid = "gtceusteamtweaker", name = "GTCEu Steam Tweaker", version = "0.1.0", dependencies = "required:gregtech@[2.8.10-beta,)")
public class GTCEuSteamTweakerMod {
    public GTCEuSteamTweakerMod() {
        MinecraftForge.EVENT_BUS.register(new SteamMiner(null,1,1,0));
    }
}