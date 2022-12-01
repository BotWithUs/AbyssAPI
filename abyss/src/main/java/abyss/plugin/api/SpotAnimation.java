package abyss.plugin.api;

/**
 * An animation that can stand independently in the scene that does not move between different tiles.
 */
public final class SpotAnimation extends Entity {

    private int id;

    /**
     * Do not make instances of this.
     */
    private SpotAnimation() {
    }

    /**
     * Retrieves the id of the SpotAnimation.
     *
     * @return The id of the SpotAnimation.
     */
    public int getId() {
        return id;
    }

}
