package abyss.plugin.api;

import abyss.plugin.api.variables.ContainerVariables;

import java.util.Objects;

/**
 * An item that is stored in a widget.
 */
public class ComponentItem extends Item {

    private int interfaceIndex;
    private int componentIndex;
    private int subComponentIndex;

    private Inventory inventory;

    public ComponentItem() {
        this.subComponentIndex = -1;
        this.interfaceIndex = -1;
        this.componentIndex = -1;
    }

    public ComponentItem(int itemId, int amount, int slot) {
        super(itemId, amount);
        this.subComponentIndex = slot;
        this.interfaceIndex = -1;
        this.componentIndex = -1;
    }

    public ComponentItem(int id, int amount, int slot, int interfaceHash, Inventory inventory) {
        super(id, amount);
        this.subComponentIndex = slot;
        this.interfaceIndex = interfaceHash >> 16;
        this.componentIndex = interfaceHash & 65535;
        this.inventory = inventory;
    }

    public ComponentItem(int id, int amount, int slot, int interfacehash, CacheItem type, Inventory inventory) {
        super(id, amount, type);
        this.subComponentIndex = slot;
        this.interfaceIndex = interfacehash >> 16;
        this.componentIndex = interfacehash & 65535;
        this.inventory = inventory;
    }

    /**
     * Retrieves the item container slot that this item is in within
     * the widget.
     */
    public int getSubComponentIndex() {
        return subComponentIndex;
    }

    /**
     * Changes the slot this item is in. Should only be used internally.
     */
    public void setSubComponentIndex(int subComponentIndex) {
        this.subComponentIndex = subComponentIndex;
    }

    /**
     * Retrieves the interact id of the widget this item is bound to.
     */
    public int getInterfaceIndex() {
        return interfaceIndex;
    }

    /**
     * Changes the interact id of the widget this item is bound to. Should only be used internally.
     */
    public void setInterfaceIndex(int interfaceIndex) {
        this.interfaceIndex = interfaceIndex;
    }

    public void setComponentIndex(int componentIndex) {
        this.componentIndex = componentIndex;
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
     * Interacts with this widget item. This may not be available on all widgets.
     *
     * @param option The option to interact with.
     */
    public boolean interact(int option) {
        if (interfaceIndex == -1) {
            return false;
        }
        //TODO: slot == -1 ? 1 : slot is confusing, why 1?
        //0 isn't valid we would be sending the wrong information options are +1
        Actions.menu(Actions.MENU_EXECUTE_WIDGET, option, subComponentIndex == -1 ? 1 : subComponentIndex, interfaceIndex << 16 | componentIndex, 0);
        return true;
    }

    public boolean interact(String option) {
        for (int i = 0; i < getOptionNames().length; i++) {
            String opt = getOptionNames()[i];
            if (option.equalsIgnoreCase(opt)) {
                return interact(i + 1);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "ComponentItem{" +
                "id=" + getId() +
                ", amount= " + getAmount() +
                ", slot=" + subComponentIndex +
                '}';
    }

    public int getVarbitValue(int varbitID) {
        if (inventory == null) return 0;
        return inventory.getVarbitById(subComponentIndex, varbitID);
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
        return subComponentIndex == that.subComponentIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subComponentIndex);
    }
}
