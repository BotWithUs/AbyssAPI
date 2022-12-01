package abyss.plugin.api.queries;

import abyss.plugin.api.queries.chat.ChatQuery;
import abyss.plugin.api.queries.spot_animations.SpotAnimationQuery;
import abyss.plugin.api.queries.items.GroundItemQuery;
import abyss.plugin.api.queries.npc.NpcQuery;
import abyss.plugin.api.queries.objects.ObjectQuery;
import abyss.plugin.api.queries.players.PlayerQuery;
import abyss.plugin.api.queries.projectiles.ProjectileQuery;
import abyss.plugin.api.queries.components.ComponentQuery;

public class Queries {
    private Queries() {
    }

    public static NpcQuery newNpcQuery() {
        return new NpcQuery();
    }
    public static ObjectQuery newObjectQuery() {
        return new ObjectQuery();
    }

    public static PlayerQuery newPlayerQuery() {
        return new PlayerQuery();
    }

    public static SpotAnimationQuery newSpotAnimationQuery() {
        return new SpotAnimationQuery();
    }

    public static ProjectileQuery newProjectileQuery() {
        return new ProjectileQuery();
    }

    public static GroundItemQuery newGroundItemQuery() {
        return new GroundItemQuery();
    }

    public static ChatQuery newChatQuery() {
        return new ChatQuery();
    }

    public static ComponentQuery newWidgetQuery(int groupId, int rootId) {
        return new ComponentQuery(groupId, rootId);
    }

    public static ComponentQuery newWidgetQuery(int groupId) {
        return new ComponentQuery(groupId);
    }
}