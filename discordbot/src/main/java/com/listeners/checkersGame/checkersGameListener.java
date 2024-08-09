package com.listeners.checkersGame;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class checkersGameListener extends ListenerAdapter {

    

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String content = event.getMessage().getContentRaw();
        if(content.contains("!checkersGame")) {
            if(event.getAuthor().isBot()) return;
            else {
                CheckersGame game = CheckersGame.getInstance();
                game.printBoard();
            }
        }
    }
}
