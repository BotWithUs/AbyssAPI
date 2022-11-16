package abyss.plugin.api.query;

import abyss.plugin.api.Area3di;
import abyss.plugin.api.world.WorldTile;

public interface IdentityQuery<T extends IdentityQuery<T>>  {
    T id(int... ids);

    T within(Area3di area);

    T tile(WorldTile tile);
}