package de.oliver.fancylib.featureFlags;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FeatureFlagConfig {

    private final Plugin plugin;
    private final File configFile;
    private final List<FeatureFlag> featureFlags;

    public FeatureFlagConfig(Plugin plugin) {
        this.plugin = plugin;
        this.configFile = new File("plugins/" + plugin.getName() + "/featureFlags.yml");
        this.featureFlags = new ArrayList<>();
    }

    public void load(){
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        for (FeatureFlag featureFlag : featureFlags) {
            if(config.isSet("feature-flags." + featureFlag.getName())){
               continue;
            }

            config.set("feature-flags." + featureFlag.getName(), false);
        }

        for (String flagName : config.getConfigurationSection("feature-flags").getKeys(false)) {
            boolean enabled = config.getBoolean("feature-flags." + flagName, false);
            FeatureFlag flag = getFeatureFlag(flagName);
            if(flag == null){
                flag = new FeatureFlag(flagName, false);
                featureFlags.add(flag);
            }

            flag.setEnabled(enabled);
        }

        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FeatureFlag getFeatureFlag(String name){
        for (FeatureFlag featureFlag : featureFlags) {
            if(featureFlag.getName().equalsIgnoreCase(name.toLowerCase())){
                return featureFlag;
            }
        }

        return null;
    }

    public void addFeatureFlag(FeatureFlag featureFlag){
        if(!featureFlags.contains(featureFlag)){
            featureFlags.add(featureFlag);
        }
    }

}
