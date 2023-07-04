package de.oliver.fancylib.serverSoftware;

import de.oliver.fancylib.serverSoftware.schedulers.BukkitScheduler;
import de.oliver.fancylib.serverSoftware.schedulers.FancyScheduler;
import de.oliver.fancylib.serverSoftware.schedulers.FoliaScheduler;
import io.papermc.paper.plugin.configuration.PluginMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ServerSoftware {

    public static boolean isFolia() {
        return Arrays.stream(PluginMeta.class.getDeclaredMethods())
                .map(Method::getName)
                .anyMatch(s -> s.equals("isFoliaSupported"));
    }

    public static boolean isPaper() {
        try {
            Class.forName("io.papermc.paper.event.player.AsyncChatEvent");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isBukkit() {
        try {
            Class.forName("org.bukkit.Bukkit");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static FancyScheduler getCorrectScheduler(JavaPlugin plugin) {
        if (isFolia()) {
            return new FoliaScheduler(plugin);
        }

        return new BukkitScheduler(plugin);
    }

}
