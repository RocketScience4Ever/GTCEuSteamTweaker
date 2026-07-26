package com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.config;

import com.github.RocketScience4Ever.MCProjects.GTCEuSteamTweaker.gtceusteamtweaker.Tags;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**A class which uses Forge's {@link Config} annotation to give this mod access to the values from the mod's configuration file.
 */
@Config(modid = Tags.MOD_ID, category = "tweaks")
public class GTCEuSteamTweakerConfig {
    @Config.Name("single_block_boiler_output")
    public static SingleblockBoilerOutputConfig singleblockBoilerOutputConfig = new SingleblockBoilerOutputConfig();

    public static class SingleblockBoilerOutputConfig {
        //Single block steam boiler steam production
        @Config.Name("1: Tweak Single Block Boiler Steam Output")
        @Config.Comment("Should GTCEu Steam Tweaker enable tweaks to the steam output of the single block steam boilers? [DEFAULT = true]")
        public boolean tweakSingleblockBoilerSteam = true;

        @Config.Name("2: Low Pressure Solid Fuel Steam Output")
        @Config.Comment("Steam output of the low pressure solid fuel boiler in mB/t [DEFAULT = 6]")
        public int lowPressureSolidFuelSteam = 6;

        @Config.Name("3: High Pressure Solid Fuel Steam Output")
        @Config.Comment("Steam output of the high pressure solid fuel boiler in mB/t [DEFAULT = 15.0]")
        public int highPressureSolidFuelSteam = 15;

        @Config.Name("4: Low Pressure Liquid Fuel Steam Output")
        @Config.Comment("Steam output of the low pressure liquid fuel boiler in mB/t [DEFAULT = 12.0]")
        public int lowPressureLiquidFuelSteam = 12;

        @Config.Name("5: High Pressure Liquid Fuel Steam Output")
        @Config.Comment("Steam output of the high pressure liquid fuel boiler in mB/t [DEFAULT = 30.0]")
        public int highPressureLiquidFuelSteam = 30;

        @Config.Name("6: Low Pressure Solar Steam Output")
        @Config.Comment("Steam output of the low pressure solar boiler in mB/t [DEFAULT = 6.0]")
        public int lowPressureSolarSteam = 6;

        @Config.Name("7: High Pressure Solar Steam Output")
        @Config.Comment("Steam output of the high pressure solar boiler in mB/t [DEFAULT = 18.0]")
        public int highPressureSolarSteam = 18;
    }

    @Config.Name("multi_block_boiler_output")
    public static MultiblockBoilerOutputConfig multiblockBoilerOutputConfig = new MultiblockBoilerOutputConfig();

    public static class MultiblockBoilerOutputConfig {
        //Multiblock steam boiler steam production
        @Config.Name("1: Tweak Multiblock Boiler Steam Output")
        @Config.Comment("Should GTCEu Steam Tweaker enable tweaks to the steam output of the multiblock steam boilers? [DEFAULT = true]")
        public boolean tweakMultiblockBoilerSteam = true;

        @Config.Name("2: Large Bronze Boiler Steam Output")
        @Config.Comment("Steam output of the large bronze boiler in mB/t [DEFAULT = 800]")
        public int bronzeMultiBoilerSteam = 800;

        @Config.Name("3: Large Steel Boiler Steam Output")
        @Config.Comment("Steam output of the large steel boiler in mB/t [DEFAULT = 1800]")
        public int steelMultiBoilerSteam = 1800;

        @Config.Name("4: Large Titanium Boiler Steam Output")
        @Config.Comment("Steam output of the large titanium boiler in mB/t [DEFAULT = 3200]")
        public int titaniumMultiBoilerSteam = 3200;

        @Config.Name("5: Large Tungstensteel Boiler Steam Output")
        @Config.Comment("Steam output of the large tungstensteel boiler in mB/t [DEFAULT = 6400]")
        public int tungstensteelMultiBoilerSteam = 6400;
    }

    @Config.Name("single_block_machine_efficiency")
    public static SingleblockMachineEfficiencyConfig singleblockMachineEfficiency = new SingleblockMachineEfficiencyConfig();

