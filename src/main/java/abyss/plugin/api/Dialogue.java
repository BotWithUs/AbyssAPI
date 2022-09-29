package abyss.plugin.api;

/**
 * Provides utilities for accessing dialogue widgets.
 */
public final class Dialogue {

    private static final int WIDGET_ID = 1188;
    private static final int WIDGET_ID_NPC_TALKING = 1184;
    private static final int WIDGET_ID_PLAYER_TALKING = 1191;

    private static final DialogueOption[] DIALOGUE_OPTIONS = {
            new DialogueOption(77856776, 0),
            new DialogueOption(77856781, 0),
            new DialogueOption(77856786, 0),
            new DialogueOption(77856791, 0)
    };

    private Dialogue() {
    }

    /**
     * Determines if the dialogue widget is open.
     *
     * @return If the dialogue widget is open.
     */
    public static boolean isOpen() {
        return Widgets.isOpen(WIDGET_ID) || Widgets.isOpen(WIDGET_ID_NPC_TALKING) || Widgets.isOpen(WIDGET_ID_PLAYER_TALKING);
    }

    /**
     * Retrieves all dialogue options.
     *
     * @return All dialogue options.
     */
    public static String[] getOptions() {
        Widget optionList = Widgets.getChild(WIDGET_ID, 0, 1, 0);
        if (optionList == null || optionList.getType() != Widget.CONTAINER) {
            return new String[0];
        }

        String[] options = new String[5];

        int index = 0;
        for (Widget optWidget : optionList.getChildren()) {
            if (optWidget.getType() != Widget.CONTAINER) {
                continue;
            }

            Widget textWrapper = optWidget.getChild(3);
            if (textWrapper.getType() != Widget.CONTAINER) {
                continue;
            }

            Widget optionName = textWrapper.getChild(0);
            if (optionName.getType() != Widget.TEXT || !optionName.isVisible()) {
                continue;
            }

            options[index++] = optionName.getText();
        }

        return options;
    }

    /**
     * Interacts with a dialogue option.
     */
    public static void interact(String name) {
        String[] options = getOptions();
        for (int i = 0; i < Math.min(DIALOGUE_OPTIONS.length, options.length); i++) {
            String s = options[i];
            if (name.equalsIgnoreCase(s)) {
                DialogueOption opt = DIALOGUE_OPTIONS[i];
                Actions.menu(Actions.MENU_EXECUTE_DIALOGUE, 0, -1, opt.dialogueId, opt.dialogueId2);
            }
        }
    }

    /**
     * Clicks to the next dialogue.
     */
    public static void next() {
        if (Widgets.isOpen(WIDGET_ID_NPC_TALKING)) {
            Actions.menu(Actions.MENU_EXECUTE_DIALOGUE, 0, -1, 77594639, 0);
        } else if (Widgets.isOpen(WIDGET_ID_PLAYER_TALKING)) {
            Actions.menu(Actions.MENU_EXECUTE_DIALOGUE, 0, -1, 78053391, 0);
        }
    }

    private static class DialogueOption {
        private final int dialogueId;
        private final int dialogueId2;

        public DialogueOption(int dialogueId, int dialogueId2) {
            this.dialogueId = dialogueId;
            this.dialogueId2 = dialogueId2;
        }
    }
}
