package abyss.plugin.api;

import abyss.plugin.api.variables.ContainerVariables;

import java.util.Objects;

/**
 * An item that is stored in a widget.
 */
public class WidgetItem extends Item {

    private int slot;
    private int widgetId = -1;
    private int childId = -1;

    private ItemContainer container;

    public WidgetItem() {
    }

    public WidgetItem(int itemId, int amount, int slot) {
        super(itemId, amount);
        this.slot = slot;
    }

    public WidgetItem(int id, int amount, int slot, int widgetId, ItemContainer container) {
        super(id, amount);
        this.slot = slot;
        this.widgetId = widgetId;
        this.container = container;
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
    public int getWidgetId() {
        return widgetId;
    }

    /**
     * Changes the interact id of the widget this item is bound to. Should only be used internally.
     */
    public void setWidgetId(int widgetId) {
        this.widgetId = widgetId;
    }

    /**
     * The Item Container this item belongs too
     *
     * @return ItemContainer
     */

    public ItemContainer getContainer() {
        return container;
    }

    /**
     * Sets the item container that this item belongs too
     */

    public void setContainer(ItemContainer container) {
        this.container = container;
    }

    /**
     * Interacts with this widget item. This may not be available on all widgets.
     *
     * @param option The option to interact with.
     */
    public boolean interact(int option) {
        if (widgetId == -1) {
            return false;
        }
        if (widgetId > Short.MAX_VALUE) {
            Actions.menu(Actions.MENU_EXECUTE_WIDGET, option, slot, widgetId, 0);
        } else {
            Actions.menu(Actions.MENU_EXECUTE_WIDGET, option, slot, Widgets.hash(widgetId, childId), 0);
        }
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
        return "WidgetItem{" +
                "id=" + getId() +
                ", amount= " + getAmount() +
                ", slot=" + slot +
                '}';
    }

    public int getVarbitValue(int varbitID) {
        if (container == null) return 0;
        return container.getVarbitById(slot, varbitID);
    }

    public int getVarbitValue(ContainerVariables var) {
        return this.getVarbitValue(var.getVarbitID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WidgetItem that = (WidgetItem) o;
        return slot == that.slot;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), slot);
    }
}
