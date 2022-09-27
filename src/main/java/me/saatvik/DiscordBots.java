package me.saatvik;

import me.saatvik.Commands.CommandEmbed;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;


public class DiscordBots {

    public static String prefix = "~";

    public static void main(String[] args) throws LoginException {

        JDA bot = JDABuilder.createDefault("OTU4MjQzMTM4MDg5MTMyMDYz.YkKfdw.gu0PqdtodkkY-nJToAvpRnroli4")
                .setActivity(Activity.playing("with your mom"))
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(EnumSet.allOf(GatewayIntent.class))
                .build();

        bot.addEventListener(new CreateEvent());
        bot.addEventListener(new CheckifMessagePin());
        bot.addEventListener(new CommandEmbed());


    }



}
