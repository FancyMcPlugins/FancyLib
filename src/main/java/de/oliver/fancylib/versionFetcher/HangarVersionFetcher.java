package de.oliver.fancylib.versionFetcher;

import org.apache.maven.artifact.versioning.ComparableVersion;

public class HangarVersionFetcher implements VersionFetcher{

    private final String pluginName;
    private ComparableVersion newestVersion;

    public HangarVersionFetcher(String pluginName) {
        this.pluginName = pluginName;
        this.newestVersion = null;
    }

    @Override
    public ComparableVersion fetchNewestVersion() {
        if(newestVersion != null) return newestVersion;

        String versionStr = VersionFetcher.getDataFromUrl("https://hangar.papermc.io/api/v1/projects/" + pluginName + "/latestrelease");
        if(versionStr == null || versionStr.isEmpty()){
            return null;
        }

        newestVersion = new ComparableVersion(versionStr);
        return newestVersion;
    }

    @Override
    public String getDownloadUrl() {
        return "https://hangar.papermc.io/Oliver/" + pluginName;
    }
}
