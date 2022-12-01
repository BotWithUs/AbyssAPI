package abyss.plugin.api;

/**
 * Provides simplified access to the coin pouch.
 */
public final class CoinPouch {

    private static final int ITEM_CONTAINER_ID = 623;

    private CoinPouch() {
    }

    /**
     * Retrieves the amount of coins currently in the coin pouch.
     *
     * @return The amount of coins currently in the coin pouch.
     */
    public static int getCoins() {
        Inventory cv = Inventories.byId(ITEM_CONTAINER_ID);
        if (cv == null) {
            return 0;
        }

        for (Item item : cv.getItems()) {
            if (item.getId() == 995) {
                return item.getAmount();
            }
        }

        return 0;
    }

}
