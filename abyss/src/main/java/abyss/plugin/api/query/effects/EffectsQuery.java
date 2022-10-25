package abyss.plugin.api.query.effects;

import abyss.plugin.api.Area3di;
import abyss.plugin.api.Effect;
import abyss.plugin.api.Vector3;
import abyss.plugin.api.query.IdentityQuery;
import abyss.plugin.api.world.WorldTile;

import java.util.List;

public final class EffectsQuery implements IdentityQuery<EffectsQuery> {

    private int[] ids;
    private Area3di area;
    private WorldTile tile;
    private Vector3 nearest;

    public EffectsQuery() {
        this.ids = null;
        this.area = null;
        this.tile = null;
        this.nearest = null;
    }

    @Override
    public EffectsQuery id(int... ids) {
        this.ids = ids;
        return this;
    }

    @Override
    public EffectsQuery within(Area3di area) {
        this.area = area;
        return this;
    }

    @Override
    public EffectsQuery tile(WorldTile tile) {
        this.tile = tile;
        return this;
    }

    @Override
    public EffectsQuery nearest(Vector3 scenePos) {
        this.nearest = scenePos;
        return this;
    }

    public native List<Effect> results();
}