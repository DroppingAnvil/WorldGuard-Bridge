package v6;

import cc.javajobs.wgbridge.infrastructure.WGBridgeAPI;
import cc.javajobs.wgbridge.infrastructure.struct.WGRegionSet;
import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import v6.impl.RegionSetV6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.sk89q.worldguard.bukkit.WGBukkit.getRegionManager;

/**
 * @author Callum Johnson
 * @since 11/02/2022 - 14:59
 */
public class WGBridgeV6 implements WGBridgeAPI {

    private WorldGuardPlugin plugin;

    public WGBridgeV6() {
        final Plugin worldGuard = Bukkit.getPluginManager().getPlugin("WorldGuard");
        if (worldGuard != null && worldGuard.isEnabled() && worldGuard instanceof WorldGuardPlugin) {
            plugin = (WorldGuardPlugin) worldGuard;
        }
    }

    /**
     * Method to get Regions from the location.
     *
     * @param location to get the Regions from.
     * @return {@link WGRegionSet}.
     */
    @Override
    public @Nullable WGRegionSet getRegions(@NotNull Location location) {
        return new RegionSetV6(plugin.getRegionManager(location.getWorld()).getApplicableRegions(location));
    }

    @Override
    public boolean checkForRegionsInChunk(Chunk chunk) {
        World world = chunk.getWorld();
        int minChunkX = chunk.getX() << 4;
        int minChunkZ = chunk.getZ() << 4;
        int maxChunkX = minChunkX + 15;
        int maxChunkZ = minChunkZ + 15;

        int worldHeight = world.getMaxHeight(); // Allow for heights other than default

        BlockVector minChunk = new BlockVector(minChunkX, 0, minChunkZ);
        BlockVector maxChunk = new BlockVector(maxChunkX, worldHeight, maxChunkZ);

        ProtectedCuboidRegion region = new ProtectedCuboidRegion("wgfactionoverlapcheck", minChunk, maxChunk);

        Collection<ProtectedRegion> allregionslist = new ArrayList<>(getRegionManager(world).getRegions().values());
        List<ProtectedRegion> overlaps = region.getIntersectingRegions(allregionslist);
        return overlaps != null && !overlaps.isEmpty();
    }

}
