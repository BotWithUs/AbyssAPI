package kraken.plugin.api;

import abyss.plugin.api.extensions.SimpleExtensionContainer;
import abyss.plugin.api.widgets.InventoryWidgetExtension;

import java.util.LinkedList;
import java.util.List;

/**
 * Provides access to the local player's inventory.
 */
public final class Inventory extends SimpleExtensionContainer {
    public static final Inventory INVENTORY = new Inventory();

    private Inventory() {
        setExtension(new InventoryWidgetExtension(1473, 93, 5));
    }

    /**
     * Retrieves all items in the inventory.
     *
     * @return All items in the inventory.
     */
    public static WidgetItem[] getItems() {
        if(!INVENTORY.hasExtension(InventoryWidgetExtension.class)) {
            return new WidgetItem[0];
        }
        InventoryWidgetExtension ext = (InventoryWidgetExtension) INVENTORY.getExt(InventoryWidgetExtension.class);

        ItemContainer container = ItemContainers.byId(ext.getContainerId());
        if (container == null) {
            return new WidgetItem[0];
        }


        List<WidgetItem> list = new LinkedList<>();
        Item[] containerItems = container.getItems();
        for (int i = 0; i < containerItems.length; i++) {
            Item item = containerItems[i];
            if (item.getId() != -1) {
                list.add(new WidgetItem(item.getId(), item.getAmount(), i, Widgets.hash(ext.getRootId(), ext.getContainerChildId()), container));
            }
        }
        return list.toArray(new WidgetItem[0]);
    }

    /**
     * Counts the number of items that pass through the provided filter.
     *
     * @return The number of items that passed through the filter.
     */
    public static int count(Filter<WidgetItem> filter) {
        int count = 0;
        for (WidgetItem item : getItems()) {
            if (filter.accept(item)) {
                count += item.getAmount();
            }
        }
        return count;
    }

    /**
     * Finds the first widget item that passes the provided filter.
     *
     * @param filter The filter that items must pass through in order to be accepted.
     * @return The first item that passed the filter.
     */
    public static WidgetItem first(Filter<WidgetItem> filter) {
        for (WidgetItem item : getItems()) {
            if (filter.accept(item)) {
                return item;
            }
        }

        return null;
    }

    /**
     * Determines if the inventory is full.
     *
     * @return If the inventory is full.
     */
    public static boolean isFull() {
        return getItems().length == 28;
    }

    /**
     * Determines if the inventory is empty.
     *
     * @return If the inventory is empty.
     */
    public static boolean isEmpty() {
        return getItems().length == 0;
    }

    /**
     * Counts the number of free slots available in the inventory.
     *
     * @return The number of free slots available in the inventory.
     */
    public static int countFreeSlots() {
        return 28 - getItems().length;
    }

    /**
     * Determines if the inventory contains an item.
     *
     * @return If the inventory contains an item that passes through the provided filter.
     */
    public static boolean contains(Filter<WidgetItem> filter) {
        return count(filter) > 0;
    }

    /**
     * Iterates over each of the elements in the inventory.
     *
     * @param cb The callback to invoke with each element.
     */
    public static void forEach(Action1<WidgetItem> cb) {
        for (WidgetItem item : getItems()) {
            if (item != null) {
                cb.call(item);
            }
        }
    }

    public static int getVarbitValue(int slot, int varbitId) {
        if(!INVENTORY.hasExtension(InventoryWidgetExtension.class)) {
            return -1;
        }
        InventoryWidgetExtension ext = (InventoryWidgetExtension) INVENTORY.getExt(InventoryWidgetExtension.class);

        ItemContainer inventory = ItemContainers.byId(ext.getContainerId());
        if(inventory == null) return -1;
        return inventory.getVarbitById(slot, varbitId);
    }


}
