package abyss.plugin.api;

import abyss.plugin.api.extensions.SimpleExtensionContainer;
import abyss.plugin.api.widgets.EquipmentWidgetExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Provides simplified access to the equipment widget.
 */
public final class Equipment extends SimpleExtensionContainer {

    public static final Equipment EQUIPMENT = new Equipment();

    private Equipment() {
        setExtension(new EquipmentWidgetExtension(670, 94, 1464, 15));
    }

    /**
     * Retrieves all items displayed in the equipment widget.
     *
     * @return All items displayed in the equipment widget.
     */
    public static ComponentItem[] getItems() {
        return getItems(false);
    }

    public static ComponentItem[] getItems(boolean showCosmetic) {
        if(!EQUIPMENT.hasExtension(EquipmentWidgetExtension.class)) {
            return new ComponentItem[0];
        }

        EquipmentWidgetExtension ext = (EquipmentWidgetExtension)EQUIPMENT.getExt(EquipmentWidgetExtension.class);
        int inventoryId = ext.getEquipmentInventoryId();
        if(showCosmetic) {
            inventoryId = ext.getCosmeticInventoryId();
        }

        Inventory inventory = Inventories.byId(inventoryId);
        if (inventory == null) {
            return new ComponentItem[0];
        }

        List<ComponentItem> list = new LinkedList<>();
        Item[] containerItems = inventory.getItems();
        for (int i = 0; i < containerItems.length; i++) {
            Item item = containerItems[i];
            if (item.getId() != -1) {
                list.add(new ComponentItem(item.getId(), item.getAmount(), i, Interfaces.hash(ext.getInterfaceIndex(), ext.getComponentIndex()), inventory));
            }
        }
        return list.toArray(new ComponentItem[0]);
    }


    /**
     * Finds the first item that passes the provided filter.
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
}
