package abyss.plugin.api.entities.hitmarks;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Not serializable for now
 * This class is dependent on the @{@link abyss.plugin.api.entities.Identifiable}
 * interface, however this identifier is lost on Jagex server restarts, making serialization of hitmarks hard.
 */

public final class HitmarkManager {
    public static final HitmarkManager MANAGER = new HitmarkManager();
    private final Map<Integer, Map<HitmarkType, Integer>> entities;

    private HitmarkManager() {
        this.entities = new HashMap<>();
    }

    public boolean hasHitmarks(int pathingEntityIndex) {
        return entities.containsKey(pathingEntityIndex);
    }

    public boolean hasHitmarks(int pathingEntityIndex, HitmarkType type) {
        if(!entities.containsKey(pathingEntityIndex)) {
            return false;
        }
        Map<HitmarkType, Integer> hitmarks = entities.get(pathingEntityIndex);
        return hitmarks.containsKey(type);
    }

    public void addHit(int pathingEntityIndex, HitmarkType type, int amount) {
        Map<HitmarkType, Integer> hitmarks;
        if(entities.containsKey(pathingEntityIndex)) {
            hitmarks = this.entities.get(pathingEntityIndex);
            Integer currentTotal = hitmarks.get(type);
            hitmarks.put(type, (currentTotal + amount));
        } else {
            hitmarks = new EnumMap<>(HitmarkType.class);
            hitmarks.put(type, amount);
        }
        entities.put(pathingEntityIndex, hitmarks);
    }

    public int getTotalHitFor(int pathingEntityIndex, HitmarkType type) {
        if(entities.containsKey(pathingEntityIndex)) {
            Map<HitmarkType, Integer> hitmarks = entities.get(pathingEntityIndex);
            if(hitmarks.containsKey(type)) {
                return hitmarks.get(type);
            }
        }
        return 0;
    }

    public boolean clear(int pathingEntityIndex) {
        if(entities.containsKey(pathingEntityIndex)) {
            entities.remove(pathingEntityIndex);
            return true;
        }
        return false;
    }

    public boolean clear(int pathingEntityIndex, HitmarkType type) {
        if(entities.containsKey(pathingEntityIndex)) {
            Map<HitmarkType, Integer> hitmarks = entities.get(pathingEntityIndex);
            hitmarks.put(type, 0);
            entities.put(pathingEntityIndex, hitmarks);
            return true;
        }
        return false;
    }
}
