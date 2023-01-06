package abyss.plugin.api.query.projectiles;

import abyss.plugin.api.Area3di;
import abyss.plugin.api.entities.PathingEntity;
import abyss.plugin.api.entities.Player;
import abyss.plugin.api.entities.ProjectileAnimation;
import abyss.plugin.api.query.IdentityQuery;
import abyss.plugin.api.query.results.ProjectileResultSet;
import abyss.plugin.api.world.WorldTile;

import java.util.List;

public final class ProjectileQuery implements IdentityQuery<ProjectileQuery> {

    private int[] ids;
    private Area3di area;
    private WorldTile tile;

    private int sourceIndex;
    private int sourceType;
    private int targetIndex;
    private int targetType;

    public ProjectileQuery() {
        this.ids = null;
        this.area = null;
        this.tile = null;
        this.sourceIndex = -1;
        this.sourceType = -1;
        this.targetIndex = -1;
        this.targetType = -1;
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

    public ProjectileQuery withSource(PathingEntity source) {
        this.sourceIndex = source.getIdentifier();
        this.sourceType = source instanceof Player ? 2 : 1;
        return this;
    }

    public ProjectileQuery withTarget(PathingEntity target) {
        this.targetIndex = target.getIdentifier();
        this.targetType = target instanceof Player ? 2 : 1;
        return this;
    }

    public ProjectileResultSet result() {
        return new ProjectileResultSet(results());
    }

    private native List<ProjectileAnimation> results();
}