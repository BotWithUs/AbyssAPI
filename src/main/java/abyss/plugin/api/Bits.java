package abyss.plugin.api;

/**
 * Provides bitwise utilities.
 */
public final class Bits {

    /**
     * A table containing the maximum value for a number of bits.
     */
    public static final int[] BIT_TABLE = new int[32];

    private Bits() {
    }

    static {
        int mask = 0;
        for (int i = 0; i < Integer.SIZE; i++) {
            mask |= (1 << i);
            BIT_TABLE[i] = mask;
        }
    }
}
