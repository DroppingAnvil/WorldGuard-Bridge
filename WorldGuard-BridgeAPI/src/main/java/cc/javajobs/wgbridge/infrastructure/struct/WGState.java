package cc.javajobs.wgbridge.infrastructure.struct;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * @author Callum Johnson
 * @since 11/02/2022 - 14:33
 */
public enum WGState {

    ALLOW,
    DENY,
    NOT_STATE_FLAG;

    /**
     * Method to get the WGState from an object.
     * <p>
     *     The 'object' can be a StateFlag from WorldGuard, a String, a Boolean or an Integer.
     * </p>
     *
     * @param object to parse.
     * @return {@link WGState} calculated from object or default '{@link #ALLOW}'.
     */
    public static WGState getStateFrom(@Nullable Object object) {
        if (object == null) return ALLOW;
        String value;
        if (object.getClass().isEnum()) value = ((Enum<?>) object).name();
        else if (object instanceof String) value = (String) object;
        else if (object instanceof Boolean) value = ((Boolean) object) ? "allow" : "deny";
        else if (object instanceof Integer) value = ((Integer) object)  == 1 ? "allow" : "deny";
        else value = "n/a";
        return Arrays.stream(WGState.values()).filter(wgState -> wgState.name().equalsIgnoreCase(value))
                .findFirst().orElse(ALLOW);
    }

}
