package abyss.plugin.api;

import abyss.plugin.api.variables.ContainerVariables;

/**
 * An item container, holds real information about items.
 */
public final class Inventory {

    /**
     * The id of this container.
     */
    private int id;

    /**
     * All items stored in this container.
     */
    private Item[] items;

    private Inventory() {
    }

    /**
     * @return The id of this container.
     */
    public int getId() {
        return id;
    }

    /**
     * @return All items stored in this container.
     */
    public Item[] getItems() {
        return items;
    }

    public int getVarbitById(int slot, int varbitId) {
        if(slot < items.length && items[slot].getId() > 0) {
            return ConfigProvider.getVarbitValue(id, slot, varbitId);
        }
        return -1;
    }

    public int getVarbitById(int slot, ContainerVariables var) {
        return getVarbitById(slot, var.getVarbitID());
    }
}
