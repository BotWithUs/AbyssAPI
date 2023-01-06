package abyss.plugin.api;

import abyss.plugin.api.extensions.SimpleExtensionContainer;

import java.util.Objects;

/**
 * A generic item.
 */
public class Item extends SimpleExtensionContainer {

    public static final Item EMPTY = new Item(-1, 0);


    private int id;
    private int amount;

    private CacheItem type;

    public Item() {
    }

    public Item(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public Item(int id, int amount, CacheItem type) {
        this(id, amount);
        this.type = type;
    }

    public Item(int id) {
        this(id, 1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
    * Retrieves the name of this item.
    *
    * @return The name of this item.
    */
    public String getName() {
        if(type == null) {
            type = Cache.getItem(id, true);
        }
        if (type == null) {
            return Abyss.BAD_DATA_STRING;
        }

        return type.getName();
    }

    /**
    * Retrieves the names of all options on this item.
    *
    * @return The names of all options on this item.
    */
    public String[] getOptionNames() {
        if(type == null) {
            type = Cache.getItem(id, true);
        }
        if(type == null) {
            return new String[0];
        }
        return type.getOptionNames();
    }

    @Override
    public String toString() {
        return "Item{" +
               "id=" + id +
               ", amount=" + amount +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
               amount == item.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

    public ComponentItem toComponentItem(int slot, int widgetId, Inventory container) {
        return new ComponentItem(id, amount, slot, widgetId, type, container);
    }
}
