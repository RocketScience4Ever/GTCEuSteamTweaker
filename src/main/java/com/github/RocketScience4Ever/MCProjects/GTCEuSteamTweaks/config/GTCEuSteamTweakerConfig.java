package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaks.config;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.gtceusteamtweaker.Tags;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Tags.MOD_ID, category = "tweaks")
public class GTCEuSteamTweakerConfig {
    @Config.Name(value = "single_block_boiler_output")
    public static SingleblockBoilerOutputConfig singleblockBoilerOutputConfig = new SingleblockBoilerOutputConfig();

    public static class SingleblockBoilerOutputConfig {
        //Single block steam boiler steam production
        @Config.Name(value = "1: Tweak Singleblock Boiler Steam Output")
        @Config.Comment("Should GTCEu Steam Tweaker enable tweaks to the steam output of the single block steam boilers? [DEFAULT = true]")
        public boolean tweakSingleblockBoilerSteam = true;

        @Config.Name(value = "2: Low Pressure Solid Fuel Steam Output")
        @Config.Comment("Steam output of the low pressure solid fuel boiler in mB/t [DEFAULT = 6]")
        public int lowPressureSolidFuelSteam = 6;

        @Config.Name(value = "3: High Pressure Solid Fuel Steam Output")
        @Config.Comment("Steam output of the high pressure solid fuel boiler in mB/t [DEFAULT = 15.0]")
        public int highPressureSolidFuelSteam = 15;

        @Config.Name(value = "4: Low Pressure Liquid Fuel Steam Output")
        @Config.Comment("Steam output of the low pressure liquid fuel boiler in mB/t [DEFAULT = 12.0]")
        public int lowPressureLiquidFuelSteam = 12;

        @Config.Name(value = "5: High Pressure Liquid Fuel Steam Output")
        @Config.Comment("Steam output of the high pressure liquid fuel boiler in mB/t [DEFAULT = 30.0]")
        public int highPressureLiquidFuelSteam = 30;

        @Config.Name(value = "6: Low Pressure Solar Steam Output")
        @Config.Comment("Steam output of the low pressure solar boiler in mB/t [DEFAULT = 6.0]")
        public int lowPressureSolarSteam = 6;

        @Config.Name(value = "7: High Pressure Solar Steam Output")
        @Config.Comment("Steam output of the high pressure solar boiler in mB/t [DEFAULT = 18.0]")
        public int highPressureSolarSteam = 18;
    }

    @Config.Name(value = "multi_block_boiler_output")
    public static MultiblockBoilerOutputConfig multiblockBoilerOutputConfig = new MultiblockBoilerOutputConfig();

    public static class MultiblockBoilerOutputConfig {
        //Multiblock steam boiler steam production
        @Config.Name(value = "1: Tweak Multiblock Boiler Steam Output")
        @Config.Comment("Should GTCEu Steam Tweaker enable tweaks to the steam output of the multiblock steam boilers? [DEFAULT = true]")
        public boolean tweakMultiblockBoilerSteam = true;

        @Config.Name(value = "2: Large Bronze Boiler Steam Output")
        @Config.Comment("Steam output of the large bronze boiler in mB/t [DEFAULT = 800]")
        public int bronzeMultiBoilerSteam = 800;

        @Config.Name(value = "3: Large Steel Boiler Steam Output")
        @Config.Comment("Steam output of the large steel boiler in mB/t [DEFAULT = 1800]")
        public int steelMultiBoilerSteam = 1800;

        @Config.Name(value = "4: Large Titanium Boiler Steam Output")
        @Config.Comment("Steam output of the large titanium boiler in mB/t [DEFAULT = 3200]")
        public int titaniumMultiBoilerSteam = 3200;

        @Config.Name(value = "5: Large Tungstensteel Boiler Steam Output")
        @Config.Comment("Steam output of the large tungstensteel boiler in mB/t [DEFAULT = 6400]")
        public int tungstensteelMultiBoilerSteam = 6400;
    }

    @Mod.EventBusSubscriber(modid = Tags.MOD_ID)
    public static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Tags.MOD_ID)) {
                ConfigManager.sync(Tags.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}