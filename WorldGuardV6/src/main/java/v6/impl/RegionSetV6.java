package v6.impl;

import cc.javajobs.wgbridge.infrastructure.abs.AbstractRegionSet;
import cc.javajobs.wgbridge.infrastructure.struct.WGRegion;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Callum Johnson
 * @since 11/02/2022 - 15:19
 */
public class RegionSetV6 extends AbstractRegionSet<ApplicableRegionSet> {

    /**
     * Constructor to initialise an AbstractRegionSet of the type 'RS'.
     *
     * @param regionSet to initialise {@link #regionSet} with.
     */
    public RegionSetV6(@NotNull ApplicableRegionSet regionSet) {
        super(regionSet);
    }

    /**
     * Method to obtain all regions in the set.
     *
     * @return {@link Set} of {@link WGRegion}.
     */
    @Override
    public @NotNull Set<WGRegion> getRegions() {
        return regionSet.getRegions().stream().map(RegionV6::new).collect(Collectors.toSet());
    }

}
