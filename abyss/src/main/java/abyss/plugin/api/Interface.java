package abyss.plugin.api;

import java.util.Objects;

/**
 * A group of components.
 */
public class Interface {

    // internal values, attempting to use these will break the client
    private long internal1;

    private int id;
    private Component[] components;

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
    public Component getComponent(int index) {
        // nobody actually wants to check for this or handle this..
        if (index < 0 || index >= components.length) {
            return null;
        }
        return components[index];
    }

    /**
     * Retrieves all widgets in this group.
     *
     * @return All widgets in this group.
     */
    public Component[] getComponents() {
        return components;
    }

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
        return internal1 == that.internal1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(internal1);
    }
}
