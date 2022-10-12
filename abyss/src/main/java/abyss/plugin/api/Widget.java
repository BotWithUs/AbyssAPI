package abyss.plugin.api;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.function.Predicate;

import static abyss.plugin.api.Text.filterSpecialChars;

/**
 * A snapshot of a widget. This data is constant, and will not be
 * changed after this object is created.
 */
public class Widget {

    public static final int INVALID = -1;
    public static final int CONTAINER = 0;
    public static final int TEXT = 4;
    public static final int MEDIA = 5;

    // internal values, attempting to use these will break the client

    private long internal1;

    private int type = -1;
    private Widget[] children;
    private boolean visible = false;
    private byte[] textBinary;
    private Item item = new Item(-1, 0);
    private Vector2i position;
    private Vector2i size;
    private Vector2i screenPosition;
    private int textureIdDisabled = -1;
    private int textureIdEnabled = -1;
    private int parentId = -1;
    private int childId = -1;

    /**
     * Do not make instances of this.
     */
    private Widget() {
    }

    /**
     * Calculates the interact id of this widget.
     *
     * @return The interact id of this widget.
     */
    public int getInteractId() {
        return ((parentId & 0xffff) << 16) | (childId & 0xffff);
    }

    /**
     * Retrieves the type of this widget.
     *
     * @return The type of this widget.
     */
    public int getType() {
        return type;
    }

    /**
     * Retrieves the children in this widget.
     *
     * @return The children in this widget.
     */
    public Widget[] getChildren() {
        return children;
    }

    /**
     * Determines if this widget has been rendered onto the screen recently.
     *
     * @return If this widget has been rendered onto the screen recently.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Retrieves a child by index. Will return NULL if index is out of bounds.
     */
    public Widget getChild(int index) {
        if (type != Widget.CONTAINER) {
            return null;
        }

        if (index < 0 || index >= children.length) {
            return null;
        }

        return children[index];
    }

    /**
     * Retrieves a child by index. Will return NULL if index is out of bounds.
     */
    public Widget getChild(Predicate<Widget> filter, int... indices) {
        Widget w = this;
        for (int i = 0; i < indices.length && w != null && filter.test(w); i++) {
            w = getChild(indices[i]);
        }
        return w;
    }

    /**
     * Retrieves a child by index. Will return NULL if index is out of bounds.
     */
    public Widget getChild(int... indices) {
        Widget w = this;
        for (int i = 0; i < indices.length && w != null; i++) {
            w = getChild(indices[i]);
        }
        return w;
    }

    /**
     * Retrieves the text being stored in this widget.
     *
     * @return The text being stored in this widget, or NULL if the widget has no text.
     */
    public byte[] getTextBinary() {
        return textBinary;
    }

    /**
     * Retrieves the text being stored in this widget.
     *
     * @return The text being stored in this widget, or NULL if the widget has no text.
     */
    public String getText() {
        byte[] bin = getTextBinary();
        if (bin == null) {
            return null;
        }

        return new String(filterSpecialChars(bin), StandardCharsets.US_ASCII);
    }

    /**
     * Retrieves the item being stored in this widget.
     *
     * @return The item being stored in this widget, or NULL if the widget has no item.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Retrieves the position of this widget on the screen. May not be valid for all widgets.
     *
     * @return The position of this widget.
     */
    public Vector2i getPosition() {
        return position;
    }

    /**
     * Retrieves the size of this widget on the screen. May not be valid for all widgets.
     *
     * @return The size of this widget.
     */
    public Vector2i getSize() {
        return size;
    }

    /**
     * Retrieves the texture id this widget displays when the widget is in a disabled state.
     *
     * @return The texture id this widget displays when the widget is in a disabled state.
     */
    public int getTextureIdDisabled() {
        return textureIdDisabled;
    }

    /**
     * Retrieves the texture id this widget displays when the widget is in an enabled state.
     *
     * @return The texture id this widget displays when the widget is in an enabled state.
     */
    public int getTextureIdEnabled() {
        return textureIdEnabled;
    }

    /**
     * @return The id of the group this widget is in.
     */
    public int getParentId() {
        return parentId;
    }

    /**
     * @return The id of this widget within the group.
     */
    public int getChildId() {
        return childId;
    }

    public boolean hover() {
        if(screenPosition != null) {
            Input.moveMouse(screenPosition.getX(), screenPosition.getY());
            return true;
        }
        return false;
    }

    public void mouseClick() {
        if(hover()) {
            Input.clickMouse(0);
        }
    }

    public Vector2i getScreenPosition() {
        return screenPosition;
    }

    /**
     * Interacts with this widget using the provided option.
     *
     * @param option The option to use.
     */
    public void interact(int option) {
        Actions.menu(Actions.MENU_EXECUTE_WIDGET, 1, option, getInteractId(), 0);
    }

    /**
     * Interacts with this widget using the default option.
     */
    public void interact() {
        interact(-1);
    }

    @Override
    public String toString() {
        return "Widget{" +
                "type= " + getType() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Widget widget = (Widget) o;
        return internal1 == widget.internal1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(internal1);
    }
}
