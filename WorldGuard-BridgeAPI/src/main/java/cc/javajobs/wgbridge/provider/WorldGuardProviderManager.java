package cc.javajobs.wgbridge.provider;

import cc.javajobs.wgbridge.infrastructure.WGBridgeAPI;
import org.jetbrains.annotations.Nullable;

/**
 * @author Callum Johnson
 * @since 11/02/2022 - 14:09
 */
public final class WorldGuardProviderManager {

    /**
     * This instance of a {@link WGBridgeAPI} can be null, or non-null.
     */
    private WGBridgeAPI api = null;

    /**
     * Method to loop through the enum {@link WorldGuardProvider} to find the correct provider.
     *
     * @return {@link WorldGuardProvider} which matched or {@code null}.
     */
    @Nullable
    public WorldGuardProvider discover() {
        for (WorldGuardProvider provider : WorldGuardProvider.values()) {
            if (provider.isWorldGuardRunning()) {
                api = provider.getWorldGuardWrapper();
                return provider;
            }
        }
        return null;
    }

    /**
     * Method to obtain the WorldGuard Wrapper.
     *
     * @return {@link #api}.
     */
    @Nullable
    public WGBridgeAPI getWorldGuardBridgeAPI() {
        return api;
    }

}
