package de.oliver.fancylib;

import de.oliver.fancylib.gui.inventoryClick.InventoryClickListener;
import de.oliver.fancylib.gui.inventoryClick.impl.CancelInventoryItemClick;
import de.oliver.fancylib.gui.inventoryClick.impl.ChangePageInventoryItemClick;
import de.oliver.fancylib.itemClick.PlayerInteractListener;
import org.apache.maven.artifact.versioning.ComparableVersion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class FancyLib {

    public static final ComparableVersion VERSION = new ComparableVersion("1.0.0");

    private static Plugin plugin;

    public static void setPlugin(Plugin plugin) {
        if(FancyLib.plugin == null){
            FancyLib.plugin = plugin;

            CancelInventoryItemClick.INSTANCE.register();
            ChangePageInventoryItemClick.INSTANCE.register();

            Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), plugin);
            Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), plugin);
        }
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
