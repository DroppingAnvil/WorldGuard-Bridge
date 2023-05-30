package cc.javajobs.wgbridge.infrastructure.abs;

import cc.javajobs.wgbridge.infrastructure.struct.WGRegionSet;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Callum Johnson
 * @since 11/02/2022 - 15:10
 */
public abstract class AbstractRegionSet<RS> implements WGRegionSet {

    /**
     * The wrapped RegionSet.
     */
    protected final RS regionSet;

    /**
     * Constructor to initialise an AbstractRegionSet of the type 'RS'.
     *
     * @param regionSet to initialise {@link #regionSet} with.
     */
    public AbstractRegionSet(@NotNull RS regionSet) {
        this.regionSet = regionSet;
    }

    /**
     * Method to obtain the wrapped RegionSet.
     *
     * @return WorldGuard RegionSet object.
     */
    public RS getRegionSet() {
        return regionSet;
    }

    /**
     * Method to determine if an object is equal to this object.
     *
     * @param object to test.
     * @return {@code true} if it is.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final AbstractRegionSet<?> that = (AbstractRegionSet<?>) object;
        return Objects.equals(regionSet, that.regionSet);
    }

    /**
     * Method to generate the hashcode for this AbstractRegionSet.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(regionSet);
    }

    /**
     * Method to obtain the String representation of the {@link AbstractRegionSet}.
     *
     * @return String representation of the {@link AbstractRegionSet}.
     */
    @Override
    public String toString() {
        return "AbstractRegionSet={regionSetObject:" + regionSet + '}';
    }

}
