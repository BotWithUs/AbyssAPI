package abyss.plugin.api;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A provider for effects.
 */
public final class Effects {

    private Effects() {
    }

    /**
     * Retrieves all effects.
     *
     * @return All effects.
     */
    public static native Effect[] all();

    /**
     * Retrieves all effects that match the provided filter.
     *
     * @return All effects that match the provided filter.
     */
    public static Effect[] all(Predicate<Effect> filter) {
        List<Effect> filtered = new LinkedList<>();
        for (Effect e : all()) {
            if (filter.test(e)) {
                filtered.add(e);
            }
        }
        return filtered.toArray(new Effect[0]);
    }

    /**
     * Finds the closest effect matching the provided filter.
     *
     * @param filter The filter effects must pass through in order to be accepted.
     * @return The found effect, or NULL if one was not found.
     */
    public static Effect closest(Predicate<Effect> filter) {
        Player self = Players.self();
        if (self == null) {
            return null;
        }

        Vector3 center = self.getScenePosition();
        Effect closest = null;
        float closestDistance = 0.f;
        for (Effect e : all(filter)) {
            float distance = e.getScenePosition().distance(center);
            if (closest == null || distance < closestDistance) {
                closest = e;
                closestDistance = distance;
            }
        }

        return closest;
    }

    /**
     * Iterates over each effect.
     *
     * @param cb The callback for invoke for each effect.
     */
    public static void forEach(Consumer<Effect> cb) {
        for (Effect e : all()) {
            cb.accept(e);
        }
    }

}
