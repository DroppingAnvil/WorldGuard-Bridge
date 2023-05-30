package v6.impl;

import cc.javajobs.wgbridge.infrastructure.abs.AbstractRegion;
import cc.javajobs.wgbridge.infrastructure.struct.WGFlag;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Callum Johnson
 * @since 11/02/2022 - 15:20
 */
public class RegionV6 extends AbstractRegion<ProtectedRegion> {

    /**
     * Constructor to initialise an AbstractRegion of the type 'R'.
     *
     * @param region to initialise {@link #region} with.
     */
    public RegionV6(@NotNull ProtectedRegion region) {
        super(region);
    }

    /**
     * Method to obtain the Flags for this Region.
     *
     * @return {@link Set} of {@link WGFlag}.
     */
    @Override
    public @NotNull Set<WGFlag> getFlags() {
        final Map<Flag<?>, Object> flags = region.getFlags();
        return flags.entrySet().stream().map(entry -> new FlagV6(entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());
    }

}
