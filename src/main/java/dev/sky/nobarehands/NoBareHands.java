package dev.sky.nobarehands;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@Mod(NoBareHands.MODID)
public class NoBareHands {
    public static final String MODID = "nobarehands";
    public static final Logger LOGGER = LogUtils.getLogger();

    public NoBareHands(IEventBus modEventBus, ModContainer modContainer) {
        // SERVER конфигурация автоматически синхронизируется с клиентом
        modContainer.registerConfig(ModConfig.Type.SERVER, Config.SPEC);
        NeoForge.EVENT_BUS.register(NoBareHandsHandler.class);
        NeoForge.EVENT_BUS.register(ConfigCommand.class);
        LOGGER.info("NoBareHands mod initialized!");
    }
}