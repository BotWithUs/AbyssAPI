package abyss.plugin.api.query.players;

import abyss.plugin.api.Area3di;
import abyss.plugin.api.Player;
import abyss.plugin.api.Vector3;
import abyss.plugin.api.query.results.EntityResultSet;
import abyss.plugin.api.query.PathingEntityQuery;
import abyss.plugin.api.query.results.PathingEntityResultSet;
import abyss.plugin.api.world.WorldTile;

import java.util.List;
import java.util.regex.Pattern;

public final class PlayerQuery implements PathingEntityQuery<PlayerQuery> {

    private int[] ids;
    private String[] names;
    private String[] options;
    private Pattern namePattern;
    private Area3di area;
    private WorldTile tile;
    private int health;
    private int healthDeviation;
    private int serverIndex;
    private int interactIndex;
    private float statusBarMin;
    private float statusBarMax;
    private int statusBarId;

    private int hitmarkId;
    private int hitmarkMinValue;
    private int hitmarkMaxValue;

    public PlayerQuery() {
        this.ids = null;
        this.names = null;
        this.options = null;
        this.namePattern = null;
        this.area = null;
        this.tile = null;
        this.health = -1;
        this.healthDeviation = 0;
        this.serverIndex = -1;
        this.interactIndex = -1;
        this.statusBarMin = 0.0f;
        this.statusBarMax = 0.0f;
        this.statusBarId = -1;
        this.hitmarkMinValue = -1;
        this.hitmarkMaxValue = -1;
        this.hitmarkId = -1;
    }

    @Override
    public PlayerQuery id(int... ids) {
        return this;
    }

    @Override
    public PlayerQuery names(String... names) {
        this.names = names;
        return this;
    }

    @Override
    public PlayerQuery names(Pattern pattern) {
        this.namePattern = pattern;
        return this;
    }

    @Override
    public PlayerQuery within(Area3di area) {
        this.area = area;
        return this;
    }

    @Override
    public PlayerQuery tile(WorldTile tile) {
        this.tile = tile;
        return this;
    }

    @Override
    public PlayerQuery health(int value, int deviation) {
        return this;
    }

    @Override
    public PlayerQuery serverIndex(int index) {
        this.serverIndex = index;
        return this;
    }

    @Override
    public PlayerQuery options(String... options) {
        this.options = options;
        return this;
    }

    @Override
    public PlayerQuery interactIndex(int index) {
        this.interactIndex = index;
        return this;
    }

    @Override
    public PlayerQuery activeHeadBar(int headbarId, float mineValue, float maxValue) {
        this.statusBarId = headbarId;
        this.statusBarMin = mineValue;
        this.statusBarMax = maxValue;
        return this;
    }

    @Override
    public PlayerQuery hitmark(int hitmarkId, int min, int max) {
        this.hitmarkId = hitmarkId;
        this.hitmarkMinValue = min;
        this.hitmarkMaxValue = max;
        return this;
    }

    public PathingEntityResultSet<Player> result() {
        return new PathingEntityResultSet<>(results());
    }
    
    private native List<Player> results();

    public static native Player self();
}