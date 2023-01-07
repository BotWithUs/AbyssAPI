package abyss.plugin.api.entities;

import abyss.Utils;
import abyss.plugin.api.Client;
import abyss.plugin.api.Debug;
import abyss.plugin.api.EquipmentSlot;
import abyss.plugin.api.entities.state.Headbar;
import abyss.plugin.api.Item;
import abyss.plugin.api.query.players.PlayerQuery;
import abyss.plugin.api.variables.Variables;
import abyss.plugin.api.world.WorldTile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A snapshot of a player within the game world. This data is constant,
 * and will not be changed after this object is created.
 */
public final class Player extends PathingEntity {
    private final Map<EquipmentSlot, Integer> wornItems;
    private int totalLevel;
    private int combatLevel;
    private int targetIndex;

    /**
     * Do not make instances of this.
     */
    private Player() {
        wornItems = new HashMap<>();
    }

    private void setEquipmentSlot(int slot, int wornItemId) {
        EquipmentSlot eslot = EquipmentSlot.forSlot(slot);
        if(eslot != null) {
            wornItems.put(eslot, wornItemId);
        }
    }

    @Override
    public boolean isReachable() {
        Player self = PlayerQuery.self();
        if (self == null) {
            return false;
        }

        return Utils.getRouteDistanceTo(self.getGlobalPosition(), new WorldTile(getGlobalPosition().getX(), getGlobalPosition().getY(), getGlobalPosition().getZ())) != -1;
    }

    /**
     * Determines if this player is under attack currently.
     * This only works for the local player and should be considered for revision.
     *
     * @return If this player is under attack currently.
     */
    public boolean isUnderAttack() {
        return Variables.IN_COMBAT.getValue() == 1;
    }

    /**
     * Retrieves all visible worn items that this player is wearing. Not all equipment is visible to remote players (e.g. ring slot.)
     *
     * @return All worn items that this player is wearing.
     */
    public Map<EquipmentSlot, Item> getWornItems() {
        Map<EquipmentSlot, Item> conv = new HashMap<>();
        for (EquipmentSlot slot : wornItems.keySet()) {
            int id = wornItems.get(slot);
            if (id != -1) {
                conv.put(slot, new Item(id));
            }
        }
        return conv;
    }

    /**
     * Gets the visible worn item that this player is wearing in the specified slot
     *
     * @param slot the slot to check for a visible worn item
     * @return the worn item, otherwise null
     */
    public Item getWornItem(EquipmentSlot slot) {
        int id = wornItems.get(slot);
        if (id == -1) {
            return null;
        }
        return new Item(id);
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
     * If you need the local player's health, you can use the more reliable {@link Client#getCurrentHealth()}
     *
     * @return This player's health percentage, or -1 if not in combat.
     */
    public float getHealth() {
        List<Headbar> healthBars = getHeadbars(STATUS_HEALTH);
        if (healthBars.size() != 1) {
            return -1;
        }
        return healthBars.get(0).getValue() / 255.0f;
    }

    /**
     * Retrieves this player's adrenaline fill percentage (0-1), if currently available.
     *
     * @return This player's adrenaline fill percentage, or -1 if not available.
     */
    public float getAdrenaline() {
        List<Headbar> adrenalineBar = getHeadbars(STATUS_ADRENALINE);
        if (adrenalineBar.size() != 1) {
            return -1;
        }
        return adrenalineBar.get(0).getValue() / 255.0f;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    @Override
    public String toString() {
        return "Player{" +
                "identifier=" + getIdentifier() +
                '}';
    }
}
