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
    public static WidgetItem[] getItems() {
        return getItems(false);
    }

    public static WidgetItem[] getItems(boolean showCosmetic) {
        if(!EQUIPMENT.hasExtension(EquipmentWidgetExtension.class)) {
            return new WidgetItem[0];
        }

        EquipmentWidgetExtension ext = (EquipmentWidgetExtension)EQUIPMENT.getExt(EquipmentWidgetExtension.class);
        int containerId = ext.getEquipmentContainerId();
        if(showCosmetic) {
            containerId = ext.getCosmeticContainerId();
        }

        ItemContainer container = ItemContainers.byId(containerId);
        if (container == null) {
            return new WidgetItem[0];
        }

        List<WidgetItem> list = new LinkedList<>();
        Item[] containerItems = container.getItems();
        for (int i = 0; i < containerItems.length; i++) {
            Item item = containerItems[i];
            if (item.getId() != -1) {
                list.add(new WidgetItem(item.getId(), item.getAmount(), i, Widgets.hash(ext.getRootId(), ext.getChildId()), container));
            }
        }
        return list.toArray(new WidgetItem[0]);
    }


    /**
     * Finds the first item that passes the provided filter.
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
}
