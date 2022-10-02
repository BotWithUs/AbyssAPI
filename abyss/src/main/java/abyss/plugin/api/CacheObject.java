package abyss.plugin.api;

import java.nio.charset.StandardCharsets;

/**
 * An object configuration from the cache.
 */
public final class CacheObject extends AsyncData {

    private int id = 0;
    private long address = 0;
    private byte[] binaryName = Abyss.BAD_DATA_STRING.getBytes(StandardCharsets.US_ASCII);
    private byte[][] binaryOptionNames = new byte[0][];

    private int sizeX;
    private int sizeY;

    private boolean blocksProjectile;
    private boolean accessBlockFlag;
    private boolean ignoreAltClip;

    private int clipType;

    CacheObject(int id) {
        this.id = id;
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
        return new String(binaryName);
    }

    /**
     * @return The name of options when right clicking this object.
     */
    public String[] getOptionNames() {
        String[] options = new String[binaryOptionNames.length];
        for (int i = 0; i < binaryOptionNames.length; i++) {
            options[i] = binaryOptionNames[i] == null ? "" : new String(binaryOptionNames[i]);
        }
        return options;
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
        return super.isLoaded() && binaryName != null && binaryOptionNames != null;
    }
}
