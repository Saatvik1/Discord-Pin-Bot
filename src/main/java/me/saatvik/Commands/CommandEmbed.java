package me.saatvik.Commands;

import me.saatvik.DiscordBots;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.nio.channels.Channel;

public class CommandEmbed extends ListenerAdapter {

    public static MessageChannel CHANNELSETNAME;

    public void onMessageReceived(MessageReceivedEvent event) {

        String GUILDOWNERID = event.getGuild().getOwner().getId();

        String authorCheck = "";
        String[] comList = new String[3];
        comList[0] = "~help";
        comList[1] = "~setPinChannel";
        comList[2] = "~notSure";

        String[] args = event.getMessage().getContentRaw().split(" ");
        if((args[0].equalsIgnoreCase(DiscordBots.prefix + "help")) || ((args[0]).contains(DiscordBots.prefix)) && !args[0].contains(comList[0]) && !args[0].contains(comList[1]) && !args[0].contains(comList[2]))  {


            EmbedBuilder help = new EmbedBuilder();
            help.setTitle("Command List and Info");
            help.addField("Dob", "Dob made this",false);
            help.addField("Commands","~help \n ~setPinChannel \n ~notSure",false);
            help.setColor(0xc302e0);

            event.getChannel().sendMessageEmbeds(help.build()).queue();
        }

        //setPinChannel Embed INTRO

        if(args[0].equalsIgnoreCase(DiscordBots.prefix + "setPinChannel")) {

            CHANNELSETNAME = event.getChannel();


            authorCheck =  event.getAuthor().getId();
            System.out.println(authorCheck);
            EmbedBuilder setPinChan = new EmbedBuilder();
            setPinChan.setTitle("Channel Set!!!");
            setPinChan.setColor(0xc302e0);
            event.getChannel().sendMessageEmbeds(setPinChan.build()).queue();

            SetPinChannelCommand SPCC = new SetPinChannelCommand();
            SPCC.ChannelGet(event.getAuthor().getId(), CHANNELSETNAME);
        }

    }


}
