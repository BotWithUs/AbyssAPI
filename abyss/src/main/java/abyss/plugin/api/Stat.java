package abyss.plugin.api;

/**
 * A stat.
 */
public class Stat {
    private int index;
    private int current;
    private int max;
    private int xp;

    private int f2pExperienceCap;
    private int f2pLevelCap;

    private boolean isMembers;

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
    public int getCurrentLevel() {
        return current;
    }

    /**
     * Retrieves the maximum level of this stat.
     *
     * @return The maximum level of this stat.
     */
    public int getMaxLevel() {
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

    /**
     * Retrieves the maximum amount of experience the player can get as a none member
     * @return The experience cap
     */

    public int getF2PExperienceCap() {
        return f2pExperienceCap;
    }

    /**
     * Retrieves the maximum level that the player can get as a none member
     * @return The level cap
     */

    public int getF2PLevelCap() {
        return f2pLevelCap;
    }

    /**
     * Retrieves is the skill is a members only skill
     * @return if the skill is members
     */
    public boolean isMembers() {
        return isMembers;
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
