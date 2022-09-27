package me.saatvik;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import me.saatvik.events.GuildJoin;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;

import java.util.EnumSet;
import java.util.List;


public class CreateEvent extends ListenerAdapter {

    private static String webhookURL;

    public static WebhookClient copyClient;

    public void onMessageReceived(MessageReceivedEvent event ) {

        String messageSent = event.getMessage().getContentRaw();
        if(messageSent.equalsIgnoreCase("~create") && !(event.getAuthor().isBot())) {
            createTextChannel(event.getMember(),"Pin Channel");

            String userName = event.getAuthor().getName();
            WebhookClientBuilder builder = new WebhookClientBuilder(this.webhookURL); // or id, token
            builder.setThreadFactory((job) -> {
                Thread thread = new Thread(job);
                thread.setName("Hello");
                thread.setDaemon(true);
                return thread;
            });
            builder.setWait(true);
            WebhookClient client = builder.build();

            copyClient = client;

            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setColor(0xc302e0)
                    .setDescription("Channel Create Successfully!")
                    .build();

            copyClient.send(embed);
            test(event.getAuthor());

        }









    }

    public static boolean lookForCopyClient(){
        try{
            TextChannel pinChannel =  GuildJoin.GUILD.getTextChannelsByName("pin-channel",true).get(0);
            RestAction<List<Webhook>>  a = pinChannel.retrieveWebhooks();
            webhookURL =  a.complete().get(0).getUrl();

            WebhookClientBuilder builder = new WebhookClientBuilder(webhookURL); // or id, token
            builder.setThreadFactory((job) -> {
                Thread thread = new Thread(job);
                thread.setName("Hello");
                thread.setDaemon(true);
                return thread;
            });
            builder.setWait(true);
            WebhookClient client = builder.build();

            copyClient = client;
            return true;

            //pinChannel.retrieveWebhooks().getJDA().retrieveWebhookById().queue();

        } catch(NullPointerException e ){
            return false;
        }
    }

    public void test(User user){
        WebhookMessageBuilder builder = new WebhookMessageBuilder();
        builder.setUsername(user.getName()); // use this username
        builder.setAvatarUrl(user.getEffectiveAvatarUrl()); // use this avatar
        builder.setContent("Test works lets go champ!");
        copyClient.send(builder.build());
    }

    public void createTextChannel(Member member, String name) {
        Guild guild = member.getGuild();
        TextChannel thisOne = guild.createTextChannel(name)
                //.addPermissionOverride(member, EnumSet.of(Permission.VIEW_CHANNEL), null)
                //.addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                .complete(); // this actually sends the request to discord.

        //TextChannel thisOne = guild.getTextChannelsByName(name,true).get(0);

        Webhook Webhook = thisOne.createWebhook("CopyCat").complete();

        String webhookID = Webhook.getUrl();
        this.webhookURL = webhookID;

    }



}

//When bot joins tell user to type ~create
//Whenever there is a pin copy