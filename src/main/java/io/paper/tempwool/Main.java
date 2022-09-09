package io.paper.tempwool;

import io.paper.tempwool.events.Events;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    public static Plugin plugin;
    public static File configFile;
    public static FileConfiguration config;

    public void onEnable() {
        Main.plugin = (Plugin)this;
        Bukkit.getServer().getPluginManager().registerEvents((Listener)new Events(), (Plugin)this);
        this.saveDefaultConfig();
        createFileConfig();
    }

    public static void createFileConfig() {
        Main.configFile = new File(Main.plugin.getDataFolder(), "config.yml");
        if (!Main.configFile.exists()) {
            Main.configFile.getParentFile().mkdirs();
            Main.plugin.saveResource("config.yml", false);
        }
        Main.config = (FileConfiguration)new YamlConfiguration();
        try {
            Main.config.load(Main.configFile);
        }
        catch (IOException | InvalidConfigurationException ex5) {
            final Exception ex4;
            final Exception e;
            final Exception ex3 = e = null;
            e.printStackTrace();
        }
    }

    public static void saveMainConfig() {
        try {
            Main.config.save(Main.configFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
