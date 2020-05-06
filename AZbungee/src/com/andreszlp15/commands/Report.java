package com.andreszlp15.commands;

	import net.md_5.bungee.api.plugin.*;
	import net.md_5.bungee.api.connection.*;
	import net.md_5.bungee.*;
	import net.md_5.bungee.api.*;
	import net.md_5.bungee.api.chat.*;
	import com.andreszlp15.Settings.*;
	import java.util.*;

	public class Report extends Command
	{
	    public static HashMap<ProxiedPlayer, Integer> cooldown;
	    public static BungeeCord proxy;
	    
	    static {
	        Report.cooldown = new HashMap<ProxiedPlayer, Integer>();
	        Report.proxy = BungeeCord.getInstance();
	    }
	    
	    public Report() {
	        super("report");
	    }
	    
	    public void execute(final CommandSender sender, final String[] args) {
	        if (sender instanceof ProxiedPlayer) {
	            final ProxiedPlayer p = (ProxiedPlayer)sender;
	            if (p.hasPermission("azbungee.report")) {
	                if (args.length == 0) {
	                    p.sendMessage((BaseComponent)new TextComponent("§cUsage: /report <Player> <Reason>"));
	                    return;
	                }
	                if (args.length == 1) {
	                    p.sendMessage((BaseComponent)new TextComponent("§cUsage: /report <Player> <Reason>"));
	                }
	                if (args.length >= 2) {
	                    final ProxiedPlayer target = Report.proxy.getPlayer(args[0]);
	                    if (target == null || !target.isConnected()) {
	                        p.sendMessage((BaseComponent)new TextComponent("§c" + args[0] + " is not online"));
	                        return;
	                    }
	                    if (Report.cooldown.containsKey(p)) {
	                        p.sendMessage((BaseComponent)new TextComponent(Settings.cooldown.replace("%cooldown%", new StringBuilder().append(Report.cooldown.get(p)).toString())));
	                        return;
	                    }
	                    p.sendMessage((BaseComponent)new TextComponent(Settings.sreport));
	                    Report.cooldown.put(p, 120);
	                    for (final ProxiedPlayer pp : BungeeCord.getInstance().getPlayers()) {
	                        if (pp.hasPermission("azbungee.staff")) {
	                            final StringBuilder str = new StringBuilder();
	                            for (int i = 1; i < args.length; ++i) {
	                                str.append(String.valueOf(args[i]) + " ");
	                            }
	                            pp.sendMessage((BaseComponent)new TextComponent(Settings.report.replace("%server%", p.getServer().getInfo().getName()).replace("%name%", p.getName()).replace("%target%", target.getName()).replace("%reason%", str.toString())));
	                        }
	                    }
	                }
	            }
	            else {
	                p.sendMessage((BaseComponent)new TextComponent("§cNope."));
	            }
	        }
	    }
	}
