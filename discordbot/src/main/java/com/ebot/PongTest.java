package com.ebot;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PongTest extends ListenerAdapter 
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if(event.getAuthor().isBot())
            return;

        String message = event.getMessage().getContentRaw();

        if(message.contains("!ping")){
            MessageChannel channel = event.getChannel();
            channel.sendMessage("PONG!").queue();
        }

    }
}