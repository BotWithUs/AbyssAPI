package abyss.plugin.api.entities.hitmarks;

import abyss.plugin.api.Hitmark;

import java.util.List;

public interface Hitmarks {

    /**
     * Gets all associated hitmarks.
     *
     * @return a non-null List.
     */
    List<Hitmark> getHitmarks();

    /**
     * Get all associated hitmarks with the given id.
     *
     * @param id the id of the hitmark, which can also be thought of as type.
     * @return A non-null List
     */
    List<Hitmark> getHitmarks(int id);

    /**
     * Gets the total number of all recorded hitmarks that have occured on this pathing entity
     * @param type - The type of hitmark
     * @return The total amount
     */

    int getTotalNumber(HitmarkType type);

    /**
     * Clears the hitmarks that were recorded by the bot.
     * @param type The type of hitmark to clear
     * @return Was this operation successful
     */

    boolean clearHitmark(HitmarkType type);

    /**
     * Clears are recorded hitmarks from the bot
     * @return Was this operation successful
     */

    boolean clearHitmarks();
}
