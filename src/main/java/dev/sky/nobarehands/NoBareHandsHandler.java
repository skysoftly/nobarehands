package dev.sky.nobarehands;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.List;

@EventBusSubscriber(modid = NoBareHands.MODID)
public class NoBareHandsHandler {

    private enum Result { IGNORE, NONE, MATCH, DENY, SLOW }

    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getEntity();
        BlockState state = event.getLevel().getBlockState(event.getPos());
        ItemStack handItem = player.getMainHandItem();

        if (player.isCreative() || player.isSpectator()) return;
        if (!Config.ENABLED.get()) return;

        Result pickaxe = checkTool(state, handItem,
                Config.PICKAXE_MODE.get(),
                Config.PICKAXE_INCLUDE_TAGS.get(),
                Config.PICKAXE_EXCLUDE_TAGS.get(),
                Config.PICKAXE_INCLUDE_BLOCKS.get(),
                Config.PICKAXE_EXCLUDE_BLOCKS.get(),
                "pickaxe");

        Result axe = checkTool(state, handItem,
                Config.AXE_MODE.get(),
                Config.AXE_INCLUDE_TAGS.get(),
                Config.AXE_EXCLUDE_TAGS.get(),
                Config.AXE_INCLUDE_BLOCKS.get(),
                Config.AXE_EXCLUDE_BLOCKS.get(),
                "axe");

        Result shovel = checkTool(state, handItem,
                Config.SHOVEL_MODE.get(),
                Config.SHOVEL_INCLUDE_TAGS.get(),
                Config.SHOVEL_EXCLUDE_TAGS.get(),
                Config.SHOVEL_INCLUDE_BLOCKS.get(),
                Config.SHOVEL_EXCLUDE_BLOCKS.get(),
                "shovel");

        Result hoe = checkTool(state, handItem,
                Config.HOE_MODE.get(),
                Config.HOE_INCLUDE_TAGS.get(),
                Config.HOE_EXCLUDE_TAGS.get(),
                Config.HOE_INCLUDE_BLOCKS.get(),
                Config.HOE_EXCLUDE_BLOCKS.get(),
                "hoe");

        if (pickaxe == Result.IGNORE || axe == Result.IGNORE
                || shovel == Result.IGNORE || hoe == Result.IGNORE) {
            return;
        }

        if (pickaxe == Result.MATCH || axe == Result.MATCH
                || shovel == Result.MATCH || hoe == Result.MATCH) {
            return;
        }

        boolean relatesToAny = pickaxe != Result.NONE || axe != Result.NONE
                || shovel != Result.NONE || hoe != Result.NONE;

        if (!relatesToAny) return;

        if (pickaxe == Result.DENY || axe == Result.DENY
                || shovel == Result.DENY || hoe == Result.DENY) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        BlockState state = event.getState();
        ItemStack handItem = player.getMainHandItem();

        if (player.isCreative() || player.isSpectator()) return;
        if (!Config.ENABLED.get()) return;

        Result pickaxe = checkTool(state, handItem,
                Config.PICKAXE_MODE.get(),
                Config.PICKAXE_INCLUDE_TAGS.get(),
                Config.PICKAXE_EXCLUDE_TAGS.get(),
                Config.PICKAXE_INCLUDE_BLOCKS.get(),
                Config.PICKAXE_EXCLUDE_BLOCKS.get(),
                "pickaxe");

        Result axe = checkTool(state, handItem,
                Config.AXE_MODE.get(),
                Config.AXE_INCLUDE_TAGS.get(),
                Config.AXE_EXCLUDE_TAGS.get(),
                Config.AXE_INCLUDE_BLOCKS.get(),
                Config.AXE_EXCLUDE_BLOCKS.get(),
                "axe");

        Result shovel = checkTool(state, handItem,
                Config.SHOVEL_MODE.get(),
                Config.SHOVEL_INCLUDE_TAGS.get(),
                Config.SHOVEL_EXCLUDE_TAGS.get(),
                Config.SHOVEL_INCLUDE_BLOCKS.get(),
                Config.SHOVEL_EXCLUDE_BLOCKS.get(),
                "shovel");

        Result hoe = checkTool(state, handItem,
                Config.HOE_MODE.get(),
                Config.HOE_INCLUDE_TAGS.get(),
                Config.HOE_EXCLUDE_TAGS.get(),
                Config.HOE_INCLUDE_BLOCKS.get(),
                Config.HOE_EXCLUDE_BLOCKS.get(),
                "hoe");

        if (pickaxe == Result.IGNORE || axe == Result.IGNORE
                || shovel == Result.IGNORE || hoe == Result.IGNORE) {
            return;
        }

        if (pickaxe == Result.MATCH || axe == Result.MATCH
                || shovel == Result.MATCH || hoe == Result.MATCH) {
            return;
        }

        boolean relatesToAny = pickaxe != Result.NONE || axe != Result.NONE
                || shovel != Result.NONE || hoe != Result.NONE;

        if (!relatesToAny) return;

        float multiplier = 1.0f;

        if (pickaxe == Result.SLOW) {
            multiplier = Math.min(multiplier, (float) (double) Config.PICKAXE_SPEED.get());
        }
        if (axe == Result.SLOW) {
            multiplier = Math.min(multiplier, (float) (double) Config.AXE_SPEED.get());
        }
        if (shovel == Result.SLOW) {
            multiplier = Math.min(multiplier, (float) (double) Config.SHOVEL_SPEED.get());
        }
        if (hoe == Result.SLOW) {
            multiplier = Math.min(multiplier, (float) (double) Config.HOE_SPEED.get());
        }

        if (multiplier < 1.0f) {
            event.setNewSpeed(event.getOriginalSpeed() * multiplier);
        }
    }

    private static Result checkTool(
            BlockState state,
            ItemStack handItem,
            String mode,
            List<? extends String> includeTags,
            List<? extends String> excludeTags,
            List<? extends String> includeBlocks,
            List<? extends String> excludeBlocks,
            String toolType
    ) {
        String blockId = BuiltInRegistries.BLOCK.getKey(state.getBlock()).toString();

        for (String tag : excludeTags) {
            if (state.is(BlockTags.create(ResourceLocation.parse(tag)))) {
                return Result.IGNORE;
            }
        }
        if (excludeBlocks.contains(blockId)) {
            return Result.IGNORE;
        }

        boolean inTags = false;
        for (String tag : includeTags) {
            if (state.is(BlockTags.create(ResourceLocation.parse(tag)))) {
                inTags = true;
                break;
            }
        }

        boolean inBlocks = includeBlocks.contains(blockId);

        if (!inTags && !inBlocks) {
            return Result.NONE;
        }

        boolean hasCorrectTool = switch (toolType) {
            case "pickaxe" -> handItem.getItem() instanceof PickaxeItem;
            case "axe" -> handItem.getItem() instanceof AxeItem;
            case "shovel" -> handItem.getItem() instanceof ShovelItem;
            case "hoe" -> handItem.getItem() instanceof HoeItem;
            default -> false;
        };

        if (hasCorrectTool) {
            return Result.MATCH;
        }

        return "DENY".equals(mode) ? Result.DENY : Result.SLOW;
    }
}