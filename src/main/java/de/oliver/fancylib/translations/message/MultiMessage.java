package de.oliver.fancylib.translations.message;

import de.oliver.fancylib.translations.TextConfig;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.ArrayList;
import java.util.List;

public class MultiMessage extends Message {

    private final List<String> messages;

    public MultiMessage(TextConfig config, List<String> messages) {
        super(config);
        this.messages = new ArrayList<>(messages);

        applyColorPlaceholders();
    }

    @Override
    public Message replace(String placeholder, String replacement) {
        messages.replaceAll(s -> s
                .replace("{" + placeholder + "}", replacement)
                .replace("%" + placeholder + "%", replacement));

        return this;
    }

    @Override
    public Message withPrefix() {
        messages.replaceAll(s -> config.prefix() + s);
        return this;
    }

    @Override
    public Message primary() {
        messages.replaceAll(s -> "<color:" + config.primaryColor() + ">" + s);
        return this;
    }

    @Override
    public Message secondary() {
        messages.replaceAll(s -> "<color:" + config.secondaryColor() + ">" + s);
        return this;
    }

    @Override
    public Message success() {
        messages.replaceAll(s -> "<color:" + config.successColor() + ">" + s);
        return this;
    }

    @Override
    public Message warning() {
        messages.replaceAll(s -> "<color:" + config.warningColor() + ">" + s);
        return this;
    }

    @Override
    public Message error() {
        messages.replaceAll(s -> "<color:" + config.errorColor() + ">" + s);
        return this;
    }

    @Override
    public Message applyCustomPlaceholders() {
        //TODO: add ChatColorHandler support
        return this;
    }

    @Override
    public Component buildComponent() {
        String joined = String.join("\n", messages);
        return MiniMessage.miniMessage().deserialize(joined);
    }

    public String build() {
        return String.join("\n", messages);
    }

    public List<String> getRawMessages() {
        return messages;
    }

    public List<SimpleMessage> getSimpleMessages() {
        List<SimpleMessage> messages = new ArrayList<>();
        for (String s : this.messages) {
            messages.add(new SimpleMessage(config, s));
        }

        return messages;
    }
}
