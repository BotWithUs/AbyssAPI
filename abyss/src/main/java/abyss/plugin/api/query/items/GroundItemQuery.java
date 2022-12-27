package abyss.plugin.api.query.items;

import abyss.plugin.api.Area3di;
import abyss.plugin.api.GroundItem;
import abyss.plugin.api.query.EntityQuery;
import abyss.plugin.api.query.results.EntityResultSet;
import abyss.plugin.api.world.WorldTile;

import java.util.List;
import java.util.regex.Pattern;

public class GroundItemQuery implements EntityQuery<GroundItemQuery> {

    private int[] ids;
    private String[] names;

    private Pattern namePattern;
    private String[] options;
    private Area3di area;
    private WorldTile tile;

    public GroundItemQuery() {
        this.ids = null;
        this.names = null;
        this.options = null;
        this.area = null;
        this.tile = null;
        this.namePattern = null;    
    }

    @Override
    public GroundItemQuery names(String... names) {
        this.names = names;
        return this;
    }

    @Override
    public GroundItemQuery names(Pattern pattern) {
        this.namePattern = pattern;
        return this;
    }

    @Override
    public GroundItemQuery id(int... ids) {
        this.ids = ids;
        return this;
    }

    @Override
    public GroundItemQuery within(Area3di area) {
        this.area = area;
        return this;
    }

    @Override
    public GroundItemQuery tile(WorldTile tile) {
        this.tile = tile;
        return this;
    }

    public GroundItemQuery options(String... options) {
        this.options = options;
        return this;
    }

    public EntityResultSet<GroundItem> result() {
        return new EntityResultSet<>(results());
    }
    
    private native List<GroundItem> results();
}