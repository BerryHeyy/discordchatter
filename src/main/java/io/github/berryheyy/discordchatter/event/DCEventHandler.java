package io.github.berryheyy.discordchatter.event;

import io.github.berryheyy.discordchatter.discord.DiscordBot;
import io.github.berryheyy.discordchatter.http.WebhookHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.DistExecutor;

import net.minecraftforge.fml.DistExecutor.SafeRunnable;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

public final class DCEventHandler {

    public static void init() {
        MinecraftForge.EVENT_BUS.addListener(DCEventHandler::onMessage);
        MinecraftForge.EVENT_BUS.addListener(DCEventHandler::serverSetup);
    }

    public static void onMessage(ServerChatEvent event) {
        DistExecutor.safeRunWhenOn(Dist.DEDICATED_SERVER, () -> new SafeRunnable() {
            @Override
            public void run() {
                WebhookHandler.sendChatMessage(event);
                
            }} );
    }

    public static void serverSetup(FMLServerStartedEvent event) {
        DistExecutor.safeRunWhenOn(Dist.DEDICATED_SERVER, () -> new SafeRunnable() {
            @Override
            public void run() {
                DiscordBot.initiateBot(event);
            }} );
    }


}
