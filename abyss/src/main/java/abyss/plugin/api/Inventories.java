package abyss.plugin.api;

/**
 * Provides access to item containers.
 */
public final class Inventories {

    private Inventories() {
    }

    /**
     * Retrieves all item continers.
     *
     * @return All item continers.
     */
    public static native Inventory[] all();

    /**
     * Retrieves an item container by its id.
     *
     * @param id The id of the item container.
     * @return The item container.
     */
    public static native Inventory byId(int id);

    /**
     * Determines if an item container is available or not.
     *
     * @param id The id of the item container to find.
     * @return If an item container was found with the provided id.
     */
    public static boolean isAvailable(int id) {
        return byId(id) != null;
    }
}
