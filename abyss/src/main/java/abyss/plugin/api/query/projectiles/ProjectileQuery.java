package abyss.plugin.api.query.projectiles;

import abyss.plugin.api.Area3di;
import abyss.plugin.api.Vector3;
import abyss.plugin.api.query.results.EntityResultSet;
import abyss.plugin.api.query.IdentityQuery;
import abyss.plugin.api.world.WorldTile;
import abyss.plugin.api.world.projectiles.Projectile;

import java.util.List;

public final class ProjectileQuery implements IdentityQuery<ProjectileQuery> {

    private int[] ids;
    private Area3di area;
    private WorldTile tile;
    private Vector3 nearest;

    public ProjectileQuery() {
        this.ids = null;
        this.area = null;
        this.tile = null;
        this.nearest = null;
    }

    @Override
    public ProjectileQuery id(int... ids) {
        this.ids = ids;
        return this;
    }

    @Override
    public ProjectileQuery within(Area3di area) {
        this.area = area;
        return this;
    }

    @Override
    public ProjectileQuery tile(WorldTile tile) {
        this.tile = tile;
        return this;
    }

    public EntityResultSet<Projectile> result() {
        return new EntityResultSet<>(results());
    }

    private native List<Projectile> results();
}