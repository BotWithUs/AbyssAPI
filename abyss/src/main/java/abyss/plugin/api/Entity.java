package abyss.plugin.api;

import abyss.plugin.api.entities.Locatable;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static abyss.plugin.api.TextUtils.filterSpecialChars;

/**
 * An entity.
 */
public abstract class Entity implements Locatable {

    // internal values, attempting to use these will break the client

    private long internal1;
    private int internal2;
    private long internal3;

    private byte[] nameBinary;

    private String name;

    private Vector3 scenePosition;
    private Vector3i globalPosition;

    /**
     * Do not make instances of this.
     */
    protected Entity() {
        name = "";
        nameBinary = "N/A".getBytes();
    }

    /**
     * Retrieves the name of this entity.
     *
     * @return The name of this entity.
     */
    public String getName() {
        if(this.name.isEmpty()) {
            return new String(TextUtils.filterSpecialChars(nameBinary));
        }
        return this.name;
    }

    /**
     * Retrieves this entity's position within the 3d scene.
     *
     * @return This entity's position within the 3d scene.
     */
    public Vector3 getScenePosition() {
        return scenePosition;
    }

    /**
     * Retrieves this entity's global position within the world.
     *
     * @return This entity's global position within the world.
     */
    @Override
    public Vector3i getGlobalPosition() {
        return globalPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return internal1 == entity.internal1 &&
                internal2 == entity.internal2 &&
                internal3 == entity.internal3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(internal1, internal2, internal3);
    }

}
