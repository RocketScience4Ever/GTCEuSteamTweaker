A small addon mod for GTCEu 1.12.2 to allow users to modify the steam production, steam consumption, and water consumption of GTCEu steam boilers and machines. This mod exists because GTCEu does not natively provide support to change most aspects of steam machines and boilers with its configs or via crafttweaker or groovyscript.

**__Features:__**

*   Independently set the maximum steam output (mB/t) of any single block steam boiler (high and low pressure)
*   Independently set the maximum steam output (mB/t) of any multiblock steam boiler
*   Independently (or collectively) set the EU/mB of steam in any GTCEu single block steam machine (multiblock machines can already do this using vanilla GTCEu config)
*   Independently (or collectively) set multipliers for the recipe duration of recipes in any low pressure steam machine
*   Independently (or collectively) set multipliers for the steam consumption of recipes in any high pressure steam machine
*   Independently (or collectively) set the water consumption for single block boilers (can be constant, or variable based on the amount of steam being generated)

All of the above features can be configured through this mod's config file (gtceusteamtweaker.cfg).

You MAY use this mod in your modpacks on any platform.

You MAY NOT re-host any of the files for this mod outside of a modpack.

**__Installation Methods (You only need to do one of these):__**
- Go to https://www.curseforge.com/minecraft/mc-mods/gtceu-steam-tweaker and install the latest version of the mod into any applicable curseforge profile
- Go to the releases in this github repository, download gtceusteamtweaker-[minecraftVersion]-v[modVersion].jar, and copy it into the ``mods`` folder under the minecraft instance for which you want to install this mod
- Note that CurseForge will only let you publish a modpack if you download the files for all of its mods through CurseForge
- Regardless of which installation method you use you DO NOT want the ``-dev`` or ``-sources`` jar files unless you are a mod developer working in a deobfuscated development environment. If you do not know what that means, don't download the ``-dev`` or ``-sources`` jar files

**__Dependencies:__**

*   GTCEu 2.8.10-beta or later
*   MixinBooter 11.7 or later

_This mod is **NOT** maintained by the GregariousT or the GTCEu team. Do not contact them about issues with this mod. If you encounter a bug, please report it as an issue to the GitHub repository for GTCEu Steam Tweaker._
