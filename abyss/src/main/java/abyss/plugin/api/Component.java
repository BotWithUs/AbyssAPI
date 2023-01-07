package abyss.plugin.api;

import java.util.List;
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

    private final int type;
    private final boolean visible;
    private final String text;
    private final int fontId;
    private final int verticalAlignment;
    private final int horizontalAlignment;
    private final int color;
    private final int alpha;
    private final Item item;
    private Vector2i position;
    private Vector2i size;
    private Vector2i screenPosition;
    private final int textureIdDisabled;
    private final int textureIdEnabled;
    private final int interfaceIndex;
    private final int componentIndex;

    private final int subComponentIndex;

    private final String[] actions;

    /**
     * Do not make instances of this.
     */
    private Component() {
        text = "";
        actions = new String[0];
        textureIdDisabled = -1;
        textureIdEnabled = -1;
        subComponentIndex = -1;
        interfaceIndex = -1;
        componentIndex = -1;
        item = Item.EMPTY;
        visible = false;
        type = -1;
        alpha = -1;
        color = -1;
        horizontalAlignment = -1;
        verticalAlignment = -1;
        fontId = -1;
    }

    /**
     * Calculates the interact id of this widget.
     *
     * @return The interact id of this widget.
     */
    public int getInteractId() {
        return ((interfaceIndex & 0xffff) << 16) | (componentIndex & 0xffff);
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
        if(type == 0) {
            return getChildren0().toArray(Component[]::new);
        }
        return new Component[0];
    }

    private native List<Component> getChildren0();

    /**
     * Determines if this widget has been rendered onto the screen recently.
     *
     * @return If this widget has been rendered onto the screen recently.
     */
    public boolean isVisible() {
        return visible;
    }

    public Component getChild(int index) {
        if(type == 0) {
            return getChild0(index);
        }
        return null;
    }

    /**
     * Retrieves a child by index. Will return NULL if index is out of bounds.
     */
    private native Component getChild0(int index);

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
    public String getText() {
        return text;
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
    public int getInterfaceIndex() {
        return interfaceIndex;
    }

    /**
     * @return The id of this widget within the group.
     */
    public int getComponentIndex() {
        return componentIndex;
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
        Debug.log("Option " + option + " : " + interfaceIndex + " - " + componentIndex);
        if (subComponentIndex != -1) {
            Actions.menu(Actions.MENU_EXECUTE_WIDGET, option, subComponentIndex, getInteractId(), 0);
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

    public int getVerticalAlignment() {
        return verticalAlignment;
    }

    public int getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public int getColor() {
        return color;
    }

    public int getAlpha() {
        return alpha;
    }

    public int getSubComponentIndex() {
        return subComponentIndex;
    }

    public int getFontId() {
        return fontId;
    }

    public String[] getActions() {
        return actions;
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
