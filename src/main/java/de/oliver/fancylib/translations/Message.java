package de.oliver.fancylib.translations;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message {

    private final TextConfig config;
    private String message;

    public Message(TextConfig config, String message) {
        this.config = config;
        this.message = message;

        replace("primaryColor", config.primaryColor());
        replace("secondaryColor", config.secondaryColor());
        replace("successColor", config.successColor());
        replace("warningColor", config.warningColor());
        replace("errorColor", config.errorColor());
    }

    public Message replace(String placeholder, String replacement) {
        message = message
                .replace("{" + placeholder + "}", replacement)
                .replace("%" + placeholder + "%", replacement);

        return this;
    }

    public Message withPrefix() {
        message = config.prefix() + message;
        return this;
    }

    public Message primary() {
        message = "<color:" + config.primaryColor() + ">" + message;
        return this;
    }

    public Message secondary() {
        message = "<color:" + config.secondaryColor() + ">" + message;
        return this;
    }

    public Message success() {
        message = "<color:" + config.successColor() + ">" + message;
        return this;
    }

    public Message warning() {
        message = "<color:" + config.warningColor() + ">" + message;
        return this;
    }

    public Message error() {
        message = "<color:" + config.errorColor() + ">" + message;
        return this;
    }

    public Message applyCustomPlaceholders() {
        // TODO: add ChatColorHandler support

        return this;
    }

    public String build() {
        return message;
    }

    public Component buildComponent() {
        return MiniMessage.miniMessage().deserialize(message);
    }

    public void send(CommandSender receiver) {
        receiver.sendMessage(buildComponent());
    }

    public void actionbar(Player receiver) {
        receiver.sendActionBar(buildComponent());
    }
}
