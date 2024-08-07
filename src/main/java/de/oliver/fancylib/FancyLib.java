package de.oliver.fancylib;

import de.oliver.fancylib.checksumChecker.ChecksumFetcher;
import de.oliver.fancylib.gui.inventoryClick.InventoryClickListener;
import de.oliver.fancylib.gui.inventoryClick.impl.CancelInventoryItemClick;
import de.oliver.fancylib.gui.inventoryClick.impl.ChangePageInventoryItemClick;
import de.oliver.fancylib.itemClick.PlayerInteractListener;
import de.oliver.fancylib.serverSoftware.ServerSoftware;
import de.oliver.fancylib.serverSoftware.schedulers.BukkitScheduler;
import de.oliver.fancylib.serverSoftware.schedulers.FancyScheduler;
import de.oliver.fancylib.serverSoftware.schedulers.FoliaScheduler;
import org.apache.maven.artifact.versioning.ComparableVersion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus;

import java.io.File;

public class FancyLib {

    public static final ComparableVersion VERSION = new ComparableVersion("1.0.3");

    private static JavaPlugin plugin;
    private static FancyScheduler scheduler;

    public static Plugin getPlugin() {
        return plugin;
    }

    /**
     * @param pluginJarFile you can get this by calling JavaPlugin#getJarFile
     */
    public static void setPlugin(JavaPlugin plugin, File pluginJarFile) {
        if (FancyLib.plugin != null) {
            return;
        }

        FancyLib.plugin = plugin;
        scheduler = ServerSoftware.isFolia()
                ? new FoliaScheduler(plugin)
                : new BukkitScheduler(plugin);
    }

    private static void checkChecksums() {
        String actualChecksum = ChecksumFetcher.getChecksum(plugin.getName(), plugin.getDescription().getVersion());
        String fileChecksum = FileUtils.getSHA256Checksum(FileUtils.findFirstFileByName(new File("plugins"), plugin.getName()));

        if (!actualChecksum.equals("N/A") && !fileChecksum.equals("N/A")) {
            if (!actualChecksum.equals(fileChecksum)) {
                plugin.getLogger().warning("""
                        ----------------------------------------------------------------
                        [!]
                        [!]
                        [!]
                        [!] Potential security risk detected!!
                        [!]
                        [!]
                        [!]
                        [!] The checksum of the plugin jar does not match the official checksum!
                        [!] This version might be modified and could contain maleware!
                        [!] Please download the plugin from the official source!
                        [!] Official checksum: %actualChecksum%
                        [!] This file's checksum: %fileChecksum%
                        [!]
                        [!]
                        [!]
                        [!]
                        [!]
                        [!]
                        [!]
                        ----------------------------------------------------------------
                        """
                        .replace("%actualChecksum%", actualChecksum)
                        .replace("%fileChecksum%", fileChecksum)
                );
                Bukkit.getPluginManager().disablePlugin(plugin);
            }
        }
    }

    /**
     * Registers the listeners for the inventory click and player interact events.
     */
    public static void registerListeners() {
        CancelInventoryItemClick.INSTANCE.register();
        ChangePageInventoryItemClick.INSTANCE.register();

        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), plugin);
    }

    @ApiStatus.Internal
    public static FancyScheduler getScheduler() {
        return scheduler;
    }
}
