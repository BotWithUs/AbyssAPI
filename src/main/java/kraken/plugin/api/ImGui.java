package kraken.plugin.api;

/**
 * Provides bindings to imgui.
 */
public final class ImGui {

    private ImGui() {
    }

    /**
     * Puts the next element onto the same line as the previous one.
     */
    public static native void sameLine();

    /**
     * Puts the next element onto a new line.
     */
    public static native void newLine();

    /**
     * Begins a tooltip
     */
    public static native void beginTooltip();

    /**
     * Ends a tooltip
     */

    public static native void endTooltip();

    /**
     * Checks if a ImGui node is being hovered
     */

    public static native boolean isItemHovered();

    /**
     * Begins a child group.
     */
    public static native boolean beginChild(String id, int width, int height, boolean hasBorder);

    /**
     * Ends a child group.
     */
    public static native void endChild();

    /**
     * Draws a label in the current UI context. This label will be positioned automatically.
     */
    public static native void label(String label);

    /**
     * Draws a combo box in the current UI context. This combo box will be positioned automatically.
     */
    public static native int combo(String label, String[] options, int selected);

    /**
     * Draws a combo box in the current UI context. This combo box will be positioned automatically.
     * <p>
     * Array sizes must be the same.
     */
    public static native void combo(String label, String[] options, boolean[] selected);

    /**
     * Draws a checkbox in the current UI context. This checkbox will be positioned automatically.
     */
    public static native boolean checkbox(String text, boolean initial);

    /**
     * Draws a slider in the current UI context. This slider will be positioned automatically.
     */
    public static native int intSlider(String text, int v, int min, int max);

    /**
     * Draws a button in the current UI context. THis button will be positioned automatically.
     */
    public static native boolean button(String text);

    /**
     * Draws a text input box in the current UI context. This input box will be positioned automatically.
     */
    public static native void input(String text, byte[] input);

    /**
     * Draws a password input box in the current UI context. This input box will be positioned automatically.
     */
    public static native void inputPassword(String text, byte[] input);

    /**
     * Draws an integer input box in the current UI context. This input box will be positioned automatically.
     */
    public static native int intInput(String text, int v);

    /**
     * Draws some text onto the screen.
     */
    public static native void freeText(String text, Vector2i pos, int color);

    /**
     * Draws a line onto the screen.
     */
    public static native void freeLine(Vector2i a, Vector2i b, int color);

    /**
     * Draws a 4-point polygon onto the screen.
     */
    public static native void freePoly4(Vector2i a, Vector2i b, Vector2i c, Vector2i d, int color);

    /**
     * Begins rendering of a tab bar.
     */
    public static native boolean beginTabBar(String name);

    /**
     * Ends rendering of a tab bar.
     */
    public static native void endTabBar();

    /**
     * Begins rendering of a tab bar.
     */
    public static native boolean beginTabItem(String name);

    /**
     * Ends rendering of a tab bar.
     */
    public static native void endTabItem();

    /**
     * Pushes a style color onto the color stack using ImVec4
     */

    public static native void pushStyleColor(int index, float a, float a1, float a2, float a3);

    /**
     * Pops x style colors off the color stack
     */

    public static native void popStyleColor(int count);

    /**
     * Creates a visual separator for two components
     */

    public static native void separator();

    /**
     * Pushes an ImGui style variable onto the variable stack.
     */
    public static native void pushStyleVar(int index, int x, int y);

    /**
     * Pops an ImGui style variable from the variable stack.
     */
    public static native void popStyleVar(int count);

    public static native boolean begin(String name, boolean isOpen, int windowsFlags);
    public static native void end();

    public static void pushStyleColor(ColorStyle col, float a, float a1, float a2, float a3) {
        pushStyleColor(col.getIndex(), a, a1, a2, a3);
    }

    public static void popStyleColor() {
        popStyleColor(1);
    }

    public static void beginChild(String id) {
        beginChild(id, 0, 0, false);
    }

    public static void beginChild(String id, boolean hasBorder) {
        beginChild(id, 0, 0, hasBorder);
    }

    public static void pushStyleVar(StyleVar var, int x, int y) {
        pushStyleVar(var.getIndex(), x, y);
    }

    public static void popStyleVar() {
        popStyleVar(1);
    }

    public static void begin(String name, boolean isOpen) {
        begin(name, isOpen, ImGuiWindowFlags_None);
    }

