package abyss.map;

import com.abyss.definitions.ObjectDefinition;
import com.abyss.definitions.ObjectType;
import abyss.plugin.api.Cache;
import abyss.plugin.api.CacheObject;
import abyss.plugin.api.Vector3i;

public class WorldObject extends Vector3i {

    private int id;
    private ObjectType type;
    private int rotation;

    public WorldObject(int x, int y, int z) {
        super(x, y, z);
    }

    public WorldObject(int x, int y, int z, int id, ObjectType type) {
        super(x, y, z);
        this.id = id;
        this.type = type;
    }

    public WorldObject(int objectId, ObjectType type, int rotation, int x, int y, int objectPlane) {
        this(x, y, objectPlane, objectId, type);
    }

    public ObjectType getType() {
        return type;
    }

    public void setType(ObjectType type) {
        this.type = type;
    }

    public ObjectDefinition getDef() {
        return Cache.getFilesystem().getObjectDefinition(id);
    }

    public int getRotation() {
        return rotation;
    }

    public int getId() {
        return id;
    }

    public int getSlot() {
        return type.slot;
    }



    @Override
    public String toString() {
        return "WorldObject{" +
                "id=" + id +
                ", type=" + type +
                ", rotation=" + rotation +
                '}';
    }
}