    public static class SingleblockMachineEfficiencyConfig {
        @Config.Name("1: Tweak Single Block Steam Machine Efficiency")
        @Config.Comment("Should GTCEu Steam Tweaker enable tweaks to the recipe duration and steam consumption for single block steam machines? [DEFAULT = true]")
        public boolean tweakSingleblockMachineEfficiency = true;

        @Config.Name("2: Steam Miner mB/t")
        @Config.Comment("The amount of GTCEu steam that the STEAM MINER should consume in mB/t [DEFAULT = 16]")
        @Config.RequiresWorldRestart
        public int minermBPt = 16;

        @Config.Name("machine_EU_per_mB_ratios")
        public SingleblockEUPerMbConfig singleblockEUPmBConfig = new SingleblockEUPerMbConfig();

        public static class SingleblockEUPerMbConfig {
            @Config.Name("A: Default Single Block Steam Machine EU/mB")
            @Config.Comment("""
                    The amount of EU each mB of GTCEu steam is worth in a steam machine [DEFAULT = 1.0]\
                    The mB/t of steam that a steam machine recipe will consume is equal to the EU/t of the recipe in JEI divided by this value.""")
            public double singleblockMachineEUPmBMaster = 1.0;

            @Config.Name("B: Steam Alloy Smelter EU/mB")
            @Config.Comment("""
                    The amount of EU each mB of GTCEu steam is worth in a STEAM ALLOY SMELTER [DEFAULT = -1.0]\
                    The mB/t of steam that a STEAM ALLOY SMELTER recipe will consume is equal to the EU/t of the recipe in JEI divided by this value.\
                    If this value is greater than or equal to 0, then this value will override the Default Single Block Steam Machine EU/mB for the STEAM ALLOY SMELTER ONLY.\
                    If this value is less than or equal to 0, then the Default Single Block Steam Machine EU/mB will be used.""")
            public double alloySmelterEUPmB = -1.0;

            @Config.Name("C: Steam Compressor EU/mB")
            @Config.Comment("""
                    The amount of EU each mB of GTCEu steam is worth in a STEAM COMPRESSOR [DEFAULT = -1.0]\
                    The mB/t of steam that a STEAM COMPRESSOR recipe will consume is equal to the EU/t of the recipe in JEI divided by this value.\
                    If this value is greater than or equal to 0, then this value will override the Default Single Block Steam Machine EU/mB for the STEAM COMPRESSOR ONLY.\
                    If this value is less than or equal to 0, then the Default Single Block Steam Machine EU/mB will be used.""")
            public double compressorEUPmB = -1.0;

            @Config.Name("D: Steam Extractor EU/mB")
            @Config.Comment("""
                    The amount of EU each mB of GTCEu steam is worth in a STEAM EXTRACTOR [DEFAULT = -1.0]\
                    The mB/t of steam that a STEAM EXTRACTOR recipe will consume is equal to the EU/t of the recipe in JEI divided by this value.\
                    If this value is greater than or equal to 0, then this value will override the Default Single Block Steam Machine EU/mB for the STEAM EXTRACTOR ONLY.\
                    If this value is less than or equal to 0, then the Default Single Block Steam Machine EU/mB will be used.""")
            public double extractorEUPmB = -1.0;

            @Config.Name("E: Steam Furnace EU/mB")
            @Config.Comment("""
                    The amount of EU each mB of GTCEu steam is worth in a SINGLE BLOCK STEAM FURNACE [DEFAULT = -1.0]\
                    The mB/t of steam that a SINGLE BLOCK STEAM FURNACE recipe will consume is equal to the EU/t of the recipe in JEI divided by this value.\
                    If this value is greater than or equal to 0, then this value will override the Default Single Block Steam Machine EU/mB for the SINGLE BLOCK STEAM FURNACE ONLY.\
                    If this value is less than or equal to 0, then the Default Single Block Steam Machine EU/mB will be used.""")
            public double furnaceEUPmB = -1.0;

            @Config.Name("F: Steam Forge Hammer EU/mB")
            @Config.Comment("""
                    The amount of EU each mB of GTCEu steam is worth in a STEAM FORGE HAMMER [DEFAULT = -1.0]\
                    The mB/t of steam that a STEAM FORGE HAMMER recipe will consume is equal to the EU/t of the recipe in JEI divided by this value.\
                    If this value is greater than or equal to 0, then this value will override the Default Single Block Steam Machine EU/mB for the STEAM FORGE HAMMER ONLY.\
                    If this value is less than or equal to 0, then the Default Single Block Steam Machine EU/mB will be used.""")
            public double forgeHammerEUPmB = -1.0;

