package com.andreszlp15.events;

import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.*;
import net.md_5.bungee.api.connection.*;
import com.andreszlp15.Settings.*;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.event.*;
import net.md_5.bungee.api.event.*;

public class PlayerJoin implements Listener
{
    public static BungeeCord proxy;
    
    static {
        PlayerJoin.proxy = BungeeCord.getInstance();
    }
    
    @EventHandler
    public void onJoin(final ServerConnectedEvent e) {
        final ProxiedPlayer p = e.getPlayer();
        if (p.hasPermission("azbungee.staff")) {
            for (final ProxiedPlayer pp : PlayerJoin.proxy.getPlayers()) {
                if (pp.hasPermission("azbungee.staff")) {
                    pp.sendMessage((BaseComponent)new TextComponent(Settings.sjoin.replace("%player%", p.getName()).replace("%server%", e.getServer().getInfo().getName())));
                }
            }
        }
    }
    
    @EventHandler
    public void onLogin(final LoginEvent Ev) {
        if (Ev.isCancelled()) {
            return;
        }
    }
}
