package cc.javajobs.wgbridge;

import cc.javajobs.wgbridge.infrastructure.WGBridgeAPI;
import cc.javajobs.wgbridge.provider.WorldGuardProvider;
import cc.javajobs.wgbridge.provider.WorldGuardProviderManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Callum Johnson
 * @since 11/02/2022 - 13:44
 */
public final class WorldGuardBridge {

    private static WorldGuardBridge instance;
    private WGBridgeAPI worldGuardWrapper;

    /**
     * Method to attach WorldGuardBridge to WorldGuard.
     *
     * @param plugin   to connect for.
     * @param messages to be displayed or not.
     */
    public void connect(@NotNull JavaPlugin plugin, boolean messages) {
        final Plugin worldGuard = Bukkit.getPluginManager().getPlugin("WorldGuard");
        if (worldGuard == null || !worldGuard.isEnabled()) {
            if (messages) plugin.getLogger().warning("Failed to find WorldGuard. WorldGuardBridge shutting down.");
        } else {
            if (messages)
                plugin.getLogger().info("Found WorldGuard v" + worldGuard.getDescription().getVersion() + "!");
            final WorldGuardProviderManager manager = new WorldGuardProviderManager();
            final WorldGuardProvider discover = manager.discover();
            if (discover == null) {
                if (messages) plugin.getLogger().severe("Failed to hook into WorldGuard!");
                worldGuardWrapper = null;
            } else {
                if (messages) plugin.getLogger().info("Hooked into WorldGuard!");
                worldGuardWrapper = discover.getWorldGuardWrapper();
            }
        }
        if (instance == null) instance = this;
    }

    /**
     * Method to obtain the nullable WorldGuardBridge instance.
     *
     * @return {@link #instance} or {@code null}.
     */
    @Nullable
    public static WorldGuardBridge getInstance() {
        return instance;
    }

    /**
     * Method to obtain the WGBridgeAPI once it has been hooked into.
     *
     * @return {@link WGBridgeAPI}.
     */
    @Nullable
    public WGBridgeAPI getAPI() {
        return worldGuardWrapper;
    }

}
