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
    private int[] textureIds;
    private int[] disabledTextureIds;
    private int[] itemIds;
    private String[] itemNames;

    private final int groupId;

    private final int rootId;

    private boolean isOpen;

    public ComponentQuery(int groupId, int rootId) {
        this.text = null;
        this.textureIds = null;
        this.disabledTextureIds = null;
        this.itemIds = null;
        this.itemNames = null;
        this.groupId = groupId;
        this.rootId = rootId;
        this.isOpen = false;
    }

    public ComponentQuery(int groupId) {
        this(groupId, 0);
    }

    public ComponentQuery text(String... text) {
        this.text = text;
        return this;
    }

    public ComponentQuery textures(int... ids) {
        this.textureIds = ids;
        return this;
    }

    public ComponentQuery disabledTextures(int... ids) {
        this.disabledTextureIds = ids;
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

    public ComponentQuery isOpen() {
        this.isOpen = true;
        return this;
    }
    public ComponentResultSet result() {
        return new ComponentResultSet(results());
    }
    private native List<Component> results();
}