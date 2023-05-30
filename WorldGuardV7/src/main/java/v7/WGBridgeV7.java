package v7;

import cc.javajobs.wgbridge.infrastructure.WGBridgeAPI;
import cc.javajobs.wgbridge.infrastructure.struct.WGRegionSet;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.internal.platform.WorldGuardPlatform;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.BlockVector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import v7.impl.RegionSetV7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Callum Johnson
 * @since 11/02/2022 - 14:59
 */
public class WGBridgeV7 implements WGBridgeAPI {

    /**
     * Method to get Regions from the location.
     *
     * @param location to get the Regions from.
     * @return {@link WGRegionSet}.
     */
    @Override
    public @Nullable WGRegionSet getRegions(@NotNull Location location) {
        final WorldGuard instance = WorldGuard.getInstance();
        final WorldGuardPlatform platform = instance.getPlatform();
        final RegionContainer regionContainer = platform.getRegionContainer();
        final BukkitWorld world = new BukkitWorld(location.getWorld());
        final RegionManager regionManager = regionContainer.get(world);
        if (regionManager == null) return null;
        final BlockVector3 position = BlockVector3.at(location.getX(), location.getY(), location.getZ());
        return new RegionSetV7(regionManager.getApplicableRegions(position));
    }

    @Override
    public boolean checkForRegionsInChunk(Chunk chunk) {
        World world = chunk.getWorld();
        int minChunkX = chunk.getX() << 4;
        int minChunkZ = chunk.getZ() << 4;
        int maxChunkX = minChunkX + 15;
        int maxChunkZ = minChunkZ + 15;

        int worldHeight = world.getMaxHeight(); // Allow for heights other than default

        BlockVector3 minChunk = BlockVector3.at(minChunkX, 0, minChunkZ);
        BlockVector3 maxChunk = BlockVector3.at(maxChunkX, worldHeight, maxChunkZ);

        ProtectedCuboidRegion region = new ProtectedCuboidRegion("wgfactionoverlapcheck", minChunk, maxChunk);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(BukkitAdapter.adapt(world));

        Collection<ProtectedRegion> allregionslist = new ArrayList<>(regions.getRegions().values());
        List<ProtectedRegion> overlaps = region.getIntersectingRegions(allregionslist);
        return overlaps != null && !overlaps.isEmpty();
    }

}
