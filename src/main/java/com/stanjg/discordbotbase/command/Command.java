package com.stanjg.discordbotbase.command;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class Command {

    private String command, arguments, description;
    private boolean admin;

    public Command(String command, String arguments, String description) {
        this(command, arguments, description, false);
    }

    public Command(String command, String arguments, String description, boolean admin) {
        this.command = command;
        this.arguments = arguments;
        this.description = description;
        this.admin = admin;
    }

    public abstract void run(MessageReceivedEvent event, String[] args);

    public String getCommand() {
        return command;
    }

    public String getArguments() {
        return arguments;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAdmin() {
        return admin;
    }
}
