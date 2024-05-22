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
        //return if ia bot calling my bot
        if(event.getAuthor().isBot())
            return;
        //if the game not started and a player is trying to start one
        if(event.getMessage().getContentRaw().contains("!jogo da velha") && gameStarted == false) {
            this.gameStarted = true;
            MessageChannel channel = event.getChannel();
            channel.sendMessage("Jogo começou!" + event.getAuthor().getAsMention()+ ". Faça sua jogada").queue();
            channel.sendMessage("```"+TicTacLogic.getInstance().printBoard()+"```").queue();
        // if the game is already started recive the next move to the game
        } else if(gameStarted == true) {
            TicTacLogic game = TicTacLogic.getInstance();
            MessageChannel channel = event.getChannel();
            //taking the player move
            if(game.makePlay(Integer.parseInt(event.getMessage().getContentRaw())-1, "X")) {
            channel.sendMessage("```"+TicTacLogic.getInstance().printBoard()+"```").queue();
            //verify winner
            if(game.verifyWin("X")) {
                channel.sendMessage("Jogador ganhou da maquina!!").queue();
                gameStarted = false;
                game.restartGame();
                return;
            }
            //verify if the board is full
            if(game.boardIsFull()) {
                channel.sendMessage("Jogo empatou").queue();
                gameStarted = false;
                game.restartGame();
                return;
            }
            //machine make his move
            game.machineMove();
            channel.sendMessage("maquina faz jogada:").queue();
            channel.sendMessage("```"+TicTacLogic.getInstance().printBoard()+"```").queue();
            //verify winner
            if(game.verifyWin("O")) {
                channel.sendMessage("Maquina ganhou do jogador!!").queue();
                gameStarted = false;
                game.restartGame();
                return;
            }
            //verify if the board is full
            if(game.boardIsFull()) {
                channel.sendMessage("Jogo empatou").queue();
                gameStarted = false;
                game.restartGame();
                return;
            }
            }
    
        }

        
    }
    
}
