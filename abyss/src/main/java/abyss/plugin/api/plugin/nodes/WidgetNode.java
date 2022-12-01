package abyss.plugin.api.plugin.nodes;

import abyss.plugin.api.Interfaces;
import abyss.plugin.api.plugin.TreeContext;
import abyss.plugin.api.plugin.TreeNode;

public abstract class WidgetNode implements TreeNode {

    private final int rootId;
    private final int childId;

    public WidgetNode(int rootId, int childId) {
        this.rootId = rootId;
        this.childId = childId;
    }

    public abstract TreeNode interact();

    @Override
    public final TreeNode execute(TreeContext ctx) {
        if(Interfaces.isOpen(rootId)) {
            return interact();
        }
        return LEAF;
    }

    int getInteractId() {
        return rootId << 16 | childId;
    }
}
