package abyss.plugin.api;

import abyss.plugin.api.query.players.PlayerQuery;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A provider for effects.
 */
@Deprecated(forRemoval = true)
public final class SpotAnimations {

    private SpotAnimations() {
    }

    /**
     * Retrieves all effects.
     *
     * @return All effects.
     */
    public static native SpotAnimation[] all();

    /**
     * Retrieves all effects that match the provided filter.
     *
     * @return All effects that match the provided filter.
     */
    public static SpotAnimation[] all(Predicate<SpotAnimation> filter) {
        List<SpotAnimation> filtered = new LinkedList<>();
        for (SpotAnimation e : all()) {
            if (filter.test(e)) {
                filtered.add(e);
            }
        }
        return filtered.toArray(new SpotAnimation[0]);
    }

    /**
     * Finds the closest effect matching the provided filter.
     *
     * @param filter The filter effects must pass through in order to be accepted.
     * @return The found effect, or NULL if one was not found.
     */
    public static SpotAnimation nearest(Predicate<SpotAnimation> filter) {
        Player self = PlayerQuery.self();
        if (self == null) {
            return null;
        }

        Vector3 center = self.getScenePosition();
        SpotAnimation closest = null;
        float closestDistance = 0.f;
        for (SpotAnimation e : all(filter)) {
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
    public static void forEach(Consumer<SpotAnimation> cb) {
        for (SpotAnimation e : all()) {
            cb.accept(e);
        }
    }

}
