package abyss.plugin.api.entities;

import abyss.Utils;
import abyss.plugin.api.Area3di;
import abyss.plugin.api.CacheNpc;
import abyss.plugin.api.Debug;
import abyss.plugin.api.Vector3i;

import java.awt.geom.Area;
import java.util.function.BiPredicate;

import static abyss.plugin.api.Actions.*;

/**
 * A snapshot of a non-playable-character within the game world. This data is constant,
 * and will not be changed after this object is created.
 */
public final class Npc extends PathingEntity {

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

    private int maxHealth;

    private int transformedId = -1;

    private int combatLevel;

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
     * Retrieves this NPC's maximum health
     * @return The maximum amount of health this npc can have
     */

    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Retrieves this NPC's combat level, repersenting the strength of the npc.
     * @return The representation of strength for a npc while in combat
     */

    public int getCombatLevel() {
        return combatLevel;
    }

    /**
     * Retrieves the names of options when right clicking this NPC.
     *
     * @return The names of options when right clicking this NPC.
     */
    public String[] getOptionNames() {
        if (type == null) {
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
    public boolean interact(String option, BiPredicate<String, String> predicate) {
        if (option == null || predicate == null) {
            return false;
        }
        String[] options = getOptionNames();
        int m = Math.min(OPTION_NAME_MAP.length, options.length);
        for (int i = 0; i < m; i++) {
            if (options[i] == null) {
                continue;
            }
            if (predicate.test(option, options[i])) {
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

    public boolean interact(String option) {
        return interact(option, (o1, o2) -> o2.contains(o1));
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

    /**
     * Gets a varbit value from the npc variable domain
     * @param varbitId Varbit ID related to npcs
     * @return The npc varbit value
     */

    public native int getVarbitValue(int varbitId);

    /**
     * Gets a varp value from the npc variable domain
     * @param varpId Varp ID related to npcs
     * @return The npc varp value
     */

    public native int getVarpValue(int varpId);

    @Override
    public Area3di getGlobalArea() {
        int size = 1;
        CacheNpc type = getType();
        if (type != null) {
            size = type.getSize();
        }
        Vector3i base = getGlobalPosition();
        return new Area3di(base, new Vector3i(base.getX() + size, base.getY() + size, base.getZ()));
    }

    @Override
    public String toString() {
        return "Npc{" +
                "serverIndex=" + getIdentifier() +
                '}';
    }

}
