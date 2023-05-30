package v7.impl;

import cc.javajobs.wgbridge.infrastructure.abs.AbstractFlag;
import cc.javajobs.wgbridge.infrastructure.struct.WGState;
import com.sk89q.worldguard.protection.flags.Flag;
import org.jetbrains.annotations.NotNull;

/**
 * @author Callum Johnson
 * @since 11/02/2022 - 15:20
 */
public class FlagV7 extends AbstractFlag<Flag<?>> {

    private final Object value;

    /**
     * Constructor to initialise an AbstractFlag of the type 'F'.
     *
     * @param flag to initialise {@link #flag} with.
     */
    public FlagV7(@NotNull Flag<?> flag, @NotNull Object value) {
        super(flag);
        this.value = value;
    }

    /**
     * Method to get the name of the Flag.
     *
     * @return Flag Name.
     */
    @Override
    public @NotNull String getName() {
        return flag.getName();
    }

    /**
     * Method to get the state of the Flag.
     *
     * @return {@code true} if it 'allows' the flag.
     */
    @Override
    public @NotNull WGState getState() {
        return WGState.getStateFrom(value);
    }

    /**
     * Method to get the flag value.
     *
     * @return if the flag is a 'state' flag, {@link #getState()} is returned.
     */
    @Override
    public @NotNull Object getValue() {
        return value;
    }

}
