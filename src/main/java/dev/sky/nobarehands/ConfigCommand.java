package dev.sky.nobarehands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class ConfigCommand {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(Commands.literal("nobarehands")
                .requires(src -> src.hasPermission(2))
                .then(Commands.literal("set")
                        .then(Commands.literal("pickaxe")
                                .then(Commands.literal("mode")
                                        .then(Commands.argument("value", StringArgumentType.word())
                                                .suggests((ctx, b) -> { b.suggest("DENY"); b.suggest("SLOW"); return b.buildFuture(); })
                                                .executes(ctx -> {
                                                    String v = StringArgumentType.getString(ctx, "value").toUpperCase();
                                                    if (!v.equals("DENY") && !v.equals("SLOW")) {
                                                        ctx.getSource().sendFailure(Component.literal("Mode must be DENY or SLOW"));
                                                        return 0;
                                                    }
                                                    Config.PICKAXE_MODE.set(v);
                                                    Config.SPEC.save();
                                                    ctx.getSource().sendSuccess(() -> Component.literal("pickaxe mode = " + v), true);
                                                    return 1;
                                                })))
                                .then(Commands.literal("speed")
                                        .then(Commands.argument("value", DoubleArgumentType.doubleArg(0.0, 1.0))
                                                .executes(ctx -> {
                                                    double v = DoubleArgumentType.getDouble(ctx, "value");
                                                    Config.PICKAXE_SPEED.set(v);
                                                    Config.SPEC.save();
                                                    ctx.getSource().sendSuccess(() -> Component.literal("pickaxe speed = " + v), true);
                                                    return 1;
                                                }))))
                        .then(Commands.literal("axe")
                                .then(Commands.literal("mode")
                                        .then(Commands.argument("value", StringArgumentType.word())
                                                .suggests((ctx, b) -> { b.suggest("DENY"); b.suggest("SLOW"); return b.buildFuture(); })
                                                .executes(ctx -> {
                                                    String v = StringArgumentType.getString(ctx, "value").toUpperCase();
                                                    if (!v.equals("DENY") && !v.equals("SLOW")) {
                                                        ctx.getSource().sendFailure(Component.literal("Mode must be DENY or SLOW"));
                                                        return 0;
                                                    }
                                                    Config.AXE_MODE.set(v);
                                                    Config.SPEC.save();
                                                    ctx.getSource().sendSuccess(() -> Component.literal("axe mode = " + v), true);
                                                    return 1;
                                                })))
                                .then(Commands.literal("speed")
                                        .then(Commands.argument("value", DoubleArgumentType.doubleArg(0.0, 1.0))
                                                .executes(ctx -> {
                                                    double v = DoubleArgumentType.getDouble(ctx, "value");
                                                    Config.AXE_SPEED.set(v);
                                                    Config.SPEC.save();
                                                    ctx.getSource().sendSuccess(() -> Component.literal("axe speed = " + v), true);
                                                    return 1;
                                                }))))
                        .then(Commands.literal("shovel")
                                .then(Commands.literal("mode")
                                        .then(Commands.argument("value", StringArgumentType.word())
                                                .suggests((ctx, b) -> { b.suggest("DENY"); b.suggest("SLOW"); return b.buildFuture(); })
                                                .executes(ctx -> {
                                                    String v = StringArgumentType.getString(ctx, "value").toUpperCase();
                                                    if (!v.equals("DENY") && !v.equals("SLOW")) {
                                                        ctx.getSource().sendFailure(Component.literal("Mode must be DENY or SLOW"));
                                                        return 0;
                                                    }
                                                    Config.SHOVEL_MODE.set(v);
                                                    Config.SPEC.save();
                                                    ctx.getSource().sendSuccess(() -> Component.literal("shovel mode = " + v), true);
                                                    return 1;
                                                })))
                                .then(Commands.literal("speed")
                                        .then(Commands.argument("value", DoubleArgumentType.doubleArg(0.0, 1.0))
                                                .executes(ctx -> {
                                                    double v = DoubleArgumentType.getDouble(ctx, "value");
                                                    Config.SHOVEL_SPEED.set(v);
                                                    Config.SPEC.save();
                                                    ctx.getSource().sendSuccess(() -> Component.literal("shovel speed = " + v), true);
                                                    return 1;
                                                }))))
                        .then(Commands.literal("hoe")
                                .then(Commands.literal("mode")
                                        .then(Commands.argument("value", StringArgumentType.word())
                                                .suggests((ctx, b) -> { b.suggest("DENY"); b.suggest("SLOW"); return b.buildFuture(); })
                                                .executes(ctx -> {
                                                    String v = StringArgumentType.getString(ctx, "value").toUpperCase();
                                                    if (!v.equals("DENY") && !v.equals("SLOW")) {
                                                        ctx.getSource().sendFailure(Component.literal("Mode must be DENY or SLOW"));
                                                        return 0;
                                                    }
                                                    Config.HOE_MODE.set(v);
                                                    Config.SPEC.save();
                                                    ctx.getSource().sendSuccess(() -> Component.literal("hoe mode = " + v), true);
                                                    return 1;
                                                })))
                                .then(Commands.literal("speed")
                                        .then(Commands.argument("value", DoubleArgumentType.doubleArg(0.0, 1.0))
                                                .executes(ctx -> {
                                                    double v = DoubleArgumentType.getDouble(ctx, "value");
                                                    Config.HOE_SPEED.set(v);
                                                    Config.SPEC.save();
                                                    ctx.getSource().sendSuccess(() -> Component.literal("hoe speed = " + v), true);
                                                    return 1;
                                                }))))));
    }
}