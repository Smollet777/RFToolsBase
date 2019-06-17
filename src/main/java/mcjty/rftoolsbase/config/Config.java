package mcjty.rftoolsbase.config;


import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {

    private static final Builder SERVER_BUILDER = new Builder();
//    private static final Builder CLIENT_BUILDER = new Builder();

    public static final ForgeConfigSpec SERVER_CONFIG;
//    public static final ForgeConfigSpec CLIENT_CONFIG;


    public static String CATEGORY_WORLDGEN = "worldgen";

    public static ForgeConfigSpec.IntValue OVERWORLD_ORE_CHANCES;
    public static ForgeConfigSpec.IntValue OVERWORLD_ORE_VEINSIZE;
    public static ForgeConfigSpec.IntValue OVERWORLD_ORE_MINY;
    public static ForgeConfigSpec.IntValue OVERWORLD_ORE_MAXY;

    public static ForgeConfigSpec.IntValue NETHER_ORE_CHANCES;
    public static ForgeConfigSpec.IntValue NETHER_ORE_VEINSIZE;
    public static ForgeConfigSpec.IntValue NETHER_ORE_MINY;
    public static ForgeConfigSpec.IntValue NETHER_ORE_MAXY;

    public static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }

    static {
        SERVER_BUILDER.comment("Dimensional shard ore generation").push(CATEGORY_WORLDGEN);

        OVERWORLD_ORE_CHANCES = SERVER_BUILDER
                .comment("Number of times to try generate the ore (set to 0 to disable)")
                .defineInRange("overworldOreChances", 0, 0, 256);
        OVERWORLD_ORE_VEINSIZE = SERVER_BUILDER
                .comment("Max size of veins")
                .defineInRange("overworldOreVeinsize", 8, 1, 256);
        OVERWORLD_ORE_MINY = SERVER_BUILDER
                .comment("Min height")
                .defineInRange("overworldOreMin", 2, 0, 256);
        OVERWORLD_ORE_MAXY = SERVER_BUILDER
                .comment("Max height")
                .defineInRange("overworldOreMax", 40, 0, 256);

        NETHER_ORE_CHANCES = SERVER_BUILDER
                .comment("Number of times to try generate the ore (set to 0 to disable)")
                .defineInRange("netherOreChances", 8, 0, 256);
        NETHER_ORE_VEINSIZE = SERVER_BUILDER
                .comment("Max size of veins")
                .defineInRange("netherOreVeinsize", 8, 1, 256);
        NETHER_ORE_MINY = SERVER_BUILDER
                .comment("Min height")
                .defineInRange("netherOreMin", 2, 0, 256);
        NETHER_ORE_MAXY = SERVER_BUILDER
                .comment("Max height")
                .defineInRange("netherOreMax", 40, 0, 256);

        SERVER_BUILDER.pop();

        SERVER_CONFIG = SERVER_BUILDER.build();
    }

}