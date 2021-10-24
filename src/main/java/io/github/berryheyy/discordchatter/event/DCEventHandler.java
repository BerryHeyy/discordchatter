package io.github.berryheyy.discordchatter.event;

import io.github.berryheyy.discordchatter.DiscordChatter;
import io.github.berryheyy.discordchatter.discord.DiscordBot;
import io.github.berryheyy.discordchatter.http.WebhookHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.DistExecutor;

import net.minecraftforge.fml.DistExecutor.SafeRunnable;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

public final class DCEventHandler {

    public static void init() {
        DistExecutor.safeRunWhenOn(Dist.DEDICATED_SERVER, () -> new SafeRunnable() {
            @Override
            public void run() {
                MinecraftForge.EVENT_BUS.addListener(DCEventHandler::onMessage);
                MinecraftForge.EVENT_BUS.addListener(DCEventHandler::serverSetup);
                MinecraftForge.EVENT_BUS.addListener(DCEventHandler::onPlayerJoin);
                MinecraftForge.EVENT_BUS.addListener(DCEventHandler::onPlayerLeave);
                MinecraftForge.EVENT_BUS.addListener(DCEventHandler::onPlayerDeath);
            }} );
    }

    public static void onMessage(ServerChatEvent event) {
        WebhookHandler.sendChatMessage(event);
    }

    public static void serverSetup(FMLServerStartedEvent event) {
        DiscordBot.initiateBot(event);
    }

    public static void onPlayerJoin(PlayerLoggedInEvent event) {
        WebhookHandler.sendSystemMessage(String.format("%s joined the game", event.getPlayer().getDisplayName().getString()));
    }

    public static void onPlayerLeave(PlayerLoggedOutEvent event) {
        WebhookHandler.sendSystemMessage(String.format("%s left the game", event.getPlayer().getDisplayName().getString()));
    }

    public static void onPlayerDeath(LivingDeathEvent event)
    {
        WebhookHandler.sendSystemMessage(event.getSource().getLocalizedDeathMessage(event.getEntityLiving()).getString());
    }


}
