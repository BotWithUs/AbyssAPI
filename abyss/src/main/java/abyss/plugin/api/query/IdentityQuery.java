package abyss.plugin.api.query;

import abyss.plugin.api.Area3di;
import abyss.plugin.api.Vector3;
import abyss.plugin.api.world.WorldTile;
import abyss.plugin.api.Entity;

public interface IdentityQuery<T extends IdentityQuery<T>>  {

    T id(int... ids);

    T within(Area3di area);

    T tile(WorldTile tile);

    T nearest(Vector3 scenePos);

    default T nearest(Entity entity) {
        return nearest(entity.getScenePosition());
    }

    default T nearest() {
        return nearest(Vector3.ZERO);
    }

}