package abyss.plugin.api;

import abyss.plugin.api.extensions.SimpleExtensionContainer;
import abyss.plugin.api.variables.ContainerVariables;
import abyss.plugin.api.variables.Variables;
import abyss.plugin.api.widgets.BackpackWidgetExtension;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Provides access to the local player's inventory.
 */
public final class Backpack extends SimpleExtensionContainer {
    public static final Backpack BACKPACK = new Backpack();

    private Backpack() {
        setExtension(new BackpackWidgetExtension(1473, 93, 5));
    }

    /**
     * Retrieves all items in the inventory.
     *
     * @return All items in the inventory.
     */
    public static ComponentItem[] getItems() {
        if(!BACKPACK.hasExtension(BackpackWidgetExtension.class)) {
            return new ComponentItem[0];
        }
        BackpackWidgetExtension ext = (BackpackWidgetExtension) BACKPACK.getExt(BackpackWidgetExtension.class);

        Inventory container = Inventories.byId(ext.getContainerId());
        if (container == null) {
            return new ComponentItem[0];
        }


        List<ComponentItem> list = new LinkedList<>();
        Item[] containerItems = container.getItems();
        for (int i = 0; i < containerItems.length; i++) {
            Item item = containerItems[i];
            if (item.getId() != -1) {
                list.add(item.toComponentItem(i, Interfaces.hash(ext.getRootId(), ext.getContainerChildId()), container));
            }
        }
        return list.toArray(new ComponentItem[0]);
    }

    /**
     * Counts the number of items that pass through the provided filter.
     *
     * @return The number of items that passed through the filter.
     */
    public static int count(Predicate<ComponentItem> filter) {
        int count = 0;
        for (ComponentItem item : getItems()) {
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
    public static ComponentItem first(Predicate<ComponentItem> filter) {
        for (ComponentItem item : getItems()) {
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
    public static boolean contains(Predicate<ComponentItem> filter) {
        return count(filter) > 0;
    }

    /**
     * Iterates over each of the elements in the inventory.
     *
     * @param cb The callback to invoke with each element.
     */
    public static void forEach(Consumer<ComponentItem> cb) {
        for (ComponentItem item : getItems()) {
            if (item != null) {
                cb.accept(item);
            }
        }
    }

    public static int getVarbitValueForItem(int itemId, Variables var) {
        return getVarbitValueForItem(itemId, var.getVariableId());
    }

    public static int getVarbitValueForItem(int itemId, int varbitId) {
        for (ComponentItem item : getItems()) {
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
        if(!BACKPACK.hasExtension(BackpackWidgetExtension.class)) {
            return -1;
        }
        BackpackWidgetExtension ext = (BackpackWidgetExtension) BACKPACK.getExt(BackpackWidgetExtension.class);

        Inventory inventory = Inventories.byId(ext.getContainerId());
        if(inventory == null) return -1;
        return inventory.getVarbitById(slot, varbitId);
    }

    public static Stream<ComponentItem> stream() {
        return Arrays.stream(getItems());
    }

}
