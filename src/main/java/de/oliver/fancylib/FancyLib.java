package de.oliver.fancylib;

import de.oliver.fancylib.gui.inventoryClick.InventoryClickListener;
import de.oliver.fancylib.gui.inventoryClick.impl.CancelInventoryItemClick;
import de.oliver.fancylib.gui.inventoryClick.impl.ChangePageInventoryItemClick;
import de.oliver.fancylib.itemClick.PlayerInteractListener;
import de.oliver.fancylib.sentry.SentryLoader;
import de.oliver.fancylib.serverSoftware.ServerSoftware;
import de.oliver.fancylib.serverSoftware.schedulers.BukkitScheduler;
import de.oliver.fancylib.serverSoftware.schedulers.FancyScheduler;
import de.oliver.fancylib.serverSoftware.schedulers.FoliaScheduler;
import org.apache.maven.artifact.versioning.ComparableVersion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus;

public class FancyLib {

    public static final ComparableVersion VERSION = new ComparableVersion("1.0.3");

    private static JavaPlugin plugin;
    private static FancyScheduler scheduler;

    public static Plugin getPlugin() {
        return plugin;
    }

    public static void setPlugin(JavaPlugin plugin) {
        if (FancyLib.plugin == null) {
            FancyLib.plugin = plugin;


            scheduler = ServerSoftware.isFolia()
                    ? new FoliaScheduler(plugin)
                    : new BukkitScheduler(plugin);

            CancelInventoryItemClick.INSTANCE.register();
            ChangePageInventoryItemClick.INSTANCE.register();

            Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), plugin);
            Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), plugin);
        }
    }

    @ApiStatus.Internal
    public static FancyScheduler getScheduler() {
        return scheduler;
    }
}
