package com.ebot;

import javax.security.auth.login.LoginException;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;


public class Main {

    private final Dotenv config;

    public Main() throws LoginException{
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        JDABuilder.createDefault(token, GatewayIntent.MESSAGE_CONTENT, 
        GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS).addEventListeners(new PongTest()).build();
   }

   public Dotenv getConfig() {
        return config;  
   }

    public static void main(String[] args) {
        try{
            Main bot = new Main();
        } catch (LoginException e) {
            System.out.println(e);
        }
    }
}