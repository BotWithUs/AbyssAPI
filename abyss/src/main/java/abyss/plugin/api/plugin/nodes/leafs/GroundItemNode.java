package abyss.plugin.api.plugin.nodes.leafs;

import abyss.Utils;
import abyss.plugin.api.Actions;
import abyss.plugin.api.GroundItem;
import abyss.plugin.api.actions.GroundItemAction;
import abyss.plugin.api.plugin.TreeContext;
import abyss.plugin.api.plugin.TreeNode;
import abyss.plugin.api.plugin.nodes.EntityNode;
import org.jetbrains.annotations.NotNull;

public class GroundItemNode extends EntityNode<GroundItem> {

    private GroundItemAction action;

    public GroundItemNode(@NotNull GroundItem entity, GroundItemAction action) {
        super(entity);
        this.action = action;
    }

    @Override
    public TreeNode execute(TreeContext ctx) {
        if(Utils.isGroundItemReachable(getEntity())) {
            Actions.entity(getEntity(), action.getType());
        }
        return LEAF;
    }
}
