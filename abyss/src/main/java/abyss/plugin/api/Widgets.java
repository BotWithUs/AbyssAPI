package abyss.plugin.api;

import java.util.Locale;
import java.util.function.Predicate;

/**
 * A provider of widgets.
 */
public final class Widgets {

    private Widgets() {
    }

    /**
     * Retrieves a widget group by id.
     *
     * @param id The id of the widget to retrieve.
     * @return The group with the provided id, or NULL if one was not found.
     */
    public static native WidgetGroup getGroupById(int id);

    /**
     * Determines if a widget is open.
     *
     * @param id The id of the widget to check the visibility of.
     * @return If the widget with the provided is is open.
     */
    public static boolean isOpen(int id) {
        WidgetGroup group = getGroupById(id);
        if (group == null) {
            return false;
        }

        for (Widget w : group.getWidgets()) {
            if (w.isVisible()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Retrieves a widget by walking through the children in each widget.
     *
     * @param group The id of the group.
     * @param groupIndex The index of the widget within the group.
     * @param childIndices The indices of each child.
     * @return The found widget, or NULL if one was not found (not a container, not visible, etc.)
     */
    public static Widget getChild(int group, int groupIndex, int... childIndices) {
        WidgetGroup g = getGroupById(group);
        if (g == null) {
            return null;
        }

        Widget cur = g.getWidget(groupIndex);
        for (int i = 0; i < childIndices.length && cur != null; i++) {
            cur = cur.getChild(childIndices[i]);
        }
        return cur;
    }

    public static Widget filter(int groupId, Predicate<Widget> predicate) {
        WidgetGroup group = getGroupById(groupId);
        if(group == null)
            return null;
        Widget widget = group.getWidget(0);
        if(widget == null) {
            return null;
        }
        if(predicate.test(widget)) {
            return widget;
        }
        return filter(widget.getChildren(), predicate);
    }

    public static Widget filter(Widget[] children, Predicate<Widget> predicate) {
        if(children == null || children.length == 0) {
            return null;
        }
        for (Widget child : children) {
            if(child == null)
                continue;
            if(predicate.test(child)) {
                return child;
            }
            Widget found = filter(child.getChildren(), predicate);
            if(found != null) {
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
}
