package com.andreszlp15.commands;

import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.*;
import net.md_5.bungee.api.connection.*;
import net.md_5.bungee.api.*;
import com.andreszlp15.Settings.*;
import net.md_5.bungee.api.chat.*;
import java.util.*;

public class Request extends Command
{
    public static BungeeCord proxy;
    public static HashMap<ProxiedPlayer, Integer> cooldown;
    
    static {
    	Request.proxy = BungeeCord.getInstance();
    	Request.cooldown = new HashMap<ProxiedPlayer, Integer>();
    }
    
    public Request() {
        super("request", "azbungee.request", new String[] { "helpop" });
    }
    
    public void execute(final CommandSender sender, final String[] args) {
        if (sender instanceof ProxiedPlayer) {
            final ProxiedPlayer p = (ProxiedPlayer)sender;
            if (Request.cooldown.containsKey(p)) {
                p.sendMessage((BaseComponent)new TextComponent(Settings.cooldown.replace("%cooldown%", new StringBuilder().append(Request.cooldown.get(p)).toString())));
                return;
            }
            if (args.length == 0) {
                p.sendMessage((BaseComponent)new TextComponent("§cUsage: /request <Message..>"));
                return;
            }
            if (args.length >= 1) {
                final StringBuilder str = new StringBuilder();
                for (int i = 0; i < args.length; ++i) {
                    str.append(String.valueOf(args[i]) + " ");
                }
                p.sendMessage((BaseComponent)new TextComponent(Settings.srequest));
                for (final ProxiedPlayer pp : Request.proxy.getPlayers()) {
                    if (pp.hasPermission("azbungee.staff")) {
                        pp.sendMessage((BaseComponent)new TextComponent(Settings.request.replace("%player%", p.getName()).replace("%msg%", str.toString()).replace("%server%", p.getServer().getInfo().getName())));
                    }
                }
            }
            Request.cooldown.put(p, 120);
        }
    }
}
