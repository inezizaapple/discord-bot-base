package com.stanjg.discordbotbase;

import com.stanjg.discordbotbase.command.CommandHandler;
import com.stanjg.discordbotbase.util.BotConfig;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class Bot {

    private static ShardManager shards;
    private static CommandHandler commandHandler;

    public Bot() {
        shards = login();
        commandHandler = new CommandHandler();

        commandHandler.setAdminCheck((guild, member) -> {
            // TODO add your own here
            return true;
        });
    }

    private ShardManager login() {
        DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
        builder.setToken(BotConfig.getProperty(BotConfig.Options.BOT_TOKEN));

        try {
            return builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static ShardManager getShards() {
        return shards;
    }

}
