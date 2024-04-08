package de.oliver.fancylib.translations.message;

import de.oliver.fancylib.translations.TextConfig;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class Message {

    protected final TextConfig config;
    protected TagResolver.Builder tagResolverBuilder = TagResolver.builder();

    public Message(TextConfig config) {
        this.config = config;
    }

    public void addTagResolver(TagResolver resolver) {
        tagResolverBuilder = tagResolverBuilder.resolver(resolver);
    }

    protected void applyColorPlaceholders() {
        replace("primaryColor", "<color:" + config.primaryColor() + ">");
        replace("secondaryColor", "<color:" + config.secondaryColor() + ">");
        replace("successColor", "<color:" + config.successColor() + ">");
        replace("warningColor", "<color:" + config.warningColor() + ">");
        replace("errorColor", "<color:" + config.errorColor() + ">");
    }

    /**
     * Replaces a placeholder in the message
     *
     * @param placeholder the placeholder to replace
     * @param replacement the replacement
     * @return this message
     */
    public abstract Message replace(String placeholder, String replacement);

    /**
     * Adds the prefix to the message
     *
     * @return this message
     */
    public abstract Message withPrefix();

    /**
     * Adds the primary color to the message
     *
     * @return this message
     */
    public abstract Message primary();

    /**
     * Adds the secondary color to the message
     *
     * @return this message
     */
    public abstract Message secondary();

    /**
     * Adds the success color to the message
     *
     * @return this message
     */
    public abstract Message success();

    /**
     * Adds the warning color to the message
     *
     * @return this message
     */
    public abstract Message warning();

    /**
     * Adds the error color to the message
     *
     * @return this message
     */
    public abstract Message error();

    /**
     * Applies custom placeholders to the message
     *
     * @return this message
     */
    public abstract Message applyCustomPlaceholders();

    /**
     * Builds the message as a component
     *
     * @return the built component
     */
    public abstract Component buildComponent();

    /**
     * Copies the message
     *
     * @return the copied message
     */
    public abstract Message copy();

    public void send(CommandSender receiver) {
        Component msg = buildComponent();
        if (Component.empty().equals(msg)) {
            return;
        }

        receiver.sendMessage(msg);
    }

    public void actionbar(Player receiver) {
        Component msg = buildComponent();
        if (Component.empty().equals(msg)) {
            return;
        }

        receiver.sendActionBar(msg);
    }
}
