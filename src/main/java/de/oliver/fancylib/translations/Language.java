package de.oliver.fancylib.translations;

import de.oliver.fancylib.translations.message.Message;

import java.util.HashMap;
import java.util.Map;

public class Language {

    private final String languageCode;
    private final String languageName;
    private final Map<String, Message> messages;

    public Language(String languageCode, String languageName) {
        this.languageCode = languageCode;
        this.languageName = languageName;
        this.messages = new HashMap<>();
    }

    public void addMessage(String key, Message message) {
        messages.put(key, message);
    }

    public Message getMessage(String key) {
        return messages.getOrDefault(key, null);
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getLanguageName() {
        return languageName;
    }
}
