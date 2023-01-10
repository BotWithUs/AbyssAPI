package abyss.plugin.api;

import abyss.plugin.api.extensions.Extension;
import abyss.plugin.api.extensions.ExtensionContainer;
import abyss.plugin.api.widgets.BankWidgetExtension;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Provides access to the local player's bank.
 */
public final class Bank implements ExtensionContainer<Extension> {

    public static final Bank BANK = new Bank();

    private final Map<Class<?>, Extension> extensions;

    private Bank() {
        extensions = new HashMap<>();
        setExtension(new BankWidgetExtension(517, 95, 195, 15, 39, 42, 127, 160));
    }

    @NotNull
    @Override
    public Extension getExt(@NotNull Class<?> clazz) {
        return extensions.get(clazz);
    }

    @Override
    public boolean hasExtension(@NotNull Class<?> clazz) {
        return extensions.containsKey(clazz);
    }

    @Override
    public void setExtension(@NotNull Extension extension) {
        extensions.put(extension.getClass(), extension);
    }

    /**
     * Determines if the bank widget is open.
     *
     * @return If the bank widget is open.
     */
    public static boolean isOpen() {
        if(BANK.hasExtension(BankWidgetExtension.class)) {
            BankWidgetExtension ext = (BankWidgetExtension) BANK.getExt(BankWidgetExtension.class);
            return Inventories.isAvailable(ext.getContainerId());
        }
        return false;
    }

    /**
     * Retrieves all items in the bank.
     *
     * @return All items in the bank.
     */
    public static ComponentItem[] getItems() {
        if(!BANK.hasExtension(BankWidgetExtension.class)) {
            return new ComponentItem[0];
        }
        BankWidgetExtension ext = (BankWidgetExtension) BANK.getExt(BankWidgetExtension.class);

        Inventory container = Inventories.byId(ext.getContainerId());
        if (container == null) {
            return new ComponentItem[0];
        }


        List<ComponentItem> list = new LinkedList<>();
        Item[] containerItems = container.getItems();
        for (int i = 0; i < containerItems.length; i++) {
            Item item = containerItems[i];
            if (item.getId() != -1) {
                list.add(new ComponentItem(item.getId(), item.getAmount(), i, Interfaces.hash(ext.getRootId(), ext.getWithdrawButtonId()), container));
            }
        }
        return list.toArray(new ComponentItem[0]);
    }

    /**
     * Counts the number of items that pass the filter.
     *
     * @return The number of items that pass the filter.
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
     * Determines if the bank is empty.
     *
     * @return If the bank is empty.
     */
    public static boolean isEmpty() {
        return getItems().length == 0;
    }

    /**
     * Determines if the bank contains an item.
     *
     * @return If the bank contains an item that passes the provided filter.
     */
    public static boolean contains(Predicate<ComponentItem> filter) {
        return count(filter) > 0;
    }

    /**
     * Iterates over each of the elements in the bank.
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

    /**
     * Withdraws some items from the bank.
     *
     * @param filter The filter items must pass through in order to be withdrawn.
     * @param option The menu option to use for withdrawing.
     */
    public static void withdraw(Predicate<ComponentItem> filter, int option) {
        forEach((item) -> {
            if (filter.test(item)) {
                item.interact(option);
            }
        });
    }

    /**
     * Deposits items into the bank.
     *
     * @param filter The filter items must pass through in order to be deposited.
     * @param option The menu option to use for depositing.
     */
    public static void deposit(Predicate<ComponentItem> filter, int option) {
        if(!BANK.hasExtension(BankWidgetExtension.class)) {
            return;
        }
        BankWidgetExtension ext = (BankWidgetExtension) BANK.getExt(BankWidgetExtension.class);

        Backpack.forEach((item) -> {
            if (filter.test(item)) {
                item.setInterfaceIndex(Interfaces.hash(ext.getRootId(), ext.getDepositButtonId()));
                item.interact(option);
            }
        });
    }

    /**
     * Deposits all items into the bank.
     */
    public static void depositAll() {
        if(!BANK.hasExtension(BankWidgetExtension.class)) {
            return;
        }
        BankWidgetExtension ext = (BankWidgetExtension) BANK.getExt(BankWidgetExtension.class);

        Actions.menu(Actions.MENU_EXECUTE_WIDGET, 1, -1, Interfaces.hash(ext.getRootId(), ext.getDepositInventoryButtonId()), 0);
    }

    /**
     * Deposits all equipment into the bank.
     */
    public static void depositEquipment() {
        if(!BANK.hasExtension(BankWidgetExtension.class)) {
            return;
        }
        BankWidgetExtension ext = (BankWidgetExtension) BANK.getExt(BankWidgetExtension.class);

        Actions.menu(Actions.MENU_EXECUTE_WIDGET, 1, -1, Interfaces.hash(ext.getRootId(), ext.getDepositEquipmentButtonId()), 0);
    }

    /**
     * Determines if items are being withdrawn as notes or not.
     */
    public static boolean isWithdrawingNotes() {
        if(!BANK.hasExtension(BankWidgetExtension.class)) {
            return false;
        }
        BankWidgetExtension ext = (BankWidgetExtension) BANK.getExt(BankWidgetExtension.class);
        return ConfigProvider.getVarpValue(ext.getWithdrawAsNoteConVarId()) == 1;
    }

    /**
     * Changes whether or not we are withdrawing items in noted form.
     */
    public static void setWithdrawingNotes(boolean notes) {
        if(!BANK.hasExtension(BankWidgetExtension.class)) {
            return;
        }
        BankWidgetExtension ext = (BankWidgetExtension) BANK.getExt(BankWidgetExtension.class);

        if (isWithdrawingNotes() != notes) {
            Actions.menu(Actions.MENU_EXECUTE_WIDGET, 1, -1, Interfaces.hash(ext.getRootId(), ext.getToggleNotesButtonId()), 0);
        }
    }

    @NotNull
    @Override
    public List<Extension> getExtensions() {
        return extensions.values().stream().toList();
    }
}
