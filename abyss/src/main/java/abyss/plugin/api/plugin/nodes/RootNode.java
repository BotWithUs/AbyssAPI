package abyss.plugin.api.plugin.nodes;

import abyss.plugin.api.plugin.TreeContext;
import abyss.plugin.api.plugin.TreeNode;

public class RootNode implements TreeNode {
    @Override
    public TreeNode execute(TreeContext ctx) {
        return NodeConditions.COPPER_ORE;
    }
}
