package abyss.plugin.api.plugin.nodes.leafs;

import abyss.plugin.api.SceneObject;
import abyss.plugin.api.actions.ObjectAction;
import abyss.plugin.api.plugin.TreeContext;
import abyss.plugin.api.plugin.TreeNode;
import abyss.plugin.api.plugin.nodes.EntityNode;

public class SceneObjectNode extends EntityNode<SceneObject> {

    private final ObjectAction action;

    public SceneObjectNode(SceneObject entity, ObjectAction action) {
        super(entity);
        this.action = action;
    }

    @Override
    public TreeNode execute(TreeContext ctx) {
        if(getEntity().isReachable()) {
            getEntity().interact(action.getType());
        }
        return LEAF;
    }
}
