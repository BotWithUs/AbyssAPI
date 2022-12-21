package abyss.plugin.api;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * A snapshot of a widget. This data is constant, and will not be
 * changed after this object is created.
 */
public class Component {
    public static final int LAYER = 0;
    public static final int RECTANGLE = 3;
    public static final int TEXT = 4;
    public static final int SPRITE = 5;
    public static final int MODEL = 6;

    // internal values, attempting to use these will break the client
    private long internal1;

    private int type = -1;
    private Component[] children;
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

    private int slot = -1;

    /**
     * Do not make instances of this.
     */
    private Component() {
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
    public Component[] getChildren() {
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
    public Component getChild(int index) {
        if (type != Component.LAYER) {
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
    public Component getChild(Predicate<Component> filter, int... indices) {
        Component w = this;
        for (int i = 0; i < indices.length && w != null && filter.test(w); i++) {
            w = w.getChild(indices[i]);
        }
        return w;
    }

    /**
     * Retrieves a child by index. Will return NULL if index is out of bounds.
     */
    public Component getChild(int... indices) {
        Component w = this;
        for (int i = 0; i < indices.length && w != null; i++) {
            w = w.getChild(indices[i]);
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

        return new String(TextUtils.filterSpecialChars(bin), StandardCharsets.US_ASCII);
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
        if (screenPosition != null) {
            Input.moveMouse(screenPosition.getX(), screenPosition.getY());
            return true;
        }
        return false;
    }

    public void mouseClick() {
        if (hover()) {
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
        if (slot != -1) {
            Actions.menu(Actions.MENU_EXECUTE_WIDGET, option, slot, getInteractId(), 0);
        } else {
            Actions.menu(Actions.MENU_EXECUTE_WIDGET, 1, option, getInteractId(), 0);
        }
    }

    /**
     * Interacts with this widget using the default option.
     */
    public void interact() {
        interact(-1);
    }

    @Override
    public String toString() {
        return "Component{" +
                "type= " + getType() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return internal1 == component.internal1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(internal1);
    }
}
