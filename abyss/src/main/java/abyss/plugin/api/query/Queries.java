package abyss.plugin.api.query;

import abyss.plugin.api.query.npc.NpcQuery;

public class Queries {
    private Queries() {}

    public static NpcQuery newQuery() {
        return new NpcQuery();
    }
}