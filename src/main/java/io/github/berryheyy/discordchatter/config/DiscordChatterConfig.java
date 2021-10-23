package io.github.berryheyy.discordchatter.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class DiscordChatterConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<String> bot_token_string;
    public static final ForgeConfigSpec.ConfigValue<String> webhook_url_string;

    static {
        BUILDER.push("DiscordChatter configs");

        bot_token_string = BUILDER.comment("The token the mod will connect to.").define("Bot Token", "Enter your token here.");
        webhook_url_string = BUILDER.comment("The webhook the mod will use to send messages.").define("Webhook URL", "Enter your webhook URL here.");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
