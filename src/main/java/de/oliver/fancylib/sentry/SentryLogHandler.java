package de.oliver.fancylib.sentry;

import io.sentry.Sentry;
import io.sentry.SentryEvent;
import io.sentry.SentryLevel;
import io.sentry.protocol.Message;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.stream.Collectors;

public class SentryLogHandler extends ConsoleHandler {

    private final JavaPlugin plugin;

    public SentryLogHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void publish(LogRecord record) {
        Throwable exception = record.getThrown();
        if (exception == null) return;

        boolean hasFancyError = false;
        for (StackTraceElement e : exception.getStackTrace()) {
            if (e.toString().contains("de.oliver.fancy")) {
                hasFancyError = true;
                break;
            }
        }

        if (!hasFancyError) return;

        String message = getFormatter().formatMessage(record);
        SentryEvent event = new SentryEvent(exception);
        event.setLevel(SentryLevel.ERROR);
        Message sentryMessage = new Message();
        sentryMessage.setMessage(message);
        event.setMessage(sentryMessage);
        event.setServerName(plugin.getServer().getName());

        event.setTag("server_version", plugin.getServer().getBukkitVersion());
        event.setTag("server_version_2", plugin.getServer().getVersion());
        event.setTag("online_mode", String.valueOf(plugin.getServer().getOnlineMode()));
        event.setExtra("online_players", plugin.getServer().getOnlinePlayers().size());
        event.setExtra("plugins", Arrays.stream(plugin.getServer().getPluginManager().getPlugins()).map(Plugin::getName).map(s -> s + " ").collect(Collectors.joining()));
        event.setExtra("plugin_version", plugin.getDescription().getVersion());

        StringBuilder systemName = new StringBuilder();
        systemName.append(System.getProperty("os.name"));
        systemName.append(" ");
        systemName.append(System.getProperty("os.version"));
        systemName.append(" ");
        systemName.append(System.getProperty("os.arch"));
        event.setEnvironment(systemName.toString());
        Sentry.captureEvent(event);
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() throws SecurityException {
    }
}