            @Config.Name("G: Steam Macerator EU/mB")
            @Config.Comment("""
                    The amount of EU each mB of GTCEu steam is worth in a SINGLE BLOCK STEAM MACERATOR [DEFAULT = -1.0]\
                    The mB/t of steam that a SINGLE BLOCK STEAM MACERATOR recipe will consume is equal to the EU/t of the recipe in JEI divided by this value.\
                    If this value is greater than or equal to 0, then this value will override the Default Single Block Steam Machine EU/mB for the SINGLE BLOCK STEAM MACERATOR ONLY.\
                    If this value is less than or equal to 0, then the Default Single Block Steam Machine EU/mB will be used.""")
            public double maceratorEUPmB = -1.0;

            @Config.Name("H: Steam Rock Breaker EU/mB")
            @Config.Comment("""
                    The amount of EU each mB of GTCEu steam is worth in a STEAM ROCK BREAKER [DEFAULT = -1.0]\
                    The mB/t of steam that a STEAM ROCK BREAKER recipe will consume is equal to the EU/t of the recipe in JEI divided by this value.\
                    If this value is greater than or equal to 0, then this value will override the Default Single Block Steam Machine EU/mB for the STEAM ROCK BREAKER ONLY.\
                    If this value is less than or equal to 0, then the Default Single Block Steam Machine EU/mB will be used.""")
            public double rockBreakerEUPmB = -1.0;
        }

        @Config.Name("low_pressure_machine_duration_multipliers")
        public SingleblockLowPressureDurationMultiplierConfig singleblockLowPressureDurationMultiplierConfig = new SingleblockLowPressureDurationMultiplierConfig();

        public static class SingleblockLowPressureDurationMultiplierConfig {
            @Config.Name("A: Default Low Pressure Machine Recipe Duration Multiplier")
            @Config.Comment("The duration of a recipe (as listed in JEI) will be multiplied by this value when the recipe is performed in a low pressure steam machine [DEFAULT = 2.0]")
            public double lowPressureMachineRecipeDurationMultiplierMaster = 2.0;

            @Config.Name("B: Low Pressure Alloy Smelter Recipe Duration Multiplier")
            @Config.Comment("""
                    The duration of a recipe (as listed in JEI) in the STEAM ALLOY SMELTER will be multiplied by this value when the recipe is performed in a low pressure steam alloy smelter [DEFAULT = -1.0]\
                    If this value is greater than or equal to 0, then this value will override the Default Low Pressure Machine Recipe Duration Multiplier for the STEAM ALLOY SMELTER ONLY.\
                    If this value is less than or equal to 0, then the Default Low Pressure Machine Recipe Duration Multiplier will be used.""")
            public double lowPressureAlloySmelterRecipeDurationMultiplier = -1.0;

            @Config.Name("C: Low Pressure Compressor Recipe Duration Multiplier")
            @Config.Comment("""
                    The duration of a recipe (as listed in JEI) in the STEAM COMPRESSOR will be multiplied by this value when the recipe is performed in a low pressure steam compressor [DEFAULT = -1.0]\
                    If this value is greater than or equal to 0, then this value will override the Default Low Pressure Machine Recipe Duration Multiplier for the STEAM COMPRESSOR ONLY.\
                    If this value is less than or equal to 0, then the Default Low Pressure Machine Recipe Duration Multiplier will be used.""")
            public double lowPressureCompressorRecipeDurationMultiplier = -1.0;

            @Config.Name("D: Low Pressure Extractor Recipe Duration Multiplier")
            @Config.Comment("""
                    The duration of a recipe (as listed in JEI) in the STEAM EXTRACTOR will be multiplied by this value when the recipe is performed in a low pressure steam extractor [DEFAULT = -1.0]\
                    If this value is greater than or equal to 0, then this value will override the Default Low Pressure Machine Recipe Duration Multiplier for the STEAM EXTRACTOR ONLY.\
                    If this value is less than or equal to 0, then the Default Low Pressure Machine Recipe Duration Multiplier will be used.""")
            public double lowPressureExtractorRecipeDurationMultiplier = -1.0;

