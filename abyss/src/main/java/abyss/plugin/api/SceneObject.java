package abyss.plugin.api;

import abyss.Utils;
import abyss.map.Region;

import static abyss.plugin.api.Actions.*;

/**
 * An object within the game world.
 */
public class SceneObject extends Entity {

    private static final int[] OPTION_NAME_MAP = {
            MENU_EXECUTE_OBJECT1,
            MENU_EXECUTE_OBJECT2,
            MENU_EXECUTE_OBJECT3,
            MENU_EXECUTE_OBJECT4,
            MENU_EXECUTE_OBJECT5,
            MENU_EXECUTE_OBJECT6,
    };
    private int id;
    private boolean hidden;
    private Vector2i size;
    private int interactId = -1;

    private CacheObject type;

    /**
     * Do not make instances of this.
     */
    private SceneObject() {
    }

    public CacheObject getType() {        
        return type;
    }

    /**
     * Retrieves the id of this object.
     *
     * @return The id of this object.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the interact id of this object.
     *
     * @return The interact id of this object.
     */
    public int getInteractId() {
        if (interactId == -1) {
            return id;
        }
        return interactId;
    }

    /**
     * Determines if this object has been hidden.
     *
     * @return If this object has been hidden.
     */
    public boolean hidden() {
        return hidden;
    }

    /**
     * Interacts with this object.
     */
    public void interact(int type) {
        entity(this, type);
    }

    /**
     * Interacts with this object.
     */
    public void interact(int type, int xOff, int yOff) {
        entity(this, type, xOff, yOff);
    }

    /**
     * Interacts with this object.
     */
    public boolean interact(String option) {
        CacheObject type = getType();
        if (type == null) {
            return false;
        }

        String[] options = type.getOptionNames();
        int m = Math.min(OPTION_NAME_MAP.length, options.length);
        for (int i = 0; i < m; i++) {
            if (option.contains(options[i])) {
                interact(OPTION_NAME_MAP[i]);
                return true;
            }
        }

        Debug.log("Failed to find option '" + option + "' on object '" + getName() + "'");
        Debug.log("Available options:");
        for (String s : options) {
            Debug.log(" " + s);
        }

        return false;
    }

    /**
     * Retrieves the size of this object.
     *
     * @return The size of this object.
     */
    public Vector2i getSize() {
        return size;
    }

    /**
     * Retrieves the name of this object.
     *
     * @return The name of this object.
     */
    public String getName() {
        CacheObject type = getType();
        if (type == null) {
            return Abyss.BAD_DATA_STRING;
        }

        return type.getName();
    }

    public boolean isReachable() {
        return Utils.isSceneObjectReachable(this);
    }

    @Override
    public String toString() {
        return "SceneObject{" +
                "id=" + id +
                ", hidden=" + hidden +
                ", size=" + size +
                '}';
    }
}
