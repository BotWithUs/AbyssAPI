package abyss.plugin.api.plugin.nodes.leafs;

import abyss.plugin.api.actions.WidgetAction;
import abyss.plugin.api.game.actionbar.ActionBar;
import abyss.plugin.api.game.actionbar.ActionBarType;
import abyss.plugin.api.game.actionbar.ActionSlot;
import abyss.plugin.api.plugin.TreeContext;
import abyss.plugin.api.plugin.TreeNode;

public class ActionBarNode implements TreeNode {

    private final ActionBarType action;
    private final WidgetAction menuAction;
    private final int id;

    public ActionBarNode(final ActionBarType action, final WidgetAction menuAction, final int id) {
        this.action = action;
        this.menuAction = menuAction;
        this.id = id;
    }

    @Override
    public TreeNode execute(TreeContext ctx) {
        switch (action) {
            case TELEPORT -> {
                ActionSlot slot = ActionBar.forTeleport(this.id);
                if(slot != null) {
                    slot.interact(menuAction);
                }
            }
            case ITEM -> {
                ActionSlot slot = ActionBar.forItem(this.id);
                if(slot != null) {
                    slot.interact(menuAction);
                }
            }
            case ABILITY -> {
                ActionSlot slot = ActionBar.forAbility(this.id);
                if(slot != null) {
                    slot.interact(menuAction);
                }
            }
        }
        return LEAF;
    }
}
