package me.saatvik.Commands;

import me.saatvik.events.GuildJoin;
import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.entities.UserById;

public class SetPinChannelCommand extends ListenerAdapter  {
public static Channel channelForPins;

public void ChannelGet(String USERID, Channel channelName) {
    GuildJoin ownerObject = new GuildJoin();
    String IDCompare = ownerObject.GUILDOWNER;

}

}
