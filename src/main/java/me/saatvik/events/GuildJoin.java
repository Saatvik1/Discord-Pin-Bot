package me.saatvik.events;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildJoin extends ListenerAdapter {

    public String GUILDOWNER;
    public String GUILDID;
    public static Guild GUILD;

    public void onGuildJoin(GuildJoinEvent event) {

    OwnerUserID(event.getGuild().getOwner().getId(),
            event.getGuild().getId());
    event.getGuild().getChannels();
    //GUILD = event.getGuild();
}

public String OwnerUserID(String OWNERID, String GUILDSID) {

     GUILDOWNER = OWNERID;
     GUILDID = GUILDSID;

    return GUILDOWNER;
}

}
