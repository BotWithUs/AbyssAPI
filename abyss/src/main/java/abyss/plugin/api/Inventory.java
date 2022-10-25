package abyss.plugin.api;

import abyss.plugin.api.extensions.SimpleExtensionContainer;
import abyss.plugin.api.variables.ContainerVariables;
import abyss.plugin.api.variables.Variables;
import abyss.plugin.api.widgets.InventoryWidgetExtension;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
    public static int count(Predicate<WidgetItem> filter) {
        int count = 0;
        for (WidgetItem item : getItems()) {
            if (filter.test(item)) {
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
    public static WidgetItem first(Predicate<WidgetItem> filter) {
        for (WidgetItem item : getItems()) {
            if (filter.test(item)) {
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
    public static boolean contains(Predicate<WidgetItem> filter) {
        return count(filter) > 0;
    }

    /**
     * Iterates over each of the elements in the inventory.
     *
     * @param cb The callback to invoke with each element.
     */
    public static void forEach(Consumer<WidgetItem> cb) {
        for (WidgetItem item : getItems()) {
            if (item != null) {
                cb.accept(item);
            }
        }
    }

    public static int getVarbitValueForItem(int itemId, Variables var) {
        return getVarbitValueForItem(itemId, var.getVariableId());
    }

    public static int getVarbitValueForItem(int itemId, int varbitId) {
        for (WidgetItem item : getItems()) {
            if(itemId == item.getId()) {
                return getVarbitValue(item.getSlot(), varbitId);
            }
        }
        return -1;
    }

    public static int getVarbitValue(int slot, ContainerVariables var) {
        return getVarbitValue(slot, var.getVarbitID());
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

    public static Stream<WidgetItem> stream() {
        return Arrays.stream(getItems());
    }

}
