package io.github.berryheyy.discordchatter.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class DiscordChatterConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<String> bot_token_string;
    public static final ForgeConfigSpec.ConfigValue<String> webhook_url_string;
    public static final ForgeConfigSpec.ConfigValue<String> channel_id_string;

    public static final ForgeConfigSpec.ConfigValue<String> system_avatar_url;
    public static final ForgeConfigSpec.ConfigValue<String> system_username;

    public static final ForgeConfigSpec.ConfigValue<Boolean> display_mob_death_messages;
    public static final ForgeConfigSpec.ConfigValue<Boolean> display_death_messages;

    public static final ForgeConfigSpec.ConfigValue<Boolean> display_join_messages;
    public static final ForgeConfigSpec.ConfigValue<Boolean> display_leave_messages;

    

    static {
        BUILDER.push("DiscordChatter Technical Configs");

        bot_token_string = BUILDER.comment("The token the mod will connect to.").define("Bot Token", "Enter your token here.");
        webhook_url_string = BUILDER.comment("The webhook the mod will use to send messages.").define("Webhook URL", "Enter your webhook URL here.");
        channel_id_string = BUILDER.comment("The channel that messages will be read from.").define("Channel ID", "Enter channel ID here.");

        BUILDER.pop();
        BUILDER.push("DiscordChatter Customization Configs");

        system_avatar_url = BUILDER.comment("The avatar of the system messages.").define("System Avatar URL", "https://cdn.discordapp.com/attachments/704815957088010300/901777307692310569/Phi_lc.svg.png");
        system_username = BUILDER.comment("The username of the system messages.").define("System Username", "System");

        display_death_messages = BUILDER.define("Display Death Messages", true);
        display_mob_death_messages = BUILDER.define("Display Mob Death Messages", false);
        
        display_join_messages = BUILDER.define("Display Player Join Messages", true);
        display_leave_messages = BUILDER.define("Display Player Leave Messages", true);


        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
