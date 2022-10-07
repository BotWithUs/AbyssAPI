package abyss.plugin.api.plugin;

@FunctionalInterface
public interface TreeNode {

    TreeNode execute(TreeContext ctx);

    static void traverse(TreeNode node, TreeContext ctx) {
        TreeNode n = node.execute(ctx);
        if(n != LEAF) { // Not Leaf
            traverse(n, ctx);
        }
    }

    TreeNode LEAF = ctx -> null;
}
