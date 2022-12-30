package abyss.plugin.api;

import java.nio.charset.StandardCharsets;

/**
 * An object configuration from the cache.
 */
public final class CacheObject extends AsyncData {

    private int id = 0;
    private final long address = 0;

    private final String name;

    private final String[] actions;

    private int sizeX;
    private int sizeY;

    private boolean blocksProjectile;
    private boolean accessBlockFlag;
    private boolean ignoreAltClip;

    private int clipType;

    CacheObject(int id) {
        this.id = id;
        this.name = "";
        this.actions = new String[0];
    }

    /**
     * @return The id of the object.
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
     * @return The name of the object.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The name of options when right clicking this object.
     */
    public String[] getOptionNames() {
        return actions;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public boolean isAccessBlockFlag() {
        return accessBlockFlag;
    }

    public boolean isIgnoreAltClip() {
        return ignoreAltClip;
    }

    public boolean isBlocksProjectile() {
        return blocksProjectile;
    }

    public int getClipType() {
        return clipType;
    }

    /**
     * Determines if this object is valid.
     *
     * @return If this object is valid.
     */
    public boolean isValid() {
        return super.isLoaded() && !name.isEmpty() && actions.length > 0;
    }
}
