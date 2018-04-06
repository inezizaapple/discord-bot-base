package com.stanjg.discordbotbase.util;

import com.stanjg.discordbotbase.Bot;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;

public class DiscordUtils {

    public static EmbedBuilder getDefaultEmbed(boolean withFooter) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(getColor());
        builder.setFooter(BotConfig.getProperty(BotConfig.Options.FOOTER_TEXT), Bot.getShards().getShards().get(0).getSelfUser().getAvatarUrl());
        return builder;
    }

    public static Color getColor() {
        return Color.decode(BotConfig.getProperty(BotConfig.Options.COLOR));
    }

}
