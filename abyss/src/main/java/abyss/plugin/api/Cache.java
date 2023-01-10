package abyss.plugin.api;

import com.abyss.filesystem.Filesystem;
import com.abyss.filesystem.sqlite.SqliteFilesystem;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides access to the cache. All access to cache game memory must be done
 * on the engine thread to prevent memory corruption.
 */
public final class Cache {

    /**
     * A cache of items.
     */
    private static final Map<Integer, CacheItem> items = new HashMap<>();

    /**
     * A cache of objects.
     */
    private static final Map<Integer, CacheObject> objects = new HashMap<>();

    /**
     * A cache of NPCs.
     */
    private static final Map<Integer, CacheNpc> npcs = new HashMap<>();

    /**
     * A lock for accessing the cache.
     */

    private static final Filesystem FS = new SqliteFilesystem(Paths.get("C:\\ProgramData\\Jagex\\RuneScape"));

    private Cache() {
    }

    public static void reset() {
        items.clear();
        npcs.clear();
        objects.clear();
    }

    /**
     * Retrieves an item from the cache. The fields within this item
     * may not immediately be available if this item has not been
     * retrieved before.
     *
     * @param id    The id of the item to retrieve.
     * @return The retrieved item.
     */
    public static CacheItem getItem(int id) {
        if (id < 0) {
            return null;
        }
        if(items.containsKey(id)) {
            return items.get(id);
        }
        return getItem0(id);
    }

    /**
     * Retrieves an object from the cache. The fields within this object
     * may not immediately be available if this object has not been
     * retrieved before.
     *
     * @param id    The id of the object to retrieve.
     * @return The retrieved object.
     */
    public static CacheObject getObject(int id) {
        if (id < 0) {
            return null;
        }
        if(objects.containsKey(id)) {
            return objects.get(id);
        }
        return getObject0(id);
    }

    public static CacheObject getObject(int id, boolean dummy) {
        return getObject(id);
    }

    /**
     * Retrieves an NPC from the cache. The fields within this NPC
     * may not immediately be available if this NPC has not been
     * retrieved before.
     *
     * @param id    The id of the NPC to retrieve.
     * @return The retrieved NPC.
     */
    public static CacheNpc getNpc(int id) {
        if (id < 0) {
            return null;
        }
        if(npcs.containsKey(id)) {
            return npcs.get(id);
        }
        return getNpc0(id);
    }

    private static native CacheNpc getNpc0(int id);
    private static native CacheItem getItem0(int id);
    private static native CacheObject getObject0(int id);

    public static Filesystem getFilesystem() {
        return FS;
    }
}
