package abyss.plugin.api.query;

import abyss.plugin.api.query.chat.ChatQuery;
import abyss.plugin.api.query.spot_animations.SpotAnimationQuery;
import abyss.plugin.api.query.items.GroundItemQuery;
import abyss.plugin.api.query.npc.NpcQuery;
import abyss.plugin.api.query.objects.ObjectQuery;
import abyss.plugin.api.query.players.PlayerQuery;
import abyss.plugin.api.query.projectiles.ProjectileQuery;
import abyss.plugin.api.query.components.ComponentQuery;

public final class Queries {
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

    public static ComponentQuery newComponentQuery(int groupId, int rootId) {
        return new ComponentQuery(groupId, rootId);
    }

    public static ComponentQuery newComponentQuery(int groupId) {
        return new ComponentQuery(groupId);
    }
}