package io.github.berryheyy.discordchatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.berryheyy.discordchatter.config.DiscordChatterConfig;
import io.github.berryheyy.discordchatter.discord.DiscordBot;
import io.github.berryheyy.discordchatter.http.WebhookHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.DistExecutor.SafeRunnable;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

@Mod(Reference.MOD_ID)
public class DiscordChatter {

    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_ID);

    public DiscordChatter() {
        ModLoadingContext.get().registerConfig(Type.SERVER, DiscordChatterConfig.SPEC, "discordchatter-server.toml");
        MinecraftForge.EVENT_BUS.addListener(this::onMessage);
        MinecraftForge.EVENT_BUS.addListener(this::serverSetup);
    }

    private void onMessage(ServerChatEvent event) {
        DistExecutor.safeRunWhenOn(Dist.DEDICATED_SERVER, () -> new SafeRunnable() {
            @Override
            public void run() {
                WebhookHandler.sendChatMessage(event);
                
            }} );
    }

    private void serverSetup(FMLServerStartedEvent event) {
        DistExecutor.safeRunWhenOn(Dist.DEDICATED_SERVER, () -> new SafeRunnable() {
            @Override
            public void run() {
                DiscordBot.initiateBot(event);
            }} );
    }
}
