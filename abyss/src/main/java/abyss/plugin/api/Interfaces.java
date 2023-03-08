package abyss.plugin.api;

import abyss.bindings.MethodBuilder;
import abyss.bindings.NativeLoader;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/**
 * A provider of widgets.
 */
public final class Interfaces {

    private Interfaces() {
    }

    /**
     * Retrieves a widget group by id.
     *
     * @param id The id of the widget to retrieve.
     * @return The group with the provided id, or NULL if one was not found.
     */
    public static Interface getById(int id) {
        return Interface.getByIndex(id);
    }

    /**
     * Determines if an Interface is open.
     *
     * @param id The id of the widget to check the visibility of.
     * @return If the widget with the provided id is open.
     */
    public static boolean isOpen(int id) {
        Interface iface = getById(id);
        if (iface == null) {
            return false;
        }

        for (Component w : iface.getComponents()) {
            if (w.isVisible()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if there is a selected component present
     * @return True if there is a selected component present
     */

    public static native boolean hasSelectedComponent();

    private static native Component getSelectedComponent0();


    /**
     * Gets the selected component (normally when you click use on an item)
     * @return The selected component
     */
    public static Optional<Component> getSelectedComponent() {
        if(hasSelectedComponent()) {
            return Optional.ofNullable(getSelectedComponent0());
        }
        return Optional.empty();
    }

    /**
     * Retrieves a widget by walking through the children in each widget.
     *
     * @param interfaceId    The id of the group.
     * @param componentId    The index of the widget within the group.
     * @param subcomponentId The indices of each child.
     * @return The found widget, or NULL if one was not found (not a container, not visible, etc.)
     */
    public static Component getComponent(int interfaceId, int componentId, int subcomponentId) {
        Interface iface = getById(interfaceId);
        if (iface == null) {
            return null;
        }
        Component component = iface.getComponent(componentId);
        if (component == null) {
            return null;
        }
        return component.getChild(subcomponentId);
    }

    public static Component filter(int interfaceId, Predicate<Component> predicate) {
        Interface group = getById(interfaceId);
        if (group == null)
            return null;
        Component component = group.getComponent(0);
        if (component == null) {
            return null;
        }
        if (predicate.test(component)) {
            return component;
        }
        return filter(component.getChildren(), predicate);
    }

    public static Component filter(Component[] children, Predicate<Component> predicate) {
        if (children == null || children.length == 0) {
            return null;
        }
        for (Component child : children) {
            if (child == null)
                continue;
            if (predicate.test(child)) {
                return child;
            }
            Component found = filter(child.getChildren(), predicate);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public static int hash(int parentId, int childId) {
        return (parentId << 16) + childId;
    }

    public static int getParentId(int hash) {
        return hash >> 16;
    }

    public static int getChildId(int hash) {
        return hash & 65535;
    }

    public static void bind(BiConsumer<Class<?>, MethodBuilder> registerNativeMethods) {
        registerNativeMethods.accept(Interfaces.class, NativeLoader
                .newMethod("hasSelectedComponent")
                .setReturnType(boolean.class));
    }
}