            @Config.Name("E: Low Pressure Furnace Recipe Duration Multiplier")
            @Config.Comment("""
                    The duration of a recipe (as listed in JEI) in the SINGLE BLOCK STEAM FURNACE will be multiplied by this value when the recipe is performed in a low pressure steam furnace [DEFAULT = -1.0]\
                    If this value is greater than or equal to 0, then this value will override the Default Low Pressure Machine Recipe Duration Multiplier for the SINGLE BLOCK STEAM FURNACE ONLY.\
                    If this value is less than or equal to 0, then the Default Low Pressure Machine Recipe Duration Multiplier will be used.""")
            public double lowPressureFurnaceRecipeDurationMultiplier = -1.0;

            @Config.Name("F: Low Pressure Forge Hammer Recipe Duration Multiplier")
            @Config.Comment("""
                    The duration of a recipe (as listed in JEI) in the STEAM FORGE HAMMER will be multiplied by this value when the recipe is performed in a low pressure steam forge hammer [DEFAULT = -1.0]\
                    If this value is greater than or equal to 0, then this value will override the Default Low Pressure Machine Recipe Duration Multiplier for the STEAM FORGE HAMMER ONLY.\
                    If this value is less than or equal to 0, then the Default Low Pressure Machine Recipe Duration Multiplier will be used.""")
            public double lowPressureForgeHammerRecipeDurationMultiplier = -1.0;

            @Config.Name("G: Low Pressure Macerator Recipe Duration Multiplier")
            @Config.Comment("""
                    The duration of a recipe (as listed in JEI) in the SINGLE BLOCK STEAM MACERATOR will be multiplied by this value when the recipe is performed in a low pressure steam macerator [DEFAULT = -1.0]\
                    If this value is greater than or equal to 0, then this value will override the Default Low Pressure Machine Recipe Duration Multiplier for the SINGLE BLOCK STEAM MACERATOR ONLY.\
                    If this value is less than or equal to 0, then the Default Low Pressure Machine Recipe Duration Multiplier will be used.""")
            public double lowPressureMaceratorRecipeDurationMultiplier = -1.0;

            @Config.Name("H: Low Pressure Rock Breaker Recipe Duration Multiplier")
            @Config.Comment("""
                    The duration of a recipe (as listed in JEI) in the STEAM ROCK BREAKER will be multiplied by this value when the recipe is performed in a low pressure steam rock breaker [DEFAULT = -1.0]\
                    If this value is greater than or equal to 0, then this value will override the Default Low Pressure Machine Recipe Duration Multiplier for the STEAM ROCK BREAKER ONLY.\
                    If this value is less than or equal to 0, then the Default Low Pressure Machine Recipe Duration Multiplier will be used.""")
            public double lowPressureRockBreakerRecipeDurationMultiplier = -1.0;
        }

        @Config.Name("high_pressure_machine_power_multipliers")
        public SingleblockHighPressurePowerMultiplierConfig singleblockHighPressurePowerMultiplierConfig = new SingleblockHighPressurePowerMultiplierConfig();

        public static class SingleblockHighPressurePowerMultiplierConfig {
            @Config.Name("A: Default High Pressure Machine Recipe Power Multiplier")
            @Config.Comment("The mB/t of steam consumed by a recipe will be multiplied by this value when the recipe is performed in a high pressure steam machine [DEFAULT = 2.0]")
            public double highPressureMachineRecipePowerMultiplierMaster = 2.0;

            @Config.Name("B: High Pressure Alloy Smelter Recipe Power Multiplier")
            @Config.Comment("""
                    The mB/t of steam consumed by a recipe in the STEAM ALLOY SMELTER will be multiplied by this value when the recipe is performed in a high pressure steam alloy smelter [DEFAULT = -1.0]\
                    If this value is greater than or equal to 0, then this value will override the Default High Pressure Machine Recipe Power Multiplier for the STEAM ALLOY SMELTER ONLY.\
                    If this value is less than or equal to 0, then the Default High Pressure Machine Recipe Power Multiplier will be used.""")
            public double highPressureAlloySmelterRecipePowerMultiplier = -1.0;

