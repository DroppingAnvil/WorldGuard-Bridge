package cc.javajobs.wgbridge.infrastructure.struct;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * @author Callum Johnson
 * @since 11/02/2022 - 14:18
 */
public interface WGRegion {

    /**
     * Method to obtain the Flags for this Region.
     *
     * @return {@link Set} of {@link WGFlag}.
     */
    @NotNull
    Set<WGFlag> getFlags();

    /**
     * Method to get a Flag by its name.
     *
     * @param name of the flag.
     * @return {@link WGFlag} or {@code null}.
     */
    @Nullable
    default WGFlag getFlagByName(@NotNull String name) {
        return getFlags().stream().filter(flag -> flag.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    /**
     * Method to get the State of a given Flag.
     *
     * @param flagName to get the State for.
     * @return {@link WGState} defaults to {@link WGState#ALLOW} if the flag is not found.
     */
    @NotNull
    default WGState getStateFor(@NotNull String flagName) {
        final WGFlag flag = getFlagByName(flagName);
        if (flag == null) return WGState.ALLOW;
        return getStateFor(flag);
    }

    /**
     * Method to get the State of a given Flag.
     *
     * @param flag to get the State for.
     * @return {@link WGState}
     */
    default WGState getStateFor(WGFlag flag) {
        return flag.getState();
    }

    /**
     * Method to determine if the Flag value (specified by name) matches the given value.
     *
     * @param flagName to get the value from the region for.
     * @param value to compare to.
     * @return {@code true} if it is equal.
     */
    default boolean checkValue(@NotNull String flagName, @NotNull Object value) {
        final WGFlag flag = getFlagByName(flagName);
        if (flag == null) return false;
        return flag.getValue().equals(value) || flag.getValue().equals(WGState.getStateFrom(value));
    }

}
