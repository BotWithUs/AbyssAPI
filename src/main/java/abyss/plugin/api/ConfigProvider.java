package abyss.plugin.api;

/**
 * Provides access to RuneScape Config Variables
 */

public final class ConfigProvider {
    private ConfigProvider() {}

    public static final int ID_LOCAL_STATUS = 659;
    public static final int ID_COMBAT_MODE = 3711;
    public static final int ID_INTERFACE_MODE = 3814;
    public static final int ID_AUTO_RETALIATE = 462;
    public static final int ID_WEAPON_SHEATHE = 689;
    public static final int ID_WARNING_CLAN_WARS_SAFE = 446;
    public static final int ID_WARNING_CLAN_WARS_DANGEROUS = 447;
    public static final int ID_WITHDRAW_NOTE = 160;
    public static final int ID_RUNNING = 463;
    public static final int ID_PRIVACY = 4983;

    public static final int CFG_INTERFACE_MODE_NEW = 0;
    public static final int CFG_INTERFACE_MODE_LEGACY = 58;

    public static final int CFG_COMBAT_MODE_NEW = 64;
    public static final int CFG_COMBAT_MODE_LEGACY = 11328;

    public static final int CFG_AUTO_RETALIATE_ON = 0;
    public static final int CFG_AUTO_RETALIATE_OFF = 1;

    public static final int CFG_RUNNING_OFF = 0;
    public static final int CFG_RUNNING_ON = 1;

    public static final int CFG_WEAPON_SHEATHE_ACTIVE = 0;

    public static final int CFG_WARNING_CLAN_WARS_SAFE_ACTIVE = 0;
    public static final int CFG_WARNING_CLAN_WARS_DANGEROUS_ACTIVE = 1;

    public static final int CFG_WITHDRAW_NOTE_ACTIVE = 1;

    public static final int CFG_PRIVACY_ANYBODY = 0;
    public static final int CFG_PRIVACY_FRIENDS_ONLY = 67108864;
    public static final int CFG_PRIVACY_NOBODY = 134217728;

    /**
     * Gets the varbit value from the global config.
     * @param id The varbit id
     * @return The varbit value
     */

    public static native int getVarbitValue(int id);

    /**
     * Gets the varbit value of an item in an item container.
     * @param invId The item container Id
     * @param slot The item container slot
     * @param id The varbit id
     * @return The value associated with the item in the container.
     */

    public static native int getVarbitValue(int invId, int slot, int id);

    /**
     * Gets the varp value from global config.
     * Previously known as ConVars
     * @param id The varp id
     * @return The varp value
     */

    public static native int getVarpValue(int id);

    /**
     * Gets the value of a script variable tha tis used in RuneScape client scripts.
     * @param id The scirpt id
     * @return The script variables' value
     */

    public static native long getScriptVarValue(int id);

    /**
     * Gets the value of a client variable, associated with some kind of client state
     * @param id The client variable id
     * @return The client variable value
     */

    public static native int getClientVarValue(int id);
}