            @Config.Name("C: High Pressure Compressor Recipe Power Multiplier")
            @Config.Comment("""
                    The mB/t of steam consumed by a recipe in the STEAM COMPRESSOR will be multiplied by this value when the recipe is performed in a high pressure steam compressor [DEFAULT = -1.0]\
                    If this value is greater than or equal to 0, then this value will override the Default High Pressure Machine Recipe Power Multiplier for the STEAM COMPRESSOR ONLY.\
                    If this value is less than or equal to 0, then the Default High Pressure Machine Recipe Power Multiplier will be used.""")
            public double highPressureCompressorRecipePowerMultiplier = -1.0;

            @Config.Name("D: High Pressure Extractor Recipe Power Multiplier")
            @Config.Comment("""
                    The mB/t of steam consumed by a recipe in the STEAM EXTRACTOR will be multiplied by this value when the recipe is performed in a high pressure steam extractor [DEFAULT = -1.0]\
                    If this value is greater than or equal to 0, then this value will override the Default High Pressure Machine Recipe Power Multiplier for the STEAM EXTRACTOR ONLY.\
                    If this value is less than or equal to 0, then the Default High Pressure Machine Recipe Power Multiplier will be used.""")
            public double highPressureExtractorRecipePowerMultiplier = -1.0;

            @Config.Name("E: High Pressure Furnace Recipe Power Multiplier")
            @Config.Comment("""
                    The mB/t of steam consumed by a recipe in the SINGLE BLOCK STEAM FURNACE will be multiplied by this value when the recipe is performed in a high pressure steam furnace [DEFAULT = -1.0]\
                    If this value is greater than or equal to 0, then this value will override the Default High Pressure Machine Recipe Power Multiplier for the SINGLE BLOCK STEAM FURNACE ONLY.\
                    If this value is less than or equal to 0, then the Default High Pressure Machine Recipe Power Multiplier will be used.""")
            public double highPressureFurnaceRecipePowerMultiplier = -1.0;

            @Config.Name("F: High Pressure Forge Hammer Recipe Power Multiplier")
            @Config.Comment("""
                    The mB/t of steam consumed by a recipe in the STEAM FORGE HAMMER will be multiplied by this value when the recipe is performed in a high pressure steam forge hammer [DEFAULT = -1.0]\
                    If this value is greater than or equal to 0, then this value will override the Default High Pressure Machine Recipe Power Multiplier for the STEAM FORGE HAMMER ONLY.\
                    If this value is less than or equal to 0, then the Default High Pressure Machine Recipe Power Multiplier will be used.""")
            public double highPressureForgeHammerRecipePowerMultiplier = -1.0;

            @Config.Name("G: High Pressure Macerator Recipe Power Multiplier")
            @Config.Comment("""
                    The mB/t of steam consumed by a recipe in the SINGLE BLOCK STEAM MACERATOR will be multiplied by this value when the recipe is performed in a high pressure steam macerator [DEFAULT = -1.0]\
                    If this value is greater than or equal to 0, then this value will override the Default High Pressure Machine Recipe Power Multiplier for the SINGLE BLOCK STEAM MACERATOR ONLY.\
                    If this value is less than or equal to 0, then the Default High Pressure Machine Recipe Power Multiplier will be used.""")
            public double highPressureMaceratorRecipePowerMultiplier = -1.0;

            @Config.Name("H: High Pressure Rock Breaker Recipe Power Multiplier")
            @Config.Comment("""
                    The mB/t of steam consumed by a recipe in the STEAM ROCK BREAKER will be multiplied by this value when the recipe is performed in a high pressure steam rock breaker [DEFAULT = -1.0]\
                    If this value is greater than or equal to 0, then this value will override the Default High Pressure Machine Recipe Power Multiplier for the STEAM ROCK BREAKER ONLY.\
                    If this value is less than or equal to 0, then the Default High Pressure Machine Recipe Power Multiplier will be used.""")
            public double highPressureRockBreakerRecipePowerMultiplier = -1.0;
        }
    }

    //Allow changes to the mod's config to take effect without restarting Minecraft
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