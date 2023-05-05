package de.oliver.fancylib;

import de.oliver.fancylib.gui.inventoryClick.InventoryClickListener;
import org.apache.maven.artifact.versioning.ComparableVersion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class FancyLib {

    public static final ComparableVersion VERSION = new ComparableVersion("1.0.0");

    private static Plugin plugin;

    public static void setPlugin(Plugin plugin) {
        if(FancyLib.plugin == null){
            FancyLib.plugin = plugin;
            Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), plugin);
        }
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
