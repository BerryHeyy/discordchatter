package io.github.berryheyy.discordchatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.berryheyy.discordchatter.config.DiscordChatterConfig;
import io.github.berryheyy.discordchatter.event.DCEventHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;


@Mod(Reference.MOD_ID)
public class DiscordChatter {

    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_ID);

    public DiscordChatter() {
        ModLoadingContext.get().registerConfig(Type.SERVER, DiscordChatterConfig.SPEC, "discordchatter-server.toml");
        DCEventHandler.init();
    }
}
