package io.github.berryheyy.discordchatter.http;

import io.github.berryheyy.discordchatter.config.DiscordChatterConfig;
import net.minecraftforge.event.ServerChatEvent;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public final class WebhookHandler {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static final OkHttpClient client = new OkHttpClient();

    public static void sendChatMessage(ServerChatEvent event)
    {
        RequestBody body = RequestBody.create(String.format("{\"avatar_url\": \"https://crafatar.com/avatars/%s?overlay\", \"username\": \"%s\", \"content\": \"%s\"}", 
            event.getPlayer().getUUID().toString(), event.getUsername(), event.getMessage()), JSON);

        Request request = new Request.Builder()
            .url(DiscordChatterConfig.webhook_url_string.get())
            .post(body)
            .build();

        try(Response response = client.newCall(request).execute()) {
            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
