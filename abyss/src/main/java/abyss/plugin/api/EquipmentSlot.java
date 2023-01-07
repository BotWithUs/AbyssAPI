package abyss.plugin.api;

/**
 * Each equipment slot for players.
 */
public enum EquipmentSlot {

    HELMET(1),
    CAPE(3),
    AMULET(5),
    BODY(9),
    LEGS(15),
    GLOVES(19),
    BOOTS(21),
    WEAPON(7),
    SHIELD(11),
    RING(-1),
    AURA(-1),
    WEAPON_SHEATHED(31),
    SHIELD_SHEATHED(33);

    private final int renderSlot;

    EquipmentSlot(int renderSlot) {
        this.renderSlot = renderSlot;
    }

    public int getRenderSlot() {
        return renderSlot;
    }

    public static EquipmentSlot forSlot(int slot) {
        for(EquipmentSlot equipSlot : values()) {
            if(equipSlot.getRenderSlot() == slot) {
                return equipSlot;
            }
        }
        return null;
    }

}
