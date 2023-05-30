package cc.javajobs.wgbridge.infrastructure.abs;

import cc.javajobs.wgbridge.infrastructure.struct.WGRegion;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Callum Johnson
 * @since 11/02/2022 - 15:04
 */
public abstract class AbstractRegion<R> implements WGRegion {

    /**
     * The wrapped Region.
     */
    protected final R region;

    /**
     * Constructor to initialise an AbstractRegion of the type 'R'.
     *
     * @param region to initialise {@link #region} with.
     */
    public AbstractRegion(@NotNull R region) {
        this.region = region;
    }

    /**
     * Method to obtain the wrapped Region.
     *
     * @return WorldGuard Region object.
     */
    public R getRegion() {
        return region;
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
        AbstractRegion<?> that = (AbstractRegion<?>) object;
        return Objects.equals(region, that.region);
    }

    /**
     * Method to generate the hashcode for this AbstractRegion.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(region);
    }

    /**
     * Method to obtain the String representation of the {@link AbstractRegion}.
     *
     * @return String representation of the {@link AbstractRegion}.
     */
    @Override
    public String toString() {
        return "AbstractRegion={regionObject:" + region + '}';
    }

}
