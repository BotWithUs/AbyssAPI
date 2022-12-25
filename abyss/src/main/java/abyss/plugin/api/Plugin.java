package abyss.plugin.api;

import abyss.map.Region;
import abyss.map.WorldObject;
import abyss.plugin.api.extensions.Extension;
import abyss.plugin.api.extensions.ExtensionContainer;
import abyss.plugin.api.plugin.attributes.Attributes;
import abyss.plugin.api.query.players.PlayerQuery;
import abyss.plugin.api.widgets.BackpackWidgetExtension;
import abyss.plugin.api.world.WorldTile;
import com.abyss.definitions.ObjectType;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * All plugins must extend this base type.
 */
public abstract class Plugin implements ExtensionContainer<Extension> {

    public final Attributes persistentAttributes = new Attributes();
    public final Attributes attributes = new Attributes();

    private final Map<Class<?>, Extension> pluginExtensions = new HashMap<>();

    /**
     * A random instance that is seeded with information about the running account.
     */
    private final Random accountSpecificRandom = new Random(Abyss.getStaticRngSeed(3));

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
     * Called when the server sends the end of tick packet
     *
     * @param self      - The Local Player
     * @param tickCount - The tick count since login (resets to 0 on logout)
     * @return - the number of ticks to wait
     */

    public int onServerTick(Player self, long tickCount) {
        return 0;
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

    /**
     * Called when the runescape client asks for the value of a varbit
     *
     * @param varbitId - The Varbit ID
     * @param varpId   - The ConVarID that the varbit is stored in
     * @param value    - The Value of the requested Varbit
     */

    public void onVarbitRequest(int varbitId, int varpId, int value) {

    }

    /**
     * Called when the RuneScape client asks for the value of a varp (ConVar)
     *
     * @param varpId The varp id
     * @param value  The varp value
     */

    public void onVarpRequest(int varpId, int value) {

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
     * Called when RuneScape gets a spawn object packet
     *
     * @param objectId The object id
     * @param x        The x coordinate where the object is being spawned
     * @param y        The y coordinate where the object is being spawned
     * @param type     The type of object (wall, groun decor, etc)
     */

    public void onSceneObjectSpawned(int objectId, int x, int y, int type) {

    }

    private void onSceneObjectAdded(int objectId, int rotation, int x, int y, int type) {
        Player self = PlayerQuery.self();
        if (self == null) {
            return;
        }
        Vector3i pos = self.getGlobalPosition();
        WorldTile tile = new WorldTile(x, y, 0);
        Region region = Region.get(tile.getRegionId());
        ObjectType otype = ObjectType.forId(type);
        if (otype == null) {
            return;
        }
        WorldObject wo = new WorldObject(x, y, pos.getZ(), objectId, rotation, otype);
        region.spawnObject(wo, pos.getZ(), tile.getXInRegion(), tile.getYInRegion());

        onSceneObjectSpawned(objectId, x, y, type);
    }

    /**
     * Called when RuneScape sends destroy scene object packet.
     *
     * @param objectId The object id to be destroyed
     * @param x        The x coordinate of the scene object to be destroyed
     * @param y        The y coordinate of the scene object to be destroyed
     * @param type     The type of the scene object to be destroyed
     */

    public void onSceneObjectDestroyed(int objectId, int x, int y, int type) {

    }

    private void onSceneObjectRemoved(int x, int y, int type) {
        Player self = PlayerQuery.self();
        if (self == null) {
            return;
        }
        Vector3i pos = self.getGlobalPosition();
        WorldTile tile = new WorldTile(x, y, 0);
        Region region = Region.get(tile.getRegionId());
        ObjectType otype = ObjectType.forId(type);
        if (otype == null) {
            return;
        }
        WorldObject wo = region.objects[pos.getZ()][tile.getXInRegion()][tile.getYInRegion()][otype.slot];
        if (wo == null) {
            return;
        }
        if (wo.getType() == null) { // wtf
            wo.setType(otype);
        }
        region.destroyObject(wo, pos.getZ(), tile.getXInRegion(), tile.getYInRegion());

        onSceneObjectDestroyed(wo.getId(), wo.getX(), wo.getY(), wo.getType().id);
    }

    /**
     * Called when an item in the inventory is changed.
     */
    private void inventoryItemChanged(ComponentItem prev, ComponentItem next) {
        if (!Backpack.BACKPACK.hasExtension(BackpackWidgetExtension.class)) {
            return;
        }
        BackpackWidgetExtension ext = (BackpackWidgetExtension) Backpack.BACKPACK.getExt(BackpackWidgetExtension.class);
        Inventory inventory = Inventories.byId(ext.getContainerId());
        prev.setInventory(inventory);
        next.setInventory(inventory);
        onInventoryItemChanged(prev, next);
    }

    public void onInventoryItemChanged(ComponentItem prev, ComponentItem next) {

    }

    /**
     * Called to determine if a break should be interrupted currently.
     */
    public boolean interruptBreak() {
        return false;
    }

    public final void save(PluginContext context) {
        try {
            persistentAttributes.flush();
            context.setPersistentData(persistentAttributes.toByteArray());
        } catch (IOException e) {
            Debug.printStackTrace("Failed to save persistent attributes", e);
        }
    }

    public final void load(PluginContext context) {
        try {
            persistentAttributes.read(new ByteArrayInputStream(context.getPersistentData()));
        } catch (IOException e) {
            Debug.printStackTrace("Attributes failed to load", e);
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

    @NotNull
    @Override
    public Extension getExt(@NotNull Class<?> clazz) {
        return pluginExtensions.get(clazz);
    }

    @Override
    public boolean hasExtension(@NotNull Class<?> clazz) {
        return pluginExtensions.containsKey(clazz);
    }

    @Override
    public void setExtension(@NotNull Extension extension) {
        pluginExtensions.put(extension.getClass(), extension);
    }

    @NotNull
    @Override
    public List<Extension> getExtensions() {
        return pluginExtensions.values().stream().toList();
    }
}
