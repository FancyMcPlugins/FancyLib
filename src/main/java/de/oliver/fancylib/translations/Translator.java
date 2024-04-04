package de.oliver.fancylib.translations;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Translator {

    private final TextConfig textConfig;
    private final List<Language> languages;
    private Language selectedLanguage;
    private Language fallbackLanguage;

    public Translator(TextConfig textConfig) {
        this.textConfig = textConfig;
        this.languages = new ArrayList<>();
    }

    public void loadLanguages(String pluginFolderPath) {
        languages.clear();
        selectedLanguage = null;
        fallbackLanguage = null;

        File langFolder = new File(pluginFolderPath + "/languages");
        if(!langFolder.exists()) {
            if (!langFolder.mkdirs()) {
                throw new RuntimeException("Could not create languages folder");
            }
        }

        File enFile = new File(langFolder, "en.yml");
        if (!enFile.exists()) {
            try {
                InputStream enDefault = getClass().getResourceAsStream("/languages/en.yml");
                if (enDefault == null) {
                    throw new RuntimeException("Could not find default language file");
                }

                Files.copy(enDefault, enFile.toPath());
            } catch (IOException e) {
                throw new RuntimeException("Could not copy default language file");
            }
        }

        File[] langFiles = langFolder.listFiles();
        if (langFiles == null) {
            throw new RuntimeException("Could not list language files");
        }

        for (File langFile : langFiles) {
            languages.add(loadLanguageFile(langFile));
        }

        fallbackLanguage = languages.stream()
                .filter(language -> language.getLanguageCode().equals("en"))
                .findFirst()
                .orElse(null);

        if (fallbackLanguage == null) {
            throw new RuntimeException("Could not find fallback language");
        }
    }

    private Language loadLanguageFile(File langFile) {
        String fileName = langFile.getName();
        String languageCode = fileName.substring(0, fileName.lastIndexOf('.'));

        YamlConfiguration lang = YamlConfiguration.loadConfiguration(langFile);
        String languageName = lang.getString("language_name", languageCode);

        Language language = new Language(languageCode, languageName);


        ConfigurationSection messages = lang.getConfigurationSection("messages");
        if(messages == null) {
            throw new RuntimeException("Language file " + langFile.getName() + " does not contain a messages section");
        }

        for (String key : messages.getKeys(true)) {
            language.addMessage(key, messages.getString(key));
        }

        return language;
    }

    public String translateRaw(String key) {
        String message = selectedLanguage.getMessage(key);

        if (message == null) {
            message = fallbackLanguage.getMessage(key);
        }

        return message;
    }

    public Message translate(String key) {
        return new Message(textConfig, translateRaw(key));
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public Language getSelectedLanguage() {
        return selectedLanguage;
    }

    public Translator setSelectedLanguage(Language selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
        return this;
    }

    public Language getFallbackLanguage() {
        return fallbackLanguage;
    }
}
