package abyss.plugin.api.query.npc;

import abyss.plugin.api.Area3di;
import abyss.plugin.api.Npc;
import abyss.plugin.api.query.SpiritQuery;
import abyss.plugin.api.world.WorldTile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class NpcQuery implements SpiritQuery<NpcQuery> {

    private int id;
    private String name;
    private Pattern namePattern;
    private Area3di area;
    private WorldTile tile;
    private int health;
    private int serverIndex;
    private int interactIndex;
    private float activeStatusBarsMin;
    private float activeStatusBarsMax;
    private float activeStatusBarsId;

    public NpcQuery() {
        this.id = -1;
        this.name = null;
        this.namePattern = null;
        this.area = null;
        this.tile = null;
        this.health = -1;
        this.serverIndex = -1;
        this.interactIndex = -1;
        this.activeStatusBarsMin = 0.0f;
        this.activeStatusBarsMax = 0.0f;
        this.activeStatusBarsId = -1;
    }

    @Override
    public NpcQuery id(int id) {
        this.id = id;
        return this;
    }

    @Override
    public NpcQuery name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public NpcQuery name(Pattern pattern) {
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
    public NpcQuery health(int value) {
        this.health = value;
        return this;
    }

    @Override
    public NpcQuery serverIndex(int index) {
        this.serverIndex = index;
        return this;
    }

    @Override
    public NpcQuery interactIndex(int index) {
        this.interactIndex = index;
        return this;
    }

    @Override
    public NpcQuery activeStatusBar(float mineValue, float maxValue) {
        this.activeStatusBarsMin = mineValue;
        this.activeStatusBarsMax = maxValue;
        return this;
    }

    @Override
    public NpcQuery activeStatusBar(int barId) {
        this.activeStatusBarsId = barId;
        List<Object> l = new ArrayList<>();
        return this;
    }

    public native List<Npc> results();
}