package de.oliver.fancylib;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHelper {

    public static Object getOrDefault(FileConfiguration config, String path, Object defaultVal) {
        if (!config.contains(path)) {
            config.set(path, defaultVal);
            return defaultVal;
        }

        return config.get(path);
    }

}
