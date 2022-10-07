package abyss.plugin.api.actions

enum class WidgetAction(override val type: Int) : ActionType {

    WIDGET(14),
    DIALOGUE(16);

    override val actionIndex: Int
        get() = ordinal
    override val typeName: String
        get() = name
}