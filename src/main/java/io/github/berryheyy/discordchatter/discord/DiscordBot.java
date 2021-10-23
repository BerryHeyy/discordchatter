package io.github.berryheyy.discordchatter.discord;

import java.util.UUID;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import io.github.berryheyy.discordchatter.DiscordChatter;
import io.github.berryheyy.discordchatter.config.DiscordChatterConfig;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

public final class DiscordBot {

    private static DiscordApi api;
    private static MinecraftServer server;

    public static void initiateBot(FMLServerStartedEvent event)
    {
        server = event.getServer();
        api = new DiscordApiBuilder().setToken(DiscordChatterConfig.bot_token_string.get()).login().join();
        api.addMessageCreateListener(DiscordBot::onMessageCreate);

    }

    private static void onMessageCreate(MessageCreateEvent event)
    {
        if (event.getChannel().getId() != Long.parseLong(DiscordChatterConfig.channel_id_string.get())) return;
        
        DiscordChatter.LOGGER.info(event.getMessageContent());
        server.getPlayerList().broadcastMessage(new TranslationTextComponent(event.getMessageContent()), ChatType.CHAT, UUID.randomUUID());
    }

    
    
}
