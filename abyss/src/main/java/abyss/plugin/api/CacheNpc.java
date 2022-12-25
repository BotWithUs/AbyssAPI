package abyss.plugin.api;

/**
 * An NPC configuration from the cache.
 */
public final class CacheNpc extends AsyncData {

    private int id = 0;
    private long address = 0;
    private String name;
    private String[] options;

    private byte[] binaryName;
    private byte[][] binaryOptionNames;

    private int varbitId = -1;
    private int[] transformIds = new int[0];

    private int size;
    private int combatLevel;
    private boolean hasMapDot;

    CacheNpc(int id) {
        this.id = id;
        this.name = "N/A";
        this.options = new String[0];
    }

    /**
     * @return The id of the NPC.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the address of this cache descriptor in memory.
     * This will only be valid in developer builds.
     *
     * @return The address of this cache descriptor in memory.
     */
    public long getAddress() {
        return address;
    }

    /**
     * @return The name of the NPC.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The name of options when right clicking this NPC.
     */
    public String[] getOptionNames() {
        return options;
    }

    /**
     * Size of the npc
     */
    public int getSize() {
        return size;
    }

    /**
     * Combat Level of the npc
     */

    public int getCombatLevel() {
        return combatLevel;
    }

    /**
     * Does the npc show up on the minimap
     */

    public boolean hasMapDot() {
        return hasMapDot;
    }

    /**
     * Retrieves the id to use for varbit configuration on this NPC.
     *
     * @return The id to use for varbit configuration.
     */
    public int getVarbitId() {
        return varbitId;
    }

    /**
     * Retrieves the NPC ids that this NPC can transform into.
     *
     * @return The transform ids for this NPC.
     */
    public int[] getTransformIds() {
        return transformIds;
    }

    /**
     * Determines if this NPC is valid.
     *
     * @return If this NPC is valid.
     */
    public boolean isValid() {
        return super.isLoaded() && !name.equals("N/A") && options.length != 0;
    }
}
