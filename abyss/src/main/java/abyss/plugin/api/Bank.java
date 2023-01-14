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
        BankWidgetExtension ext = getBankExtension();
        return ext != null && Inventories.isAvailable(ext.getContainerId());
    }

    /**
     * Retrieves all items in the bank.
     *
     * @return All items in the bank.
     */
    public static ComponentItem[] getItems() {
        BankWidgetExtension ext = getBankExtension();
        if (ext == null)
            return new ComponentItem[0];

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
    @Deprecated
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
        BankWidgetExtension ext = getBankExtension();
        if (ext == null)
            return;
        for (ComponentItem item : Bank.getItems()) {
            if (filter.test(item)) {
                item.setInterfaceIndex(ext.getRootId());
                item.setComponentIndex(ext.getDepositButtonId());
                if(item.interact(option)) {
                    break;
                }
            }
        }
    }

    /**
     * Deposits items into the bank.
     *
     * @param filter The filter items must pass through in order to be deposited.
     * @param option The menu option to use for depositing.
     */
    public static void deposit(Predicate<ComponentItem> filter, int option) {
        BankWidgetExtension ext = getBankExtension();
        if (ext == null)
            return;
        for (ComponentItem item : Backpack.getItems()) {
            if(filter.test(item)) {
                item.setInterfaceIndex(ext.getRootId());
                item.setComponentIndex(ext.getDepositButtonId());
                if(item.interact(option)) {
                    break;
                }
            }
        }
    }

    /**
     * Deposits all items into the bank.
     */
    public static void depositAll() {
        BankWidgetExtension ext = getBankExtension();
        if (ext != null)
            Actions.menu(Actions.MENU_EXECUTE_WIDGET, 1, -1, Interfaces.hash(ext.getRootId(), ext.getDepositInventoryButtonId()), 0);
    }

    /**
     * Deposits all equipment into the bank.
     */
    public static void depositEquipment() {
        BankWidgetExtension ext = getBankExtension();
        if (ext != null)
            Actions.menu(Actions.MENU_EXECUTE_WIDGET, 1, -1, Interfaces.hash(ext.getRootId(), ext.getDepositEquipmentButtonId()), 0);
    }

    /**
     * Determines if items are being withdrawn as notes or not.
     */
    public static boolean isWithdrawingNotes() {
        BankWidgetExtension ext = getBankExtension();
        return ext != null && ConfigProvider.getVarpValue(ext.getWithdrawAsNoteConVarId()) == 1;
    }

    /**
     * Changes whether or not we are withdrawing items in noted form.
     */
    public static void setWithdrawingNotes(boolean notes) {
        BankWidgetExtension ext = getBankExtension();
        if (ext != null && isWithdrawingNotes() != notes) {
            Actions.menu(Actions.MENU_EXECUTE_WIDGET, 1, -1, Interfaces.hash(ext.getRootId(), ext.getToggleNotesButtonId()), 0);
        }
    }

    /**
     * Checks if the bank component extension can be fetched.
     *
     * Returns:
     * the bank extension if it can, or null.
     */
    public static BankWidgetExtension getBankExtension() {
        if(!BANK.hasExtension(BankWidgetExtension.class)) {
            return null;
        }
        return (BankWidgetExtension) BANK.getExt(BankWidgetExtension.class);
    }

    @NotNull
    @Override
    public List<Extension> getExtensions() {
        return extensions.values().stream().toList();
    }
}
