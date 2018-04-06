package com.stanjg.discordbotbase.util;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;

public interface AdminCheck {

    boolean isAdmin(Guild guild, Member member);

}
