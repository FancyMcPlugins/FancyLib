package de.oliver.fancylib.versionFetcher;

import org.apache.maven.artifact.versioning.ComparableVersion;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public interface VersionFetcher {

    ComparableVersion fetchNewestVersion();
    String getDownloadUrl();

    static String getDataFromUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            connection.setConnectTimeout(300);
            Scanner scanner = new Scanner(connection.getInputStream(), StandardCharsets.UTF_8).useDelimiter("\\A");

            return scanner.hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
