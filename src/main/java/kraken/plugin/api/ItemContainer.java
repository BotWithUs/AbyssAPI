package kraken.plugin.api;

import abyss.plugin.api.variables.ContainerVariables;
import abyss.plugin.api.variables.VariableManager;

/**
 * An item container, holds real information about items.
 */
public final class ItemContainer {

    /**
     * The id of this container.
     */
    private int id;

    /**
     * All items stored in this container.
     */
    private Item[] items;

    private ItemContainer() {
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
        return VariableManager.getContainerVarbit(id, slot, varbitId);
    }

    public int getVarbitById(int slot, ContainerVariables var) {
        return getVarbitById(slot, var.getVarbitID());
    }
}
