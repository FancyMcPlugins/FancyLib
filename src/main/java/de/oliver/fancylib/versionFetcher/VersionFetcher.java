package de.oliver.fancylib.versionFetcher;

import org.apache.maven.artifact.versioning.ComparableVersion;

import java.io.IOException;
import java.net.URL;

public interface VersionFetcher {

    ComparableVersion fetchNewestVersion();
    String getDownloadUrl();

    static String getDataFromUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            try (java.util.Scanner scanner = new java.util.Scanner(url.openStream(), "UTF-8").useDelimiter("\\A")) {
                return scanner.hasNext() ? scanner.next() : "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
