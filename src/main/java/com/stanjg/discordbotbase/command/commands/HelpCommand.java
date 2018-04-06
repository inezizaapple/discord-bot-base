package com.stanjg.discordbotbase.command.commands;

import com.stanjg.discordbotbase.command.Command;
import com.stanjg.discordbotbase.command.CommandHandler;
import com.stanjg.discordbotbase.util.DiscordUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelpCommand extends Command {

    private CommandHandler handler;

    public HelpCommand(CommandHandler handler) {
        super("help", "", "Shows the help message");
        this.handler = handler;
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {

        event.getChannel().sendMessage(constructHelpMessage()).queue();

    }

    private MessageEmbed constructHelpMessage() {

        EmbedBuilder embed = DiscordUtils.getDefaultEmbed(true);

        StringBuilder message = new StringBuilder();
        for (Command cmd : handler.getCommands()) {
            message.append(constructCommandMessage(cmd));
        }

        embed.addField(new MessageEmbed.Field("All Commands", message.toString(), false));

        return embed.build();
    }

    private String constructCommandMessage(Command command) {
        return "**"+handler.getPREFIX()+command.getCommand()+(command.getArguments().length() > 0 ? " " + command.getArguments() : "")+"** "+command.getDescription()+"\n";
    }

}
