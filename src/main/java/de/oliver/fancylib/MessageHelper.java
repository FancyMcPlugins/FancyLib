package de.oliver.fancylib;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;

public class MessageHelper {
    public static String pluginName = "FancyLib";
    private static String primaryColor = "#6696e3";
    private static String successColor = "#81e366";
    private static String warningColor = "#e3ca66";
    private static String errorColor = "#e36666";

    public static void info(CommandSender receiver, String message, boolean withPrefix){
        receiver.sendMessage(MiniMessage.miniMessage().deserialize((withPrefix ? getPrefix() : "") + "<color:" + primaryColor + "> " + message));
    }
    public static void info(CommandSender receiver, String message){
        info(receiver, message, true);
    }

    public static void success(CommandSender receiver, String message, boolean withPrefix){
        receiver.sendMessage(MiniMessage.miniMessage().deserialize((withPrefix ? getPrefix() : "") + "<color:" + successColor + "> " + message));
    }
    public static void success(CommandSender receiver, String message){
        success(receiver, message, true);
    }

    public static void warning(CommandSender receiver, String message, boolean withPrefix){
        receiver.sendMessage(MiniMessage.miniMessage().deserialize((withPrefix ? getPrefix() : "") + "<color:" + warningColor + "> " + message));
    }
    public static void warning(CommandSender receiver, String message){
        warning(receiver, message, true);
    }

    public static void error(CommandSender receiver, String message, boolean withPrefix){
        receiver.sendMessage(MiniMessage.miniMessage().deserialize((withPrefix ? getPrefix() : "") + "<color:" + errorColor + "> " + message));
    }
    public static void error(CommandSender receiver, String message){
        error(receiver, message, true);
    }

    public static String getPrefix() {
        return "<color:#3b3f8c>[</color><gradient:#9666e3:#6696e3>" + pluginName + "</gradient><color:#3b3f8c>]</color>";
    }

    public static String getPrimaryColor() {
        return primaryColor;
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
}
