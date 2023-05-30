package cc.javajobs.wgbridge.provider;

import cc.javajobs.wgbridge.infrastructure.WGBridgeAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Callum Johnson
 * @since 11/02/2022 - 14:01
 */
public enum WorldGuardProvider {

    SIX("6.", "v6.WGBridgeV6"),
    SEVEN("7.", "v7.WGBridgeV7");

    private final String versionPrefix, wgWrapper;

    WorldGuardProvider(String versionPrefix, String wgWrapper) {
        this.versionPrefix = versionPrefix;
        this.wgWrapper = wgWrapper;
    }

    /**
     * Method to get the Version Prefix for this version of the WorldGuard Wrapper.
     *
     * @return 6. or 7. etc. etc.
     */
    @NotNull
    public String getVersionPrefix() {
        return versionPrefix;
    }

    /**
     * Method to obtain the WorldGuardWrapper for this version of WorldGuard.
     *
     * @return {@link WGBridgeAPI} instance.
     */
    @Nullable
    public WGBridgeAPI getWorldGuardWrapper() {
        WGBridgeAPI api = null;
        try {
            final Class<?> clazz = Class.forName(wgWrapper);
            if (WGBridgeAPI.class.isAssignableFrom(clazz)) api = (WGBridgeAPI) clazz.getConstructor().newInstance();
        } catch (ReflectiveOperationException ignored) {}
        return api;
    }

    /**
     * Method to determine if this specific version of WorldGuard is running.
     *
     * @return {@code true} if it is.
     */
    public boolean isWorldGuardRunning() {
        final Plugin worldGuard = Bukkit.getPluginManager().getPlugin("WorldGuard");
        return worldGuard != null && worldGuard.isEnabled() && worldGuard.getDescription().getVersion()
                .startsWith(getVersionPrefix());
    }

}
