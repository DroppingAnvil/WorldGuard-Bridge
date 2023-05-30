package cc.javajobs.wgbridge.infrastructure.abs;

import cc.javajobs.wgbridge.infrastructure.struct.WGFlag;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Callum Johnson
 * @since 11/02/2022 - 15:04
 */
public abstract class AbstractFlag<F> implements WGFlag {

    /**
     * The wrapped Flag.
     */
    protected final F flag;

    /**
     * Constructor to initialise an AbstractFlag of the type 'F'.
     *
     * @param flag to initialise {@link #flag} with.
     */
    public AbstractFlag(@NotNull F flag) {
        this.flag = flag;
    }

    /**
     * Method to obtain the wrapped Flag.
     *
     * @return WorldGuard flag object.
     */
    public F getFlag() {
        return flag;
    }

    /**
     * Method to determine if an object is equal to this object.
     *
     * @param object to test.
     * @return {@code true} if it is.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AbstractFlag)) return false;
        final AbstractFlag<?> obj = (AbstractFlag<?>) object;
        if (obj.getName().equalsIgnoreCase(getName())) return true;
        else return hashCode() == obj.hashCode();
    }

    /**
     * Method to generate the hashcode for this AbstractFlag.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(flag, getName(), getValue());
    }

    /**
     * Method to obtain the String representation of the {@link AbstractFlag}.
     *
     * @return String representation of the {@link AbstractFlag}.
     */
    @Override
    public String toString() {
        return "AbstractFlag={flagObject:" + flag + '}';
    }

}
