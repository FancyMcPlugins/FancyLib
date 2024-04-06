package de.oliver.fancylib.translations.message;

import de.oliver.fancylib.translations.TextConfig;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SimpleMessage extends Message {

    private String message;

    public SimpleMessage(TextConfig config, String message) {
        super(config);
        this.message = message;

        applyColorPlaceholders();
    }

    public SimpleMessage replace(String placeholder, String replacement) {
        message = message
                .replace("{" + placeholder + "}", replacement)
                .replace("%" + placeholder + "%", replacement);

        return this;
    }

    public SimpleMessage withPrefix() {
        message = config.prefix() + message;
        return this;
    }

    public SimpleMessage primary() {
        message = "<color:" + config.primaryColor() + ">" + message;
        return this;
    }

    public SimpleMessage secondary() {
        message = "<color:" + config.secondaryColor() + ">" + message;
        return this;
    }

    public SimpleMessage success() {
        message = "<color:" + config.successColor() + ">" + message;
        return this;
    }

    public SimpleMessage warning() {
        message = "<color:" + config.warningColor() + ">" + message;
        return this;
    }

    public SimpleMessage error() {
        message = "<color:" + config.errorColor() + ">" + message;
        return this;
    }

    public SimpleMessage applyCustomPlaceholders() {
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

    public String getMessage() {
        return message;
    }
}
