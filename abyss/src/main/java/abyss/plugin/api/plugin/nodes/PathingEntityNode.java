package abyss.plugin.api.plugin.nodes;

import abyss.plugin.api.Actions;
import abyss.plugin.api.entities.PathingEntity;
import abyss.plugin.api.actions.ActionType;
import abyss.plugin.api.plugin.TreeContext;
import abyss.plugin.api.plugin.TreeNode;

public abstract class PathingEntityNode<A extends ActionType, T extends PathingEntity> extends EntityNode<T> {

    private final A action;

    public PathingEntityNode(T entity, A action) {
        super(entity);
        this.action = action;
    }

    @Override
    public TreeNode execute(TreeContext ctx) {
        if (exists() && getEntity().isReachable()) {
            Actions.entity(this.getEntity(), this.action.getType());
        }
        return LEAF;
    }

    public abstract boolean exists();

    public A getAction() {
        return action;
    }
}
