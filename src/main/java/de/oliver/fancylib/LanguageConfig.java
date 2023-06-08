package de.oliver.fancylib;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LanguageConfig {

    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\{([^}]+)\\}");

    private final Plugin plugin;
    private File configFile;
    private final Map<String, String> lang;
    private final Map<String, String> defaultLang;

    public LanguageConfig(Plugin plugin) {
        this.plugin = plugin;
        this.configFile = new File("plugins/" + plugin.getName() + "/lang.yml");
        this.lang = new HashMap<>();
        this.defaultLang = new HashMap<>();
    }

    public void addDefaultLang(String key, String message){
        defaultLang.put(key, message);
    }

    public String get(String key, String... placeholders){
        String message = lang.getOrDefault(key, "Error: message not found");

        Matcher matcher = PLACEHOLDER_PATTERN.matcher(message);
        int i = 0;
        while(matcher.find()){
            String placeholder = matcher.group();
            message = message.replace(placeholder, placeholders[i]);

            i++;

            if(i >= placeholders.length){
                break;
            }
        }

        return message;
    }

    public void load(){
        lang.clear();

        if(!configFile.exists()){
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        for (Map.Entry<String, String> entry : defaultLang.entrySet()) {
            if(!config.isSet("messages." + entry.getKey())){
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
