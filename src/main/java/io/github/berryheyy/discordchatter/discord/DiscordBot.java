package io.github.berryheyy.discordchatter.discord;

import java.util.UUID;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import io.github.berryheyy.discordchatter.DiscordChatter;
import io.github.berryheyy.discordchatter.Reference;
import io.github.berryheyy.discordchatter.config.DiscordChatterConfig;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
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
        if (event.getMessageAuthor().isWebhook()) return;
        if (event.getMessageAuthor().isBotUser()) return;
        
        DiscordChatter.LOGGER.info(event.getMessageContent());

        ITextComponent userframe = new TranslationTextComponent("<%s>", event.getMessageAuthor().getDisplayName()).withStyle((style) -> {
            return style.withColor(TextFormatting.BLUE)
            .withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, String.format("<@%s> ", event.getMessageAuthor().getIdAsString())))
            .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslationTextComponent("Click to mention user.")));
        });

        ITextComponent finalMessage = new TranslationTextComponent("%s %s", userframe, event.getMessageContent());

        server.getPlayerList().broadcastMessage(finalMessage, ChatType.CHAT, UUID.randomUUID());
    }

    
    
}
