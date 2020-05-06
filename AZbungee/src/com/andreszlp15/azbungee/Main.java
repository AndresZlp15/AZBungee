package com.andreszlp15.azbungee; 

import java.nio.file.*;
import net.md_5.bungee.api.*;
import com.andreszlp15.Settings.*;
import java.util.concurrent.*;
import com.andreszlp15.commands.*;
import net.md_5.bungee.api.plugin.*;
import com.andreszlp15.events.*;
import net.md_5.bungee.config.*;
import java.io.*;
import net.md_5.bungee.api.connection.*;
import java.util.*;

public class Main extends Plugin
{
    public static Configuration config;
    public static Main instance;
    
    public void onEnable() {
        Main.instance = this;
        try {
            if (!this.getDataFolder().exists()) {
                this.getDataFolder().mkdir();
            }
            final File file = new File(this.getDataFolder(), "config.yml");
            if (!file.exists()) {
                Files.copy(this.getResourceAsStream("config.yml"), file.toPath(), new CopyOption[0]);
            }
            this.loadConfig();
            this.registerEvents();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (!Main.config.getString("CONFIG-VERSION").equalsIgnoreCase("B5")) {
            System.out.println("DUE TO CHANGES IN THE LATEST VERSION PLEASE DELETE YOUR CONFIG.YML");
            System.out.println("DUE TO CHANGES IN THE LATEST VERSION PLEASE DELETE YOUR CONFIG.YML");
            return;
        }
        this.getLogger().info(ChatColor.DARK_GRAY + "-=-=-=-=-=-=-=-=-=-=-=-=-");
        this.getLogger().info(ChatColor.GREEN + "[b] Enabled");
        this.getLogger().info(ChatColor.GREEN + "Version: " + this.getDescription().getVersion());
        this.getLogger().info(ChatColor.DARK_GRAY + "-=-=-=-=-=-=-=-=-=-=-=-=-");
        this.registerCommands();
        this.getProxy().getScheduler().schedule((Plugin)this, (Runnable)new Runnable() {
            @Override
            public void run() {
                Main.this.requestCooldown();
                Main.this.reportCooldown();
            }
        }, 0L, 1L, TimeUnit.SECONDS);
    }
    
    void registerCommands() {
        this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new Report());
        this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new StaffChat());
        this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new Request());
    }
    
    void registerEvents() {
        this.getProxy().getPluginManager().registerListener((Plugin)this, (Listener)new PlayerJoin());
    }
    
    public void loadConfig() {
        try {
            Main.config = ConfigurationProvider.getProvider((Class)YamlConfiguration.class).load(new File(this.getDataFolder(), "config.yml"));
        }
        catch (IOException ex) {}
    }
    
    void reportCooldown() {
        for (final ProxiedPlayer p : Report.cooldown.keySet()) {
            final int newvalue = Report.cooldown.get(p) - 1;
            if (newvalue != 0) {
            	Report.cooldown.put(p, newvalue);
            }
            else {
            	Request.cooldown.remove(p);
            }
        }
    }
    
    void requestCooldown() {
        for (final ProxiedPlayer p : Request.cooldown.keySet()) {
            final int newvalue = Request.cooldown.get(p) - 1;
            if (newvalue != 0) {
            	Request.cooldown.put(p, newvalue);
            }
            else {
            	Request.cooldown.remove(p);
            }
        }
    }
    
    public void save() {
        try {
            ConfigurationProvider.getProvider((Class)YamlConfiguration.class).save(Main.config, new File(this.getDataFolder(), "config.yml"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
