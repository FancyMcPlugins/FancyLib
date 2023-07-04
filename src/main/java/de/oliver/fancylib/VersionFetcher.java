package de.oliver.fancylib;

import com.google.gson.Gson;
import org.apache.maven.artifact.versioning.ComparableVersion;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class VersionFetcher {

    private final String apiUrl;
    private final String downloadUrl;
    private ComparableVersion newestVersion = null;


    public VersionFetcher(String apiUrl, String downloadUrl) {
        this.apiUrl = apiUrl;
        this.downloadUrl = downloadUrl;
    }

    private static String getDataFromUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        try (java.util.Scanner scanner = new java.util.Scanner(url.openStream(), "UTF-8").useDelimiter("\\A")) {
            return scanner.hasNext() ? scanner.next() : "";
        }
    }

    /**
     * @return the newest version
     */
    private ComparableVersion fetch(String url) {
        String jsonString = null;
        try {
            jsonString = getDataFromUrl(url);
        } catch (IOException e) {
            return null;
        }

        // Parse the JSON data into a Map
        Gson gson = new Gson();
        Map<String, Object>[] versions = gson.fromJson(jsonString, Map[].class);

        // Get the first version in the list
        Map<String, Object> firstVersion = versions[0];
        String versionNumber = (String) firstVersion.get("version_number");

        ComparableVersion ver = new ComparableVersion(versionNumber);

        newestVersion = ver;
        return ver;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public ComparableVersion getNewestVersion() {
        if (newestVersion != null) return newestVersion;

        newestVersion = fetch(apiUrl);
        if (newestVersion != null) return newestVersion;

        return null;
    }
}
