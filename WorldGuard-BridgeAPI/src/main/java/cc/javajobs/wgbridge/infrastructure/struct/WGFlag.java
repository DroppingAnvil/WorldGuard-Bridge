package cc.javajobs.wgbridge.infrastructure.struct;

import org.jetbrains.annotations.NotNull;

/**
 * @author Callum Johnson
 * @since 11/02/2022 - 14:19
 */
public interface WGFlag {

    /**
     * Method to get the name of the Flag.
     *
     * @return Flag Name.
     */
    @NotNull
    String getName();

    /**
     * Method to get the state of the Flag.
     *
     * @return {@code true} if it 'allows' the flag.
     */
    @NotNull
    WGState getState();

    /**
     * Method to get the flag value.
     *
     * @return if the flag is a 'state' flag, {@link #getState()} is returned.
     */
    @NotNull
    Object getValue();

}
