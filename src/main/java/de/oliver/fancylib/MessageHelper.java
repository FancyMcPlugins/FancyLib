package de.oliver.fancylib;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MessageHelper {
    private static String primaryColor = "#6696e3";
    private static String secondaryColor = "#3e5b8a";
    private static String successColor = "#81e366";
    private static String warningColor = "#e3ca66";
    private static String errorColor = "#e36666";

    public static @NotNull Component info(@Nullable CommandSender receiver, @NotNull String message) {
        Component msg = MiniMessage.miniMessage().deserialize("<color:" + primaryColor + ">" + message);
        if (receiver != null) {
            receiver.sendMessage(msg);
        }
        return msg;
    }

    public static @NotNull Component success(@Nullable CommandSender receiver, @NotNull String message) {
        Component msg = MiniMessage.miniMessage().deserialize("<color:" + successColor + ">" + message);
        if (receiver != null) {
            receiver.sendMessage(msg);
        }
        return msg;
    }

    public static @NotNull Component warning(@Nullable CommandSender receiver, @NotNull String message) {
        Component msg = MiniMessage.miniMessage().deserialize("<color:" + warningColor + ">" + message);
        if (receiver != null) {
            receiver.sendMessage(msg);
        }
        return msg;
    }

    public static @NotNull Component error(@Nullable CommandSender receiver, @NotNull String message) {
        Component msg = MiniMessage.miniMessage().deserialize("<color:" + errorColor + ">" + message);
        if (receiver != null) {
            receiver.sendMessage(msg);
        }
        return msg;
    }

    public static @NotNull Component getPrefix(@NotNull Plugin plugin) {
        return MiniMessage.miniMessage().deserialize("<color:#3b3f8c>[</color><gradient:#9666e3:#6696e3>" + plugin.getName() + "</gradient><color:#3b3f8c>]</color>");
    }

    public static String getPrimaryColor() {
        return primaryColor;
    }

    public static String getSecondaryColor() {
        return secondaryColor;
    }

    public static String getSuccessColor() {
        return successColor;
    }

    public static String getWarningColor() {
        return warningColor;
    }

    public static String getErrorColor() {
        return errorColor;
    }

    public static Component removeDecoration(Component component, TextDecoration decoration) {
        return component.decoration(decoration, TextDecoration.State.FALSE);
    }
}
