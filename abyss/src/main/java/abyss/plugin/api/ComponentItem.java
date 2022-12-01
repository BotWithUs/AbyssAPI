package abyss.plugin.api;

import abyss.plugin.api.variables.ContainerVariables;

import java.util.Objects;

/**
 * An item that is stored in a widget.
 */
public class ComponentItem extends Item {

    private int slot;
    private int componentId = -1;
    private int childId = -1;

    private Inventory inventory;

    public ComponentItem() {
        this.slot = -1;
    }

    public ComponentItem(int itemId, int amount, int slot) {
        super(itemId, amount);
        this.slot = slot;
    }

    public ComponentItem(int id, int amount, int slot, int componentId, Inventory inventory) {
        super(id, amount);
        this.slot = slot;
        this.componentId = componentId;
        this.inventory = inventory;
    }

    public ComponentItem(int id, int amount, int slot, int componentId, CacheItem type, Inventory inventory) {
        super(id, amount, type);
        this.slot = slot;
        this.componentId = componentId;
        this.inventory = inventory;
    }

    /**
     * Retrieves the item container slot that this item is in within
     * the widget.
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Changes the slot this item is in. Should only be used internally.
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }

    /**
     * Retrieves the interact id of the widget this item is bound to.
     */
    public int getComponentId() {
        return componentId;
    }

    /**
     * Changes the interact id of the widget this item is bound to. Should only be used internally.
     */
    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    /**
     * The Inventory this item belongs too
     *
     * @return Inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Sets the Inventory that this item belongs too
     */
    public void setInventory(Inventory container) {
        this.inventory = container;
    }

    /**
     * Interacts with this widget item. This may not be available on all widgets.
     *
     * @param option The option to interact with.
     */
    public boolean interact(int option) {
        if (componentId == -1) {
            return false;
        }
        //TODO: slot == -1 ? 1 : slot is confusing, why 1?
        Actions.menu(Actions.MENU_EXECUTE_WIDGET, option, slot == -1 ? 1 : slot, componentId, 0);
        return true;
    }

    public boolean interact(String option) {
        for (int i = 0; i < getOptionNames().length; i++) {
            String opt = getOptionNames()[i];
            if (option.equalsIgnoreCase(opt)) {
                return interact(i);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "ComponentItem{" +
                "id=" + getId() +
                ", amount= " + getAmount() +
                ", slot=" + slot +
                '}';
    }

    public int getVarbitValue(int varbitID) {
        if (inventory == null) return 0;
        return inventory.getVarbitById(slot, varbitID);
    }

    public int getVarbitValue(ContainerVariables var) {
        return this.getVarbitValue(var.getVarbitID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ComponentItem that = (ComponentItem) o;
        return slot == that.slot;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), slot);
    }
}
