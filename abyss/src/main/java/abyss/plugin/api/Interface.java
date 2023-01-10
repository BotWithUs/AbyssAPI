package abyss.plugin.api;

import java.util.List;
import java.util.Objects;

/**
 * A group of components.
 */
public class Interface {

    // internal values, attempting to use these will break the client
    private long internal;

    private int id;

    /**
     * Do not make instances of this.
     */
    private Interface() {
    }

    /**
     * Retrieves the id of this Interface.
     *
     * @return The id of this Interface.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves a component by index. Will return null if index is out of bounds.
     */
    public native Component getComponent(int index);

    /**
     * Retrieves all widgets in this group.
     *
     * @return All widgets in this group.
     */
    public native List<Component> getComponents();

    @Override
    public String toString() {
        return "Interface{" +
                "id=" + getId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interface that = (Interface) o;
        return internal == that.internal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(internal);
    }

    public static native Interface getByIndex(int index);
}
