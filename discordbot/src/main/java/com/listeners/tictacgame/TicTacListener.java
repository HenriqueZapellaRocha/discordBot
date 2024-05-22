package com.listeners.tictacgame;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class TicTacListener extends ListenerAdapter{

    private Boolean gameStarted;

    public TicTacListener() {
        this.gameStarted = false;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if(event.getAuthor().isBot())
            return;
        
        if(event.getMessage().getContentRaw().contains("!jogo da velha") && gameStarted == false) {
            this.gameStarted = true;
            MessageChannel channel = event.getChannel();
            channel.sendMessage("```"+TicTacLogic.getInstance().printBoard()+"```").queue();
        }

        
    }
    
}
