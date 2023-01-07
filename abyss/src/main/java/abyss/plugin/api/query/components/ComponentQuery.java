package abyss.plugin.api.query.components;

import abyss.plugin.api.Component;
import abyss.plugin.api.query.results.ComponentResultSet;

import java.util.List;

public final class ComponentQuery {

    public static final int LAYER = 0;
    public static final int RECTANGLE=3;
    public static final int TEXT = 4;
    public static final int SPRITE = 5;
    public static final int MODEL = 6;

    private String[] text;
    private int[] materialIds;
    private int[] itemIds;
    private String[] itemNames;

    private String[] actions;

    private int fontId;
    private int color;
    private int alpha;

    private final int interfaceIndex;

    private int type;

    public ComponentQuery(int interfaceIndex) {
        this.text = null;
        this.materialIds = null;
        this.itemIds = null;
        this.itemNames = null;
        this.actions = null;
        this.interfaceIndex = interfaceIndex;
        this.fontId = -1;
        this.color = -1;
        this.alpha = -1;
    }

    public ComponentQuery actions(String... actions) {
        this.actions = actions;
        return this;
    }

    public ComponentQuery font(int fontId) {
        this.fontId = fontId;
        return this;
    }

    public ComponentQuery text(String... text) {
        this.text = text;
        return this;
    }

    public ComponentQuery text(int fontId, String... text) {
        this.fontId = fontId;
        this.text = text;
        return this;
    }

    public ComponentQuery text(int fonId, int color, int alpha, String... text) {
        this.fontId = fonId;
        this.color = color;
        this.alpha = alpha;
        this.text = text;
        return this;
    }

    public ComponentQuery materials(int... ids) {
        this.materialIds = ids;
        return this;
    }

    public ComponentQuery items(int... ids) {
        this.itemIds = ids;
        return this;
    }

    public ComponentQuery names(String... names) {
        this.itemNames = names;
        return this;
    }

    public ComponentResultSet result() {
        return new ComponentResultSet(results());
    }
    private native List<Component> results();
}