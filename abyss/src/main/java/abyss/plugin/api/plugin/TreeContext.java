package abyss.plugin.api.plugin;

import abyss.plugin.api.Actions;
import abyss.plugin.api.Player;
import abyss.plugin.api.SceneObject;

public class TreeContext {

    private Player self;
    private long tickCount;

    public TreeContext(Player self, long tickCount) {
        this.self = self;
        this.tickCount = tickCount;
    }

    public Player getSelf() {
        return self;
    }

    public long getTickCount() {
        return tickCount;
    }
}
