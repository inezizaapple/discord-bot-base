package com.stanjg.discordbotbase.command;

import com.stanjg.discordbotbase.Bot;
import com.stanjg.discordbotbase.util.AdminCheck;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.List;

public class CommandHandler extends ListenerAdapter {

    private final String PREFIX = "-";

    private List<Command> commands;
    private AdminCheck adminCheck;

    public CommandHandler() {

        Bot.getShards().addEventListener(this);
        adminCheck = (guild, member) -> { return true; };

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getChannelType() != ChannelType.TEXT || event.getAuthor().isBot())
            return;

        if (!event.getMessage().getContentRaw().startsWith(PREFIX))
            return;

        String[] split = event.getMessage().getContentRaw().split(" ");
        String cmdName = split[0].substring(PREFIX.length());

        Command command = commands.stream().filter(cmd -> cmd.getCommand().equalsIgnoreCase(cmdName)).findFirst().orElse(null);
        if (command == null)
            return;

        if (command.isAdmin() && !adminCheck.isAdmin(event.getGuild(), event.getMember())) {
            // TODO send message, or ignore
            return;
        }

        String[] args;
        if (split.length > 1) {
            args = event.getMessage().getContentRaw().substring(PREFIX.length() + cmdName.length() + 1).split(" ");
        } else {
            args = new String[0];
        }

        command.run(event, args);
        System.out.println("Command '"+command.getCommand()+"' has been run by '"+event.getAuthor().getName()+"' in '"+event.getGuild().getName()+"'");
    }

    public void setAdminCheck(AdminCheck check) {
        this.adminCheck = check;
    }

    public String getPREFIX() {
        return PREFIX;
    }

    public List<Command> getCommands() {
        return commands;
    }
}
