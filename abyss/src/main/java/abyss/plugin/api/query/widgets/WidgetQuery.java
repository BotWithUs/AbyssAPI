package abyss.plugin.api.query.widgets;

import abyss.plugin.api.components.WidgetComponent;

import java.util.List;

public final class WidgetQuery {

    public static final int CONTAINER = 0;
    public static final int TEXT = 4;
    public static final int MEDIA = 5;

    private String[] text;
    private int[] textureIds;
    private int[] disabledTextureIds;
    private int[] itemIds;
    private String[] itemNames;

    private final int groupId;

    private final int rootId;

    private boolean isOpen;

    public WidgetQuery(int groupId, int rootId) {
        this.text = null;
        this.textureIds = null;
        this.disabledTextureIds = null;
        this.itemIds = null;
        this.itemNames = null;
        this.groupId = groupId;
        this.rootId = rootId;
        this.isOpen = false;
    }

    public WidgetQuery(int groupId) {
        this(groupId, 0);
    }

    public WidgetQuery text(String... text) {
        this.text = text;
        return this;
    }

    public WidgetQuery textures(int... ids) {
        this.textureIds = ids;
        return this;
    }

    public WidgetQuery disabledTextures(int... ids) {
        this.disabledTextureIds = ids;
        return this;
    }

    public WidgetQuery items(int... ids) {
        this.itemIds = ids;
        return this;
    }

    public WidgetQuery names(String... names) {
        this.itemNames = names;
        return this;
    }

    public WidgetQuery isOpen() {
        this.isOpen = true;
        return this;
    }

    public WidgetResultSet result() {
        return new WidgetResultSet(results());
    }

    private native List<WidgetComponent> results();

}