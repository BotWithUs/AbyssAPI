package kraken.plugin.api;

import abyss.plugin.api.actions.attributes.PluginAttributes;
import abyss.plugin.api.imgui.containers.ImVerticalPane;

import java.io.*;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Random;

/**
 * All plugins must extend this base type.
 */
public abstract class Plugin {

    public final PluginAttributes attributes = new PluginAttributes(new HashMap<>());
    public final PluginAttributes sharedAttributes = new PluginAttributes(new HashMap<>());

    protected ImVerticalPane pane = null;

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

    public void initImGui() {
        pane = new ImVerticalPane();
    }

    /**
     * Called when the plugin's window is being painted.
     */
    public void onPaint() {
        if(pane != null) {
            pane.getSkin().onPaint();
        }
    }

    /**
     * Called when the client's 3d overlay is being painted.
     */
    public void onPaintOverlay() {

    }

    public void onActionMenuFired(int type, int param1, int param2, int param3, int param4) {

    }

    /**
     * Called when a connection variable changes.
     */
    public void onConVarChanged(ConVar conv, int oldValue, int newValue) {

    }

    /**
     * Called when logs are pushed to the imgui console
     * @param log The Log that was printed
     */

    public void onDebugLog(String log) {

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
        attributes.put(key, Integer.toString(value));
    }

    public final void setAttribute(String key, boolean value) {
        attributes.put(key, Boolean.toString(value));
    }

    public final void setAttribute(String key, double value) {
        attributes.put(key, Double.toString(value));
    }

    public final void setAttribute(String key, float value) {
        attributes.put(key, Float.toString(value));
    }

    public final void setAttribute(String key, String value) {
        attributes.put(key, value);
    }

    public final int getInt(String key) {
        try {
            return attributes.containsKey(key) ? Integer.parseInt(attributes.get(key)) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public final boolean getBoolean(String key) {
        try {
            return attributes.containsKey(key) && Boolean.parseBoolean(attributes.get(key));
        } catch (Exception e) {
            return false;
        }
    }

    public final double getDouble(String key) {
        try {
            return attributes.containsKey(key) ? Double.parseDouble(attributes.get(key)) : 0.0;
        } catch (Exception e) {
            return 0.0;
        }
    }

    public final double getFloat(String key) {
        try {
            return attributes.containsKey(key) ? Float.parseFloat(attributes.get(key)) : 0.0;
        } catch (Exception e) {
            return 0.0;
        }
    }

    public final String getString(String key) {
        return attributes.getOrDefault(key, "");
    }

    public final void save(PluginContext context) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(out);

        try {
            data.writeByte(attributes.size());
            attributes.forEach((key, value) -> {
                try {
                    data.writeUTF(key);
                    data.writeUTF(value);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            Debug.printStackTrace(e.getMessage(), e);
        }

        context.setPersistentData(out.toByteArray());
    }

    public final void load(PluginContext context) {
        ByteArrayInputStream input = new ByteArrayInputStream(context.getPersistentData());
        DataInputStream data = new DataInputStream(input);
        try {
            int size = data.readUnsignedByte();
            for (int i = 0; i < size; i++) {
                String key = data.readUTF();
                String value = data.readUTF();
                attributes.put(key, value);
            }
        } catch (IOException e) {
            Debug.printStackTrace(e.getMessage(), e);
        }
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
