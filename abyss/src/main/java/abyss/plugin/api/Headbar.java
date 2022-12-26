package abyss.plugin.api;

public class Headbar {
    private int id;
    //TODO should be currentValue and we should have a maxValue field.
    private int value;

    private Headbar() {
    }

    /**
     * Gets the id of the current headbar. Can also be thought of as the type of headbar.
     *
     * @return the id of this headbar.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the current value of this headbar in the form of the width.
     * Typically, between 0 and 255, but for some bosses and other scenarios it can be larger or smaller.
     *
     * @return an integer between 0 and the max value of the Headbar (commonly 255.0).
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets the current value of this Headbar as a percentage.
     * It takes into account the maximum headbar value for the given headbar.
     *
     * @return a percentage (double precision floating point) value between 0 and 100.
     */
    public double getValueAsPercent() {
        //TODO fetch the max value from the Type (definition) and use that instead of 255.0f/
        return ((value) * 100) / 255.D;
    }
}
