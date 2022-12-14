package abyss.plugin.api.query.spot_animations;

import abyss.plugin.api.Area3di;
import abyss.plugin.api.SpotAnimation;
import abyss.plugin.api.query.results.EntityResultSet;
import abyss.plugin.api.query.IdentityQuery;
import abyss.plugin.api.world.WorldTile;

import java.util.List;

public final class SpotAnimationQuery implements IdentityQuery<SpotAnimationQuery> {

    private int[] ids;
    private Area3di area;
    private WorldTile tile;

    public SpotAnimationQuery() {
        this.ids = null;
        this.area = null;
        this.tile = null;
    }

    @Override
    public SpotAnimationQuery id(int... ids) {
        this.ids = ids;
        return this;
    }

    @Override
    public SpotAnimationQuery within(Area3di area) {
        this.area = area;
        return this;
    }

    @Override
    public SpotAnimationQuery tile(WorldTile tile) {
        this.tile = tile;
        return this;
    }

    public EntityResultSet<SpotAnimation> result() {
        return new EntityResultSet<>(results());
    }

    private native List<SpotAnimation> results();
}