package cc.javajobs.wgbridge.infrastructure;

import cc.javajobs.wgbridge.infrastructure.struct.WGRegionSet;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Callum Johnson
 * @since 11/02/2022 - 14:03
 */
public interface WGBridgeAPI {

    /**
     * Method to get Regions from the location.
     *
     * @param location to get the Regions from.
     * @return {@link WGRegionSet}.
     */
    @Nullable
    WGRegionSet getRegions(@NotNull Location location);

    boolean checkForRegionsInChunk(Chunk chunk);

    /**
     * Method to get Regions from the block.
     *
     * @param block to get the Regions from.
     * @return {@link WGRegionSet}.
     */
    @Nullable
    default WGRegionSet getRegions(@NotNull Block block) {
        return getRegions(block.getLocation());
    }



    default boolean checkRegionForFlagState(Location location, String flagName, String value) {
        return getRegions(location).checkValue(flagName, value);
    }

    default boolean canPlayerBuild(Location location) {
        return getRegions(location).checkValue("build", "allow");
    }

}
