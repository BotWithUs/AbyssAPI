package abyss.plugin.api;

import abyss.Utils;

import static abyss.plugin.api.Actions.*;

/**
 * A snapshot of a non-playable-character within the game world. This data is constant,
 * and will not be changed after this object is created.
 */
public final class Npc extends Spirit {

    private static final int[] OPTION_NAME_MAP = {
            MENU_EXECUTE_NPC1,
            MENU_EXECUTE_NPC2,
            MENU_EXECUTE_NPC3,
            MENU_EXECUTE_NPC4,
            MENU_EXECUTE_NPC5,
            MENU_EXECUTE_NPC6,
    };

    private int id;
    private int health;
    private int transformedId = -1;

    private CacheNpc type;

    /**
     * Do not make instances of this.
     */
    private Npc() {
    }

    /**
     * Rerieves this NPC's id.
     *
     * @return This NPC's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves this NPC's health.
     *
     * @return This NPC's health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Retrieves the names of options when right clicking this NPC.
     *
     * @return The names of options when right clicking this NPC.
     */
    public String[] getOptionNames() {
        if(type == null) {
            return new String[0];
        }
        return type.getOptionNames();
    }

    @Override
    public String getName() {
        return type == null ? "Unknown " + id : type.getName();
    }

    /**
     * Interacts with this NPC.
     */
    public boolean interact(String option) {
        String[] options = getOptionNames();
        int m = Math.min(OPTION_NAME_MAP.length, options.length);
        for (int i = 0; i < m; i++) {
            if (option.equalsIgnoreCase(options[i])) {
                interact(OPTION_NAME_MAP[i]);
                return true;
            }
        }

        Debug.log("Failed to find option '" + option + "' on NPC '" + getName() + "'");
        Debug.log("Available options:");
        for (String s : getOptionNames()) {
            Debug.log(" " + s);
        }
        return false;
    }

    /**
     * Retrieves the id of the NPC cache description that this NPC is transformed into.
     *
     * @return The id of the NPC that this NPC is transformed into.
     */
    public int getTransformedId() {
        return transformedId;
    }

    /**
     * Determines if this NPC has been tagged by a player.
     */
    public boolean isTagged() {
        return getName().contains("*");
    }

    public CacheNpc getType() {
        return type;
    }

    @Override
    public boolean isReachable() {
        return Utils.isNpcReachable(this);
    }

    @Override
    public String toString() {
        return "Npc{" +
                "serverIndex=" + getServerIndex() +
                '}';
    }

}
