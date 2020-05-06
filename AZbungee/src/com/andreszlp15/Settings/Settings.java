package com.andreszlp15.Settings;

import com.andreszlp15.azbungee.*;
import net.md_5.bungee.api.*;
import java.util.*;
import net.md_5.bungee.api.connection.*;
import net.md_5.bungee.api.chat.*;

public class Settings
{
    public static String report;
    public static String scformat;
    public static String sjoin;
    public static String cooldown;
    public static String request;
    public static String sreport;
    public static String srequest;
    public static String estaffchat;
    public static String dstaffchat;
    public static String staffchattoggled;
    
    static {
        Settings.report = ChatColor.translateAlternateColorCodes('&', Main.config.getString("REPORT"));
        Settings.scformat = ChatColor.translateAlternateColorCodes('&', Main.config.getString("STAFFCHAT-FORMAT"));
        Settings.sjoin = ChatColor.translateAlternateColorCodes('&', Main.config.getString("STAFF-JOIN"));
        Settings.cooldown = ChatColor.translateAlternateColorCodes('&', Main.config.getString("COOLDOWN"));
        Settings.request = ChatColor.translateAlternateColorCodes('&', Main.config.getString("REQUEST-FORMAT"));
        Settings.sreport = ChatColor.translateAlternateColorCodes('&', Main.config.getString("SUCCESSFULL-REPORT"));
        Settings.srequest = ChatColor.translateAlternateColorCodes('&', Main.config.getString("SUCCESSFULL-REQUEST"));
        Settings.estaffchat = ChatColor.translateAlternateColorCodes('&', Main.config.getString("ENABLED-STAFFCHAT"));
        Settings.dstaffchat = ChatColor.translateAlternateColorCodes('&', Main.config.getString("DISABLED-STAFFCHAT"));
        Settings.staffchattoggled = ChatColor.translateAlternateColorCodes('&', Main.config.getString("TOGGLED-STAFFCHAT"));
    }
    static String translate(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
