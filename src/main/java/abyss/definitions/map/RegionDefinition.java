package abyss.definitions.map;

import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegionDefinition {

    private final int regionId;
    private final List<MapObject> objects;
    public int[][][] overlayIds;
    public int[][][] underlayIds;
    public byte[][][] overlayPathShapes;
    public byte[][][] overlayRotations;
    public byte[][][] settings;

    public RegionDefinition(int regionId) {
        this.regionId = regionId;
        this.objects = new ArrayList<>();
        this.overlayIds = new int[4][64][64];
        this.underlayIds = new int[4][64][64];
        this.overlayPathShapes = new byte[4][64][64];
        this.overlayRotations = new byte[4][64][64];
        this.settings = new byte[4][64][64];
    }

    public int getId() {
        return this.regionId;
    }

    public int getRegionId() {
        return regionId;
    }

    public List<MapObject> getObjects() {
        return objects;
    }

    @NotNull
    public JsonObject toJsonObject() {
        return new JsonObject(Collections.emptyMap());
    }

    public static int getMapArchiveId(int rx, int ry) {
        return rx | ry << 7;
    }
}
