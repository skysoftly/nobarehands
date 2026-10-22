package dev.sky.nobarehands;

import net.neoforged.neoforge.common.ModConfigSpec;
import java.util.List;

public class Config {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.BooleanValue ENABLED;

    // КИРКА
    public static final ModConfigSpec.ConfigValue<String> PICKAXE_MODE;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> PICKAXE_INCLUDE_TAGS;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> PICKAXE_EXCLUDE_TAGS;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> PICKAXE_INCLUDE_BLOCKS;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> PICKAXE_EXCLUDE_BLOCKS;
    public static final ModConfigSpec.DoubleValue PICKAXE_SPEED;

    // ТОПОР
    public static final ModConfigSpec.ConfigValue<String> AXE_MODE;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> AXE_INCLUDE_TAGS;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> AXE_EXCLUDE_TAGS;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> AXE_INCLUDE_BLOCKS;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> AXE_EXCLUDE_BLOCKS;
    public static final ModConfigSpec.DoubleValue AXE_SPEED;

    // ЛОПАТА
    public static final ModConfigSpec.ConfigValue<String> SHOVEL_MODE;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> SHOVEL_INCLUDE_TAGS;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> SHOVEL_EXCLUDE_TAGS;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> SHOVEL_INCLUDE_BLOCKS;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> SHOVEL_EXCLUDE_BLOCKS;
    public static final ModConfigSpec.DoubleValue SHOVEL_SPEED;

    // МОТЫГА
    public static final ModConfigSpec.ConfigValue<String> HOE_MODE;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> HOE_INCLUDE_TAGS;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> HOE_EXCLUDE_TAGS;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> HOE_INCLUDE_BLOCKS;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> HOE_EXCLUDE_BLOCKS;
    public static final ModConfigSpec.DoubleValue HOE_SPEED;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.comment("NoBareHands - полная настройка ограничений для инструментов")
                .push("general");

        ENABLED = builder
                .comment("Включить/выключить мод")
                .define("enabled", true);

        builder.pop();

        // ===== КИРКА =====
        builder.push("pickaxe");
        PICKAXE_MODE = builder
                .comment("Режим: 'DENY' = нельзя без кирки, 'SLOW' = медленно без кирки")
                .define("mode", "DENY");
        PICKAXE_INCLUDE_TAGS = builder
                .comment("Теги, которые требуют кирку")
                .defineList("includeTags", List.of("minecraft:mineable/pickaxe"), obj -> obj instanceof String);
        PICKAXE_EXCLUDE_TAGS = builder
                .comment("Теги, которые исключаются из правила")
                .defineList("excludeTags", List.of("minecraft:replaceable"), obj -> obj instanceof String);
        PICKAXE_INCLUDE_BLOCKS = builder
                .comment("Конкретные блоки, которые требуют кирку")
                .defineList("includeBlocks", List.of(), obj -> obj instanceof String);
        PICKAXE_EXCLUDE_BLOCKS = builder
                .comment("Конкретные блоки, которые исключаются из правила")
                .defineList("excludeBlocks", List.of(), obj -> obj instanceof String);
        PICKAXE_SPEED = builder
                .comment("Скорость для режима SLOW (0.0 - 1.0)")
                .defineInRange("speed", 0.25, 0.0, 1.0);
        builder.pop();

        // ===== ТОПОР =====
        builder.push("axe");
        AXE_MODE = builder
                .comment("Режим: 'DENY' = нельзя без топора, 'SLOW' = медленно без топора")
                .define("mode", "DENY");
        AXE_INCLUDE_TAGS = builder
                .comment("Теги, которые требуют топор")
                .defineList("includeTags", List.of("minecraft:mineable/axe"), obj -> obj instanceof String);
        AXE_EXCLUDE_TAGS = builder
                .comment("Теги, которые исключаются из правила")
                .defineList("excludeTags", List.of("minecraft:replaceable", "minecraft:leaves"), obj -> obj instanceof String);
        AXE_INCLUDE_BLOCKS = builder
                .comment("Конкретные блоки, которые требуют топор")
                .defineList("includeBlocks", List.of(), obj -> obj instanceof String);
        AXE_EXCLUDE_BLOCKS = builder
                .comment("Конкретные блоки, которые исключаются из правила")
                .defineList("excludeBlocks", List.of("minecraft:bamboo"), obj -> obj instanceof String);
        AXE_SPEED = builder
                .comment("Скорость для режима SLOW (0.0 - 1.0)")
                .defineInRange("speed", 0.25, 0.0, 1.0);
        builder.pop();

        // ===== ЛОПАТА =====
        builder.push("shovel");
        SHOVEL_MODE = builder
                .comment("Режим: 'DENY' = нельзя без лопаты, 'SLOW' = медленно без лопаты")
                .define("mode", "SLOW");
        SHOVEL_INCLUDE_TAGS = builder
                .comment("Теги, которые требуют лопату")
                .defineList("includeTags", List.of("minecraft:mineable/shovel"), obj -> obj instanceof String);
        SHOVEL_EXCLUDE_TAGS = builder
                .comment("Теги, которые исключаются из правила")
                .defineList("excludeTags", List.of("minecraft:replaceable"), obj -> obj instanceof String);
        SHOVEL_INCLUDE_BLOCKS = builder
                .comment("Конкретные блоки, которые требуют лопату")
                .defineList("includeBlocks", List.of(), obj -> obj instanceof String);
        SHOVEL_EXCLUDE_BLOCKS = builder
                .comment("Конкретные блоки, которые исключаются из правила")
                .defineList("excludeBlocks", List.of(), obj -> obj instanceof String);
        SHOVEL_SPEED = builder
                .comment("Скорость для режима SLOW (0.0 - 1.0)")
                .defineInRange("speed", 0.25, 0.0, 1.0);
        builder.pop();

        // ===== МОТЫГА =====
        builder.push("hoe");
        HOE_MODE = builder
                .comment("Режим: 'DENY' = нельзя без мотыги, 'SLOW' = медленно без мотыги")
                .define("mode", "SLOW");
        HOE_INCLUDE_TAGS = builder
                .comment("Теги, которые требуют мотыгу")
                .defineList("includeTags", List.of("minecraft:mineable/hoe"), obj -> obj instanceof String);
        HOE_EXCLUDE_TAGS = builder
                .comment("Теги, которые исключаются из правила")
                .defineList("excludeTags", List.of("minecraft:replaceable"), obj -> obj instanceof String);
        HOE_INCLUDE_BLOCKS = builder
                .comment("Конкретные блоки, которые требуют мотыгу")
                .defineList("includeBlocks", List.of(), obj -> obj instanceof String);
        HOE_EXCLUDE_BLOCKS = builder
                .comment("Конкретные блоки, которые исключаются из правила")
                .defineList("excludeBlocks", List.of(), obj -> obj instanceof String);
        HOE_SPEED = builder
                .comment("Скорость для режима SLOW (0.0 - 1.0)")
                .defineInRange("speed", 0.5, 0.0, 1.0);
        builder.pop();

        SPEC = builder.build();
    }
}