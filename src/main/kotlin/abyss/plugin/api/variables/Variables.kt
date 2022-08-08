package abyss.plugin.api.variables

import kraken.plugin.api.ItemContainer
import kraken.plugin.api.ItemContainers
import kotlin.reflect.KProperty

enum class Variables(val variable: PlayerVariable) : PlayerVariable by variable {
    BANK_INVENTORY_TAB(VariableBit(45319)),
    BANK_TAB(VariableBit(45141)),
    PRESETS_OPEN(VariableBit(39433)),
    SELECTED_PRESET(VariableBit(22179)),
    PRESET_SUM_SELECTED(VariableBit(26177)),
    PRESET_INVENTORY_SELECTED(VariableBit(22158)),
    PRESET_EQUIPMENT_SELECTED(VariableBit(22159)),
    TRANSFER_X(VariableBit(2567)),
    TRANSFER_AMOUNT(VariableBit(45189)),
    PLACEHOLDERS_ENABLED(VariableBit(45190)),
    ACTION_BAR_NUMBER(VariableBit(1893)),
    ACTION_BAR_LOCKED(VariableBit(1892)),
    PRAYER_AMOUNT(VariableBit(16736)),
    ADRENALINE_AMOUNT(VariableBit(1902)),
    HEALTH_AMOUNT(VariableBit(1668)),
    MAX_HEALTH_AMOUNT(VariableBit(24595)),
    SUMMONING_POINTS(VariableBit(41524)),
    GRAND_EXCHANGE_SLOT(ConVarValue(138)),
    GRAND_EXCHANGE_ACTION(ConVarValue(139)),
    GRAND_EXCHANGE_TAB(VariableBit(19000)),
    GRAND_EXCHANGE_BUY_ITEM(ConVarValue(135)),
    GRAND_EXCHANGE_BUY_AMOUNT(ConVarValue(136)),
    GRAND_EXCHANGE_BUY_PRICE(ConVarValue(137)),
    CURSE_PROTECT_ITEM(VariableBit(16761)),
    CURSE_SAP_WARRIOR(VariableBit(16762)),
    CURSE_SAP_RANGE(VariableBit(16763)),
    CURSE_SAP_RANGE_STRENGTH(VariableBit(16786)),
    CURSE_SAP_MAGE(VariableBit(16764)),
    CURSE_SAP_MAGIC_STRENGTH(VariableBit(16785)),
    CURSE_SAP_ADRENALINE(VariableBit(16765)),
    CURSE_SAP_DEFENCE(VariableBit(16788)),
    CURSE_SAP_STRENGTH(VariableBit(16787)),
    CURSE_BERSERKER(VariableBit(16766)),
    CURSE_DEFLECT_SUMMONING(VariableBit(16767)),
    CURSE_DEFLECT_MAGIC(VariableBit(16768)),
    CURSE_DEFLECT_RANGE(VariableBit(16769)),
    CURSE_DEFLECT_MELEE(VariableBit(16770)),
    CURSE_WRATH(VariableBit(16778)),
    CURSE_SOUL_SPLIT(VariableBit(16779)),
    CURSE_TURMOIL(VariableBit(16780)),
    CURSE_ANGUISH(VariableBit(16783)),
    CURSE_TORMENT(VariableBit(16784)),
    PROTECT_ITEM(VariableBit(16744)),
    PROTECT_SUMMONING(VariableBit(16755)),
    PROTECT_MAGIC(VariableBit(16745)),
    PROTECT_RANGE(VariableBit(16746)),
    PROTECT_MELEE(VariableBit(16747)),
    RETRIBUTION(VariableBit(16748)),
    REDEMPTION(VariableBit(16749)),
    SMITE(VariableBit(16750)),
    RAPID_RENEWAL(VariableBit(16758)),
    QUICK_PRAYER_ACTIVE(VariableBit(5941)),
    IN_COMBAT(VariableBit(1899));

    inline operator fun <reified T> getValue(ref: Any?, prop: KProperty<*>): T = when(T::class)
    {
        ItemContainer::class -> ItemContainers.byId(value) as T
        Boolean::class -> (value == 1) as T
        else -> value as T
    }
}