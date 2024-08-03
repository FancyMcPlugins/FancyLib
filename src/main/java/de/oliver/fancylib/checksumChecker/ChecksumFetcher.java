package de.oliver.fancylib.checksumChecker;

import de.oliver.fancylib.versionFetcher.VersionFetcher;

public class ChecksumFetcher {

    private static final String CHECKSUM_URL = "https://raw.githubusercontent.com/FancyMcPlugins/%plugin%/main/checksums.txt";

    public static String getChecksum(String plugin, String version) {
        String data = VersionFetcher.getDataFromUrl(CHECKSUM_URL.replace("%plugin%", plugin));
        String[] lines = data.split("\n");
        for (String line : lines) {

            String[] parts = line.split(" ");
            if (parts.length != 2) {
                continue;
            }

            if (parts[0].equals(version)) {
                return parts[1];
            }
        }

        return "N/A";
    }

}
