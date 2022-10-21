package abyss.plugin.api.query;

import abyss.plugin.api.Area3di;
import abyss.plugin.api.world.WorldTile;

import java.util.regex.Pattern;

public interface EntityQuery<T extends EntityQuery<T>> {

    T id(int id);

    T name(String name);

    T name(Pattern pattern);

    T within(Area3di area);

    T tile(WorldTile tile);
}