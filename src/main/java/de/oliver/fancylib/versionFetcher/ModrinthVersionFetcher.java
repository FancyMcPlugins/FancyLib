package de.oliver.fancylib.versionFetcher;

import com.google.gson.Gson;
import org.apache.maven.artifact.versioning.ComparableVersion;

import java.util.Map;

public class ModrinthVersionFetcher implements VersionFetcher{

    private final String pluginName;
    private ComparableVersion newestVersion;

    public ModrinthVersionFetcher(String pluginName) {
        this.pluginName = pluginName;
        this.newestVersion = null;
    }

    @Override
    public ComparableVersion fetchNewestVersion() {
        if(newestVersion != null) return newestVersion;

        String jsonString = de.oliver.fancylib.versionFetcher.VersionFetcher.getDataFromUrl("https://api.modrinth.com/v2/project/" + pluginName.toLowerCase() + "/version");
        if(jsonString == null || jsonString.isEmpty()){
            return null;
        }

        Gson gson = new Gson();
        Map<String, Object>[] versions = gson.fromJson(jsonString, Map[].class);

        Map<String, Object> firstVersion = versions[0];
        String versionNumber = (String) firstVersion.get("version_number");

        newestVersion = new ComparableVersion(versionNumber);
        return newestVersion;
    }

    @Override
    public String getDownloadUrl() {
        return "https://modrinth.com/plugin/" + pluginName;
    }
}
