package abyss.plugin.api.components;

import abyss.plugin.api.Actions;
import abyss.plugin.api.Input;
import abyss.plugin.api.Item;
import abyss.plugin.api.Vector2i;

public final class WidgetComponent {

    private int type;
    private int parentId;
    private int childId;
    private int slot;
    private int textureId;
    private int disabledTextureId;

    private int renderIndex;

    private boolean visible;

    private Vector2i screenPosition;
    private String text;
    private Item item;

    public WidgetComponent() {
        this.type = -1;
        this.parentId = -1;
        this.childId = -1;
        this.slot = -1;
        this.textureId = -1;
        this.disabledTextureId = -1;
        this.text = "";
        this.item = new Item(0, 0);
    }

    public int getType() {
        return type;
    }

    public int getParentId() {
        return parentId;
    }

    public int getChildId() {
        return childId;
    }

    public int getSlot() {
        return slot;
    }

    public int getTextureId() {
        return textureId;
    }

    public int getDisabledTextureId() {
        return disabledTextureId;
    }

    public int getRenderIndex() {
        return renderIndex;
    }

    public boolean isVisible() {
        return visible;
    }

    public Vector2i getScreenPosition() {
        return screenPosition;
    }

    public String getText() {
        return text;
    }

    public Item getItem() {
        return item;
    }

    public void hover() {
        if(screenPosition != null) {
            Input.moveMouse(screenPosition.getX(), screenPosition.getY());
        }
    }

    public boolean interact(int option) {
        if(parentId == -1 || childId == -1)
            return false;
        if(slot != -1) {
            Actions.menu(Actions.MENU_EXECUTE_WIDGET, option, slot, (parentId << 16) | childId, 1);
        } else {
            Actions.menu(Actions.MENU_EXECUTE_WIDGET, option, -1, (parentId << 16) | childId, 1);
        }
        return true;
    }

    public boolean interact() {
        return interact(-1);
    }
}