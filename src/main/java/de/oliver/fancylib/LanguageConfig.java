package de.oliver.fancylib;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Deprecated
public class LanguageConfig {

    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\{([^}]+)\\}");

    private final Plugin plugin;
    private final Map<String, String> lang;
    private final Map<String, String> defaultLang;
    private File configFile;

    @Deprecated
    public LanguageConfig(Plugin plugin) {
        this.plugin = plugin;
        this.configFile = new File("plugins/" + plugin.getName() + "/lang.yml");
        this.lang = new HashMap<>();
        this.defaultLang = new HashMap<>();
    }

    @Deprecated
    public void addDefaultLang(String key, String message) {
        defaultLang.put(key, message);
    }

    /**
     * @param placeholders format: placeholder1, replacement1, placeholder2, replacement2 ...
     */
    @Deprecated
    public String get(String key, String... placeholders) {
        String message = lang.getOrDefault(key, "Error: message not found");

        for (int i = 0; i < placeholders.length; i += 2) {
            String placeholder = placeholders[i];
            String replacement = placeholders[i + 1];

            message = message.replace("{" + placeholder + "}", replacement);
        }

        return message;
    }

    @Deprecated
    public void load() {
        lang.clear();

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        config.options().width(1000);

        for (Map.Entry<String, String> entry : defaultLang.entrySet()) {
            if (!config.isSet("messages." + entry.getKey())) {
                config.set("messages." + entry.getKey(), entry.getValue());
            }
        }

        for (String key : config.getConfigurationSection("messages").getKeys(false)) {
            String message = config.getString("messages." + key);
            lang.put(key, message);
        }

        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
