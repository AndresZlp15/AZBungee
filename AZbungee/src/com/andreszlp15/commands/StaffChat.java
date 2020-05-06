package com.andreszlp15.commands;

import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.*;
import net.md_5.bungee.api.connection.*;
import net.md_5.bungee.api.*;
import net.md_5.bungee.api.chat.*;
import com.andreszlp15.Settings.Settings;
import java.util.*;

public class StaffChat extends Command
{
public static BungeeCord proxy;
public static HashMap<ProxiedPlayer, Boolean> toggled;

static {
    StaffChat.proxy = BungeeCord.getInstance();
    StaffChat.toggled = new HashMap<ProxiedPlayer, Boolean>();
}

public StaffChat() {
    super("staffchat", "azbungee.staffchat", new String[] { "sc" });
}

public void execute(final CommandSender sender, final String[] args) {
    if (sender instanceof ProxiedPlayer) {
        final ProxiedPlayer p = (ProxiedPlayer)sender;
        if (args.length == 0) {
            p.sendMessage((BaseComponent)new TextComponent("§cUsage: /staffchat <Message..>"));
            return;
        }
        if (args.length >= 1) {
            final StringBuilder str = new StringBuilder();
            for (int i = 0; i < args.length; ++i) {
                str.append(String.valueOf(args[i]) + " ");
            }
            for (final ProxiedPlayer pp : StaffChat.proxy.getPlayers()) {
                if (pp.hasPermission("azbungee.staff")) {
                    pp.sendMessage((BaseComponent)new TextComponent(Settings.scformat.replace("%name%", p.getName()).replace("%message%", str.toString()).replace("%server%", p.getServer().getInfo().getName())));
                }
            }
        }
    }
}
}
