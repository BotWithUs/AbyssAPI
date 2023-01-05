package abyss.plugin.api;

import abyss.plugin.api.entities.Npc;
import abyss.plugin.api.entities.Player;
import abyss.plugin.api.query.Queries;
import abyss.plugin.api.query.npc.NpcQuery;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A provider of NPCs.
 */
@Deprecated(forRemoval = true)
public final class Npcs {

    private Npcs() {
    }

    private static final NpcQuery all = Queries.newNpcQuery();

    /**
     * Retrieves all NPCs.
     *
     * @return All NPCs.
     */
    public static Npc[] all() {
        return all.result().stream().toArray(Npc[]::new);
    }

    /**
     * Retrieves all NPCs that match the provided filter.
     *
     * @return All NPCs that match the provided filter.
     */
    @Deprecated(forRemoval = true)
    public static Npc[] all(Predicate<Npc> filter) {
        List<Npc> filtered = new LinkedList<>();
        for (Npc n : all()) {
            if (filter.test(n)) {
                filtered.add(n);
            }
        }
        return filtered.toArray(new Npc[0]);
    }

    /**
     * Counts the number of available NPCs that pass the provided filter.
     *
     * @param filter The filter to use for counting.
     * @return The number of NPCs that passed the provided filter.
     */
    public static int count(Predicate<Npc> filter) {
        return all(filter).length;
    }

    /**
     * Finds the best NPC matching the provided filter.
     *
     * @param comparator The comparator to use for determining priority.
     * @return The found NPC, or NULL if one was not found.
     */
    public static Npc best(Comparator<Npc> comparator) {
        List<Npc> filtered = Arrays.asList(all());
        filtered.sort(comparator);
        if (filtered.isEmpty()) {
            return null;
        }

        return filtered.get(0);
    }

    /**
     * Finds the best NPC matching the provided filter.
     *
     * @param filter The filter NPCs must pass through in order to be accepted.
     * @param comparator The comparator to use for determining priority.
     * @return The found NPC, or NULL if one was not found.
     */
    public static Npc best(Predicate<Npc> filter, Comparator<Npc> comparator) {
        List<Npc> filtered = Arrays.asList(all(filter));
        filtered.sort(comparator);

        return filtered.isEmpty() ? null : filtered.get(0);
    }

    /**
     * Finds the closest NPC matching the provided filter.
     *
     * @param filter The filter NPCs must pass through in order to be accepted.
     * @return The found NPC, or NULL if one was not found.
     */
    public static Npc closest(Predicate<Npc> filter) {
        Player self = Players.self();
        if (self == null) {
            return null;
        }

        Vector3 center = self.getScenePosition();
        Npc closest = null;
        float closestDistance = 0.f;
        for (Npc npc : all(filter)) {
            float distance = npc.getScenePosition().distance(center);
            if (closest == null || distance < closestDistance) {
                closest = npc;
                closestDistance = distance;
            }
        }

        return closest;
    }

    /**
     * Iterates over each NPC.
     *
     * @param cb The callback for invoke for each NPC.
     */
    public static void forEach(Consumer<Npc> cb) {
        for (Npc n : all()) {
            cb.accept(n);
        }
    }

    /**
     * Finds a NPC by their server side index.
     *
     * @param index The server index to search for.
     * @return The found NPC, or NULL if one was not found.
     */
    public static Npc byServerIndex(int index) {
        return closest((n) -> n.getIdentifier() == index);
    }



}
