package de.oliver.fancylib;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MessageHelper {
    private static String primaryColor = "#59bdff";
    private static String secondaryColor = "#6696e3";
    private static String successColor = "#81e366";
    private static String warningColor = "#e3ca66";
    private static String errorColor = "#e36666";

    public static @NotNull String transform(@NotNull String message) {
        message = message.replace("<primaryColor>", "<color:" + primaryColor + ">")
                .replace("<secondaryColor>", "<color:" + secondaryColor + ">")
                .replace("<successColor>", "<color:" + successColor + ">")
                .replace("<warningColor>", "<color:" + warningColor + ">")
                .replace("<errorColor>", "<color:" + errorColor + ">");

        return message;
    }

    public static @NotNull Component send(@Nullable CommandSender receiver, @NotNull String message) {
        Component msg = MiniMessage.miniMessage().deserialize(transform(message));
        if (receiver != null) {
            receiver.sendMessage(msg);
        }
        return msg;
    }

    public static @NotNull Component info(@Nullable CommandSender receiver, @NotNull String message) {
        Component msg = MiniMessage.miniMessage().deserialize(transform("<primaryColor>" + message));
        if (receiver != null) {
            receiver.sendMessage(msg);
        }
        return msg;
    }

    public static @NotNull Component success(@Nullable CommandSender receiver, @NotNull String message) {
        Component msg = MiniMessage.miniMessage().deserialize(transform("<successColor>" + message));
        if (receiver != null) {
            receiver.sendMessage(msg);
        }
        return msg;
    }

    public static @NotNull Component warning(@Nullable CommandSender receiver, @NotNull String message) {
        Component msg = MiniMessage.miniMessage().deserialize(transform("<warningColor>" + message));
        if (receiver != null) {
            receiver.sendMessage(msg);
        }
        return msg;
    }

    public static @NotNull Component error(@Nullable CommandSender receiver, @NotNull String message) {
        Component msg = MiniMessage.miniMessage().deserialize(transform("<errorColor>" + message));
        if (receiver != null) {
            receiver.sendMessage(msg);
        }
        return msg;
    }

    public static String getPrimaryColor() {
        return primaryColor;
    }

    public static void setPrimaryColor(String primaryColor) {
        MessageHelper.primaryColor = primaryColor;
    }

    public static String getSecondaryColor() {
        return secondaryColor;
    }

    public static void setSecondaryColor(String secondaryColor) {
        MessageHelper.secondaryColor = secondaryColor;
    }

    public static String getSuccessColor() {
        return successColor;
    }

    public static void setSuccessColor(String successColor) {
        MessageHelper.successColor = successColor;
    }

    public static String getWarningColor() {
        return warningColor;
    }

    public static void setWarningColor(String warningColor) {
        MessageHelper.warningColor = warningColor;
    }

    public static String getErrorColor() {
        return errorColor;
    }

    public static void setErrorColor(String errorColor) {
        MessageHelper.errorColor = errorColor;
    }

    public static Component removeDecoration(Component component, TextDecoration decoration) {
        return component.decoration(decoration, TextDecoration.State.FALSE);
    }
}
