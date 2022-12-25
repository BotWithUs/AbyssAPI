package abyss.plugin.api.query.npc;

import abyss.plugin.api.*;
import abyss.plugin.api.query.results.EntityResultSet;
import abyss.plugin.api.query.PathingEntityQuery;
import abyss.plugin.api.query.results.PathingEntityResultSet;
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

    private float headbarMin;
    private float headbarMax;
    private int headbarId;

    private int hitmarkId;
    private int hitmarkMinValue;
    private int hitmarkMaxValue;

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
        this.headbarMin = 0.0f;
        this.headbarMax = 0.0f;
        this.headbarId = -1;
        this.hitmarkMinValue = -1;
        this.hitmarkMaxValue = -1;
        this.hitmarkId = -1;
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

    @Override
    public NpcQuery activeHeadBar(int headbarId, float mineValue, float maxValue) {
        this.headbarId = headbarId;
        this.headbarMin = mineValue;
        this.headbarMax = maxValue;
        return this;
    }

    @Override
    public NpcQuery hitmark(int hitmarkId, int min, int max) {
        this.hitmarkId = hitmarkId;
        this.hitmarkMinValue = min;
        this.hitmarkMaxValue = max;
        return this;
    }

    public PathingEntityResultSet<Npc> result() {
        return new PathingEntityResultSet<>(results());
    }

    private native List<Npc> results();
}