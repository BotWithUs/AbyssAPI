package abyss.definitions.map;

import kotlin.collections.MapsKt;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ObjectTilesDefinition {

    private final int regionId;
    private final List<MapObject> objects;

    public ObjectTilesDefinition(int regionId) {
        this.regionId = regionId;
        this.objects = new ArrayList<>();
    }

    public List<MapObject> getObjects() {
        return objects;
    }

    public int getId() {
        return this.regionId;
    }

    @NotNull
    public JsonObject toJsonObject() {
        return new JsonObject(MapsKt.emptyMap());
    }
}
