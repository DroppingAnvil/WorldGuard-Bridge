package cc.javajobs.wgbridge.infrastructure.struct;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * @author Callum Johnson
 * @since 11/02/2022 - 14:30
 */
public interface WGRegionSet {

    /**
     * Method to obtain all regions in the set.
     *
     * @return {@link Set} of {@link WGRegion}.
     */
    @NotNull
    Set<WGRegion> getRegions();

    /**
     * Method to determine if the Flag value (specified by name) matches the given value.
     *
     * @param flagName to get the value from the region for.
     * @param value to compare to.
     * @return {@code true} if it is equal.
     */
    default boolean checkValue(@NotNull String flagName, @NotNull Object value) {
        return getRegions().stream().allMatch(region -> region.checkValue(flagName, value));
    }

}
