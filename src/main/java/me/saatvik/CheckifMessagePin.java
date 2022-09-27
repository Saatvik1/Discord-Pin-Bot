package me.saatvik;

import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import me.saatvik.events.GuildJoin;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import net.dv8tion.jda.api.entities.*;


public class CheckifMessagePin extends ListenerAdapter {


    public void onMessageUpdate(MessageUpdateEvent event) {
        GuildJoin.GUILD =  event.getGuild();
        if(event.getMessage().isPinned()
                && (!GuildJoin.GUILD.getTextChannelsByName("pin-channel",true).isEmpty())
                && ((!event.getAuthor().isSystem()) || (!event.isFromGuild()))) {

            User pinnedUser = event.getMessage().getAuthor();
            String linkToMessage = event.getMessage().getJumpUrl();
            String messageID = event.getMessageId();



            WebhookMessageBuilder builder = new WebhookMessageBuilder();
            builder.setUsername(pinnedUser.getName()); // use this username
            builder.setAvatarUrl(pinnedUser.getEffectiveAvatarUrl()); // use this avatar
            builder.setContent(event.getMessage().getContentDisplay());

            try{

                WebhookEmbed embed = new WebhookEmbedBuilder()
                        .setColor(0xc302e0)
                        .setDescription("[Jump to message]("+linkToMessage+")")
                        .build();


                CreateEvent.copyClient.send(builder.build());
                WebhookMessageBuilder builderSecond = new WebhookMessageBuilder();
                builderSecond.setUsername(pinnedUser.getName()); // use this username
                builderSecond.setAvatarUrl(pinnedUser.getEffectiveAvatarUrl());
                builderSecond.addEmbeds(embed);
                CreateEvent.copyClient.send(builderSecond.build());

                System.out.println("called");
                event.getChannel().unpinMessageById(messageID).complete();

            } catch(NullPointerException e){
                if(CreateEvent.lookForCopyClient()){
                    event.getChannel().sendMessage("Try again").queue();
                }
            }

        } else if(event.getMessage().isPinned()){
            event.getChannel().sendMessage("Type '~create'").queue();
        }

    }
}
