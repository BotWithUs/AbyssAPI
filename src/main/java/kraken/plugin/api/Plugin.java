package kraken.plugin.api;

import abyss.plugin.api.actions.attributes.DefaultPluginAttributeSerializer;
import abyss.plugin.api.actions.attributes.PluginAttributes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * All plugins must extend this base type.
 */
public abstract class Plugin {

    public final PluginAttributes persistentAttributes = new PluginAttributes(new HashMap<>(), DefaultPluginAttributeSerializer.INSTANCE);
    public final PluginAttributes attributes = new PluginAttributes(new HashMap<>(), DefaultPluginAttributeSerializer.INSTANCE);

    public final Map<Integer, VarbitRequest> requestedVarbits = new HashMap<>();

    /**
     * A random instance that is seeded with information about the running account.
     */
    private final Random accountSpecificRandom = new Random(Kraken.getStaticRngSeed(3));

    /**
     * A random instance that is secure.
     */
    private final Random secureRandom = new SecureRandom();

    /**
     * Called when the plugin is loaded into the client.
     *
     * @return If the plugin will run or not.
     */
    public boolean onLoaded(PluginContext pluginContext) {
        return true;
    }

    /**
     * Called when this plugin is enabled in the plugin list.
     */
    public void onEnabled() {

    }

    /**
     * Called when this plugin is disabled in the plugin list.
     */
    public void onDisabled() {

    }

    /**
     * Called when the client is ticking, and it's time for us
     * to loop again. The client will wait for the amount of milliseconds
     * you return before calling this function again.
     *
     * @return The amount of time to wait before invoking this function again.
     */
    public int onLoop() {
        return 60000;
    }

    /**
     * Called when the plugin's window is being painted.
     */
    public void onPaint() {

    }

    /**
     * Called when the client's 3d overlay is being painted.
     */
    public void onPaintOverlay() {

    }

    public void onActionMenuFired(int type, int param1, int param2, int param3, int param4, boolean isSynthetic) {

    }

    /**
     * Called when a connection variable changes.
     */
    public void onConVarChanged(ConVar conv, int oldValue, int newValue) {

    }

    public void test() {

        attributes.addListener("agility_course", (oldValue, newValue) -> {
            switch (newValue) {
                case "gnome":
                    //Walk to gnome
                    break;
                case "other":
                    //Walk to this one
                    break;
            }
        });

        //Remembers values after crashes/restarts
        persistentAttributes.put("agility_course", "gnome");

    }

    /**
     * Called when logs are pushed to the imgui console
     *
     * @param log The Log that was printed
     */

    public void onDebugLog(String log) {

    }

    /**
     * Called when the runescape client asks for the value of a varbit
     *
     * @param varbitId - The Varbit ID
     * @param conVarId - The ConVarID that the varbit is stored in
     * @param value    - The Value of the requested Varbit
     */

    public void onVarbitRequest(int varbitId, int conVarId, int value) {

        if (!requestedVarbits.containsKey(varbitId)) {
            requestedVarbits.put(varbitId, new VarbitRequest(varbitId, conVarId, value));
        } else {
            VarbitRequest req = requestedVarbits.get(varbitId);
            req.setValue(value);
        }

    }

    /**
     * Called when the visibility of a widget changes.
     */
    public void onWidgetVisibilityChanged(int id, boolean visible) {

    }

    /**
     * Called when the local player is changed. This is useful for initializing plugin data
     * about the local player.
     */
    public void onLocalPlayerChanged(Player self) {

    }

    /**
     * Called when an item in the inventory is changed.
     */
    private void inventoryItemChanged(WidgetItem prev, WidgetItem next) {
        ItemContainer inventory = ItemContainers.byId(93);
        prev.setContainer(inventory);
        next.setContainer(inventory);
        onInventoryItemChanged(prev, next);
    }

    public void onInventoryItemChanged(WidgetItem prev, WidgetItem next) {

    }

    /**
     * Called to determine if a break should be interrupted currently.
     */
    public boolean interruptBreak() {
        return false;
    }

    public final void setAttribute(String key, int value) {
        persistentAttributes.put(key, Integer.toString(value));
    }

    public final void setAttribute(String key, boolean value) {
        persistentAttributes.put(key, Boolean.toString(value));
    }

    public final void setAttribute(String key, double value) {
        persistentAttributes.put(key, Double.toString(value));
    }

    public final void setAttribute(String key, float value) {
        persistentAttributes.put(key, Float.toString(value));
    }

    public final void setAttribute(String key, String value) {
        persistentAttributes.put(key, value);
    }

    public final int getInt(String key) {
        try {
            return persistentAttributes.containsKey(key) ? Integer.parseInt(persistentAttributes.getOrDefault(key, "0")) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public final boolean getBoolean(String key) {
        try {
            return persistentAttributes.containsKey(key) && Boolean.parseBoolean(persistentAttributes.get(key));
        } catch (Exception e) {
            return false;
        }
    }

    public final double getDouble(String key) {
        try {
            return persistentAttributes.containsKey(key) ? Double.parseDouble(persistentAttributes.getOrDefault(key, "0.0")) : 0.0;
        } catch (Exception e) {
            return 0.0;
        }
    }

    public final double getFloat(String key) {
        try {
            return persistentAttributes.containsKey(key) ? Float.parseFloat(persistentAttributes.getOrDefault(key, "0.0")) : 0.0;
        } catch (Exception e) {
            return 0.0;
        }
    }

    public final String getString(String key) {
        return persistentAttributes.getOrDefault(key, "");
    }

    public final void save(PluginContext context) {
        ByteArrayOutputStream out = persistentAttributes.serialize(persistentAttributes);
        context.setPersistentData(out.toByteArray());
    }

    public final void load(PluginContext context) {
        persistentAttributes.deserialize(persistentAttributes, new ByteArrayInputStream(context.getPersistentData()));
    }

    /**
     * @return A random instance that is seeded with information about the running account.
     */
    public Random getAccountSpecificRandom() {
        return accountSpecificRandom;
    }

    /**
     * @return A random instance that is secure.
     */
    public Random getSecureRandom() {
        return secureRandom;
    }


}
