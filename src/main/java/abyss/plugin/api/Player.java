package abyss.plugin.api;

import abyss.plugin.api.variables.Variables;

import java.util.HashMap;
import java.util.Map;

/**
 * A snapshot of a player within the game world. This data is constant,
 * and will not be changed after this object is created.
 */
public final class Player extends Spirit {

    private Map<EquipmentSlot, Integer> equipment = new HashMap<>();
    private int totalLevel;
    private int combatLevel;

    private int targetIndex;

    /**
     * Do not make instances of this.
     */
    private Player() {
    }

    /**
     * Determines if this player is under attack currently.
     *
     * @return If this player is under attack currently.
     */
    public boolean isUnderAttack() {
        return Variables.IN_COMBAT.getValue() == 1;
    }

    /**
     * Retrieves all equipment this player is wearing. Not all equipment is visible to remote players (e.g. ring slot.)
     *
     * @return All equipment this player is wearing.
     */
    public Map<EquipmentSlot, Item> getEquipment() {
        Map<EquipmentSlot, Item> conv = new HashMap<>();
        for (EquipmentSlot slot : equipment.keySet()) {
            int id = equipment.get(slot);
            conv.put(slot, new Item(id));
        }
        return conv;
    }

    /**
     * Retrieves the total level of this player. This may only be valid if their total level is displayed.
     *
     * @return The total level of this player, if accessible.
     */
    public int getTotalLevel() {
        return totalLevel;
    }

    /**
     * Retrieves the combat level of this player. This may only be valid if their combat level is displayed.
     *
     * @return The combat level of this player, if accessible.
     */
    public int getCombatLevel() {
        return combatLevel;
    }

    /**
     * Retrieves this player's health percentage (0-1), if they are currently in combat.
     * The status bar must be displayed above the player's head to retrieve their health.
     * If you need the local player's health, you can use the more reliable {@link Client#getCurrentHealth() Client#getCurrentHealth}
     *
     * @return This player's health percentage, or 0 if not in combat.
     */
    public float getHealth() {
        if (!isStatusBarActive(STATUS_HEALTH)) {
            return 0.0f;
        }

        return getStatusBarFill(STATUS_HEALTH);
    }

    /**
     * Retrieves this player's adrenaline fill percentage (0-1), if currently available.
     *
     * @return This player's adrenaline fill percentage, or 0 if not available.
     */
    public float getAdrenaline() {
        if (!isStatusBarActive(STATUS_ADRENALINE)) {
            return 0.0f;
        }

        return getStatusBarFill(STATUS_ADRENALINE);
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    @Override
    public String toString() {
        return "Player{" +
                "serverIndex=" + getServerIndex() +
                '}';
    }
}
