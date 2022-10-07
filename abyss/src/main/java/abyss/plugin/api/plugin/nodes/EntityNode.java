package abyss.plugin.api.plugin.nodes;

import abyss.plugin.api.Entity;
import abyss.plugin.api.Vector3i;
import abyss.plugin.api.plugin.TreeNode;
import abyss.plugin.api.world.WorldTile;
import org.jetbrains.annotations.NotNull;

public abstract class EntityNode<T extends Entity> implements TreeNode {
    private final T entity;

    public EntityNode(@NotNull T entity) {
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }

    public final String getName() {
        return entity.getName();
    }

    public final WorldTile getTile() {
        Vector3i tile = entity.getGlobalPosition();
        return new WorldTile(tile.getX(), tile.getY(), tile.getZ());
    }
}
