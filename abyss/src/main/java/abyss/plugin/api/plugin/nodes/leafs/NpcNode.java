package abyss.plugin.api.plugin.nodes.leafs;

import abyss.plugin.api.Npc;
import abyss.plugin.api.Npcs;
import abyss.plugin.api.actions.NpcAction;
import abyss.plugin.api.plugin.nodes.PathingEntityNode;

public class NpcNode extends PathingEntityNode<NpcAction, Npc> {

    public NpcNode(Npc entity, NpcAction action) {
        super(entity, action);
    }

    @Override
    public boolean exists() {
        return Npcs.byServerIndex(getEntity().getServerIndex()) != null;
    }
}
