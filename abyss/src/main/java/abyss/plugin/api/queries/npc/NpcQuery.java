package abyss.plugin.api.queries.npc;

import abyss.plugin.api.*;
import abyss.plugin.api.queries.results.EntityResultSet;
import abyss.plugin.api.queries.PathingEntityQuery;
import abyss.plugin.api.world.WorldTile;

import java.util.List;
import java.util.regex.Pattern;

public final class NpcQuery implements PathingEntityQuery<NpcQuery> {
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

    public NpcQuery() {
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
    }


    @Override
    public NpcQuery id(int... ids) {
        this.ids = ids;
        return this;
    }

    @Override
    public NpcQuery names(String... names) {
        this.names = names;
        return this;
    }

    @Override
    public NpcQuery names(Pattern pattern) {
        this.namePattern = pattern;
        return this;
    }

    @Override
    public NpcQuery within(Area3di area) {
        this.area = area;
        return this;
    }

    @Override
    public NpcQuery tile(WorldTile tile) {
        this.tile = tile;
        return this;
    }

    @Override
    public NpcQuery health(int value, int deviation) {
        this.health = value;
        this.healthDeviation = deviation;
        return this;
    }

    @Override
    public NpcQuery serverIndex(int index) {
        this.serverIndex = index;
        return this;
    }

    @Override
    public NpcQuery options(String... options) {
        this.options = options;
        return this;
    }

    @Override
    public NpcQuery interactIndex(int index) {
        this.interactIndex = index;
        return this;
    }

    @Deprecated
    @Override
    public NpcQuery activeHeadBar(float mineValue, float maxValue) {
        return this;
    }

    @Deprecated
    @Override
    public NpcQuery activeHeadBar(int barId) {
        return this;
    }

    public EntityResultSet<Npc> result() {
        return new EntityResultSet<>(results());
    }

    private native List<Npc> results();
}