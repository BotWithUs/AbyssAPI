package abyss.plugin.api.query.players;

import abyss.plugin.api.Area3di;
import abyss.plugin.api.Player;
import abyss.plugin.api.Vector3;
import abyss.plugin.api.Vector3i;
import abyss.plugin.api.query.EntityResultSet;
import abyss.plugin.api.query.SpiritQuery;
import abyss.plugin.api.world.WorldTile;

import java.util.List;
import java.util.regex.Pattern;

public final class PlayerQuery implements SpiritQuery<PlayerQuery> {

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
    private Vector3 nearest;

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
        this.statusBarMin = -1;
        this.statusBarMax = -1;
        this.statusBarId = -1;
        this.nearest = null;
    }

    @Override
    public PlayerQuery id(int... ids) {
        this.ids = ids;
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
        this.health = value;
        this.healthDeviation = deviation;
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
    public PlayerQuery activeStatusBar(float mineValue, float maxValue) {
        this.statusBarMin = mineValue;
        this.statusBarMax = maxValue;
        return this;
    }

    @Override
    public PlayerQuery activeStatusBar(int barId) {
        this.statusBarId = barId;
        return this;
    }

    public EntityResultSet<Player> result() {
        return new EntityResultSet<>(results());
    }
    
    private native List<Player> results();

    public static native Player self();
}