    public enum ColorStyle {
        ImGuiCol_Text,
        ImGuiCol_TextDisabled,
        ImGuiCol_WindowBg,              // Background of normal windows
        ImGuiCol_ChildBg,               // Background of child windows
        ImGuiCol_PopupBg,               // Background of popups, menus, tooltips windows
        ImGuiCol_Border,
        ImGuiCol_BorderShadow,
        ImGuiCol_FrameBg,               // Background of checkbox, radio button, plot, slider, text input
        ImGuiCol_FrameBgHovered,
        ImGuiCol_FrameBgActive,
        ImGuiCol_TitleBg,
        ImGuiCol_TitleBgActive,
        ImGuiCol_TitleBgCollapsed,
        ImGuiCol_MenuBarBg,
        ImGuiCol_ScrollbarBg,
        ImGuiCol_ScrollbarGrab,
        ImGuiCol_ScrollbarGrabHovered,
        ImGuiCol_ScrollbarGrabActive,
        ImGuiCol_CheckMark,
        ImGuiCol_SliderGrab,
        ImGuiCol_SliderGrabActive,
        ImGuiCol_Button,
        ImGuiCol_ButtonHovered,
        ImGuiCol_ButtonActive,
        ImGuiCol_Header,                // Header* colors are used for CollapsingHeader, TreeNode, Selectable, MenuItem
        ImGuiCol_HeaderHovered,
        ImGuiCol_HeaderActive,
        ImGuiCol_Separator,
        ImGuiCol_SeparatorHovered,
        ImGuiCol_SeparatorActive,
        ImGuiCol_ResizeGrip,
        ImGuiCol_ResizeGripHovered,
        ImGuiCol_ResizeGripActive,
        ImGuiCol_Tab,
        ImGuiCol_TabHovered,
        ImGuiCol_TabActive,
        ImGuiCol_TabUnfocused,
        ImGuiCol_TabUnfocusedActive,
        ImGuiCol_PlotLines,
        ImGuiCol_PlotLinesHovered,
        ImGuiCol_PlotHistogram,
        ImGuiCol_PlotHistogramHovered,
        ImGuiCol_TableHeaderBg,         // Table header background
        ImGuiCol_TableBorderStrong,     // Table outer and header borders (prefer using Alpha=1.0 here)
        ImGuiCol_TableBorderLight,      // Table inner borders (prefer using Alpha=1.0 here)
        ImGuiCol_TableRowBg,            // Table row background (even rows)
        ImGuiCol_TableRowBgAlt,         // Table row background (odd rows)
        ImGuiCol_TextSelectedBg,
        ImGuiCol_DragDropTarget,
        ImGuiCol_NavHighlight,          // Gamepad/keyboard: current highlighted item
        ImGuiCol_NavWindowingHighlight, // Highlight window when using CTRL+TAB
        ImGuiCol_NavWindowingDimBg,     // Darken/colorize entire screen behind the CTRL+TAB window list, when active
        ImGuiCol_ModalWindowDimBg,      // Darken/colorize entire screen behind a modal window, when one is active
        ImGuiCol_COUNT;

        public int getIndex() {
            return ordinal();
        }
    }

    public enum StyleVar {
        // Enum name --------------------- // Member in ImGuiStyle structure (see ImGuiStyle for descriptions)
        ImGuiStyleVar_Alpha,               // float     Alpha
        ImGuiStyleVar_WindowPadding,       // ImVec2    WindowPadding
        ImGuiStyleVar_WindowRounding,      // float     WindowRounding
        ImGuiStyleVar_WindowBorderSize,    // float     WindowBorderSize
        ImGuiStyleVar_WindowMinSize,       // ImVec2    WindowMinSize
        ImGuiStyleVar_WindowTitleAlign,    // ImVec2    WindowTitleAlign
        ImGuiStyleVar_ChildRounding,       // float     ChildRounding
        ImGuiStyleVar_ChildBorderSize,     // float     ChildBorderSize
        ImGuiStyleVar_PopupRounding,       // float     PopupRounding
        ImGuiStyleVar_PopupBorderSize,     // float     PopupBorderSize
        ImGuiStyleVar_FramePadding,        // ImVec2    FramePadding
        ImGuiStyleVar_FrameRounding,       // float     FrameRounding
        ImGuiStyleVar_FrameBorderSize,     // float     FrameBorderSize
        ImGuiStyleVar_ItemSpacing,         // ImVec2    ItemSpacing
        ImGuiStyleVar_ItemInnerSpacing,    // ImVec2    ItemInnerSpacing
        ImGuiStyleVar_IndentSpacing,       // float     IndentSpacing
        ImGuiStyleVar_CellPadding,         // ImVec2    CellPadding
        ImGuiStyleVar_ScrollbarSize,       // float     ScrollbarSize
        ImGuiStyleVar_ScrollbarRounding,   // float     ScrollbarRounding
        ImGuiStyleVar_GrabMinSize,         // float     GrabMinSize
        ImGuiStyleVar_GrabRounding,        // float     GrabRounding
        ImGuiStyleVar_TabRounding,         // float     TabRounding
        ImGuiStyleVar_ButtonTextAlign,     // ImVec2    ButtonTextAlign
        ImGuiStyleVar_SelectableTextAlign, // ImVec2    SelectableTextAlign
        ImGuiStyleVar_COUNT;

        public int getIndex() {
            return ordinal();
        }
    }

