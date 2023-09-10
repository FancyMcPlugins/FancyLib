package de.oliver.fancylib;

import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class FileUtils {

    public String readResource(String name) {
        URL url = getClass().getClassLoader().getResource(name);
        if (url == null) {
            return null;
        }
        URLConnection connection = null;
        try {
            connection = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.setUseCaches(false);
        try (InputStream inputStream = connection.getInputStream()) {
            byte[] file_raw = new byte[inputStream.available()];
            inputStream.read(file_raw);
            inputStream.close();
            return new String(file_raw, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveFile(Plugin plugin, String name) {
        URL url = getClass().getClassLoader().getResource(name);
        if (url == null) {
            return;
        }
        File file = new File(plugin.getDataFolder() + "/" + name);
        if (file.exists()) return;
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        URLConnection connection = null;
        try {
            connection = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.setUseCaches(false);
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            int read;
            InputStream inputStream = connection.getInputStream();
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
