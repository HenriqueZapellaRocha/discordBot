package com.listeners;

import java.util.Random;


import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JokenpoListener extends ListenerAdapter{



    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        
        String content = event.getMessage().getContentRaw();

        if(content.contains("!jokenpo")){
            
            String userChoise = splitMessage(content);
            Random random = new Random();
            int myChoice = random.nextInt(3);
            String[] choices = {"papel", "tesoura", "pedra"} ;
            int result = whoWin(myChoice, userChoise, choices);

            if(result == 0) {
                MessageChannel channel = event.getChannel();
                channel.sendMessage("Nós empatamos!! Você escolheu " + userChoise + ", eu escolhi " 
                                                                     + choices[myChoice]).queue();
            } else if(result == 1) {
                MessageChannel channel = event.getChannel();
                channel.sendMessage("Eu ganhei!! Você escolheu " + userChoise + ", eu escolhi " 
                                                                 +  choices[myChoice]).queue();
            } else {
                MessageChannel channel = event.getChannel();
                channel.sendMessage("Voce ganhou!! Você escolheu " + userChoise + ", eu escolhi " 
                                                                   +  choices[myChoice]).queue();
            }
        }
    }

    private String splitMessage(String message) {

        String messages[] = message.split(" ");
        return messages[1];
    }


    private int whoWin(int myChoice, String userChoice, String[] choices) {
        int userChoicesCoverted = -20;
        System.out.println(myChoice);
        for(int i =0; i < choices.length; i++) {
            if (choices[i].equals(userChoice)) {
                userChoicesCoverted = i;
            }
        }

        if(userChoicesCoverted == myChoice)
            return 0;
        if(userChoicesCoverted == 0 && myChoice == 1)
            return 1;
        if(userChoicesCoverted == 0 && myChoice == 2)
            return 2;
        if(userChoicesCoverted == 1 && myChoice == 0)
            return 2;
        if(userChoicesCoverted == 1 && myChoice == 2)
            return 1;
        if(userChoicesCoverted == 2 && myChoice == 0)
            return 1;
        if(userChoicesCoverted == 2 && myChoice == 1)
            return 2;


        return -1;
    
    }
}