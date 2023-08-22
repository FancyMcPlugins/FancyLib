package de.oliver.fancylib.featureFlags;

public class FeatureFlag {

    private final String name;
    private boolean enabled;
    private final boolean forceDisabled;

    public FeatureFlag(String name, boolean forceDisabled) {
        this.name = name;
        this.forceDisabled = forceDisabled;
        this.enabled = false;
    }

    public boolean isEnabled() {
        if(forceDisabled) return false;

        return enabled;
    }

    public String getName() {
        return name;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isForceDisabled() {
        return forceDisabled;
    }
}
