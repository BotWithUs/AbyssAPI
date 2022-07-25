package kraken.plugin.api;

/**
 * A stat.
 */
public class Stat {
    private int index;
    private int current;
    private int max;
    private int xp;

    public Stat() {
    }

    public Stat(int index, int current, int max, int xp) {
        this.index = index;
        this.current = current;
        this.max = max;
        this.xp = xp;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Retrieves the current level of this stat.
     *
     * @return The current level of this stat.
     */
    public int getCurrent() {
        return current;
    }

    /**
     * Retrieves the maximum level of this stat.
     *
     * @return The maximum level of this stat.
     */
    public int getMax() {
        return max;
    }

    /**
     * Retrieves the amount of experience this stat has.
     *
     * @return The amount of experience this stat has.
     */
    public int getXp() {
        return xp;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "index=" + index +
                ", current=" + current +
                ", max=" + max +
                ", xp=" + xp +
                '}';
    }
}
