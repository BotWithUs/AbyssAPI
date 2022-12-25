package abyss.plugin.api;

public class Hitmark {
    private int id;
    private int damage;
    private int startCycle;

    private Hitmark() {
    }

    /**
     * The id for this Hitmark. It can be used to determine what type of damage has been inflicted,
     * whether a critical hit occurred, etc.
     *
     * @return The id of this type of Hitmark
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the number displayed in the hitsplat.
     *
     * @return The number displayed in the hitsplat, 0 if nothing is shown.
     */
    public int getNumber() {
        return damage;
    }
}