    public static final int ImGuiWindowFlags_None = 0;
    public static final int ImGuiWindowFlags_NoTitleBar = 1;   // Disable title-bar
    public static final int ImGuiWindowFlags_NoResize = 1 << 1;   // Disable user resizing with the lower-right grip
    public static final int ImGuiWindowFlags_NoMove = 1 << 2;   // Disable user moving the window
    public static final int ImGuiWindowFlags_NoScrollbar = 1 << 3;   // Disable scrollbars (window can still scroll with mouse or programmatically)
    public static final int ImGuiWindowFlags_NoScrollWithMouse = 1 << 4;   // Disable user vertically scrolling with mouse wheel. On child window, mouse wheel will be forwarded to the parent unless NoScrollbar is also set.
    public static final int ImGuiWindowFlags_NoCollapse = 1 << 5;   // Disable user collapsing window by double-clicking on it
    public static final int ImGuiWindowFlags_AlwaysAutoResize = 1 << 6;   // Resize every window to its content every frame
    public static final int ImGuiWindowFlags_NoBackground = 1 << 7;   // Disable drawing background color (WindowBg, etc.) and outside border. Similar as using SetNextWindowBgAlpha(0.0f).
    public static final int ImGuiWindowFlags_NoSavedSettings = 1 << 8;   // Never load/save settings in .ini file
    public static final int ImGuiWindowFlags_NoMouseInputs = 1 << 9;   // Disable catching mouse, hovering test with pass through.
    public static final int ImGuiWindowFlags_MenuBar = 1 << 10;  // Has a menu-bar
    public static final int ImGuiWindowFlags_HorizontalScrollbar = 1 << 11;  // Allow horizontal scrollbar to appear (off by default). You may use SetNextWindowContentSize(ImVec2(width,0.0f)); prior to calling Begin() to specify width. Read code in imgui_demo in the "Horizontal Scrolling" section.
    public static final int ImGuiWindowFlags_NoFocusOnAppearing = 1 << 12;  // Disable taking focus when transitioning from hidden to visible state
    public static final int ImGuiWindowFlags_NoBringToFrontOnFocus = 1 << 13;  // Disable bringing window to front when taking focus (e.g. clicking on it or programmatically giving it focus)
    public static final int ImGuiWindowFlags_AlwaysVerticalScrollbar = 1 << 14;  // Always show vertical scrollbar (even if ContentSize.y < Size.y)
    public static final int ImGuiWindowFlags_AlwaysHorizontalScrollbar = 1 << 15;  // Always show horizontal scrollbar (even if ContentSize.x < Size.x)
    public static final int ImGuiWindowFlags_AlwaysUseWindowPadding = 1 << 16;  // Ensure child windows without border uses style.WindowPadding (ignored by default for non-bordered child windows, because more convenient)
    public static final int ImGuiWindowFlags_NoNavInputs = 1 << 18;  // No gamepad/keyboard navigation within the window
    public static final int ImGuiWindowFlags_NoNavFocus = 1 << 19;  // No focusing toward this window with gamepad/keyboard navigation (e.g. skipped by CTRL+TAB)
    public static final int ImGuiWindowFlags_UnsavedDocument = 1 << 20;  // Display a dot next to the title. When used in a tab/docking context, tab is selected when clicking the X + closure is not assumed (will wait for user to stop submitting the tab). Otherwise closure is assumed when pressing the X, so if you keep submitting the tab may reappear at end of tab bar.
    public static final int ImGuiWindowFlags_NoNav = ImGuiWindowFlags_NoNavInputs | ImGuiWindowFlags_NoNavFocus;
    public static final int ImGuiWindowFlags_NoDecoration = ImGuiWindowFlags_NoTitleBar | ImGuiWindowFlags_NoResize | ImGuiWindowFlags_NoScrollbar | ImGuiWindowFlags_NoCollapse;
    public static final int ImGuiWindowFlags_NoInputs = ImGuiWindowFlags_NoMouseInputs | ImGuiWindowFlags_NoNavInputs | ImGuiWindowFlags_NoNavFocus;
    public static final int ImGuiWindowFlags_NavFlattened = 1 << 23;  // [BETA] Allow gamepad/keyboard navigation to cross over parent border to this child (only use on child that have no scrolling!)
    public static final int ImGuiWindowFlags_ChildWindow = 1 << 24;  // Don't use! For internal use by BeginChild()
    public static final int ImGuiWindowFlags_Tooltip = 1 << 25;  // Don't use! For internal use by BeginTooltip()
    public static final int ImGuiWindowFlags_Popup = 1 << 26;  // Don't use! For internal use by BeginPopup()
    public static final int ImGuiWindowFlags_Modal = 1 << 27;  // Don't use! For internal use by BeginPopupModal()
    public static final int ImGuiWindowFlags_ChildMenu = 1 << 28;  // Don't use! For internal use by BeginMenu()
}
