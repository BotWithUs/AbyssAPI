package abyss.plugin.api;

import abyss.plugin.api.entities.Identifiable;
import abyss.plugin.api.entities.hitmarks.HitmarkManager;

import java.util.*;

/**
 * A snapshot of a pathing entity (character) in the game world. This data is constant,
 * and will not be changed after this object is created.
 */
public abstract class PathingEntity extends Entity implements Hitmarks, Identifiable {

    public static final int STATUS_HEALTH = 0;
    public static final int STATUS_ADRENALINE = 5;
    public static final int STATUS_MINING = 13;

    private int serverIndex;
    private boolean isMoving;
    private final Map<Integer, Boolean> activeStatusBars = new HashMap<>();
    private final Map<Integer, Float> statusBarFill = new HashMap<>();
    private final List<Hitmark> hitmarks = new ArrayList<>(5);
    private int animationId;
    private boolean isAnimationPlaying;
    private int interactingIndex;
    private Vector2 directionOffset;

    /**
     * Do not make instances of this.
     */
    PathingEntity() {
    }

    /**
     * Retrieves the state of all status bars.
     */
    public Map<Integer, Boolean> getActiveStatusBars() {
        return activeStatusBars;
    }

    /**
     * Retrieves the fill of all status bars.
     */
    public Map<Integer, Float> getStatusBarFill() {
        return statusBarFill;
    }

    /**
     * Retrieves this entities server index.
     *
     * @return This entities server index.
     */
    @Override
    public int getIdentifier() {
        return serverIndex;
    }


    public int getServerIndex() {
        return getIdentifier();
    }


    /**
     * Determines if this spirit is currently moving.
     *
     * @return If this spirit is currently moving.
     */
    public boolean isMoving() {
        return isMoving;
    }

    /**
     * Determines if a status bar is active.
     *
     * @return If the status bar with the provided id is active.
     */
    public boolean isStatusBarActive(int id) {
        return activeStatusBars.containsKey(id) && activeStatusBars.get(id);
    }

    /**
     * Retrieves the fill of a status bar (0-1.)
     *
     * @return The fill of a status bar.
     */
    public float getStatusBarFill(int id) {
        return statusBarFill.getOrDefault(id, 0.0f);
    }

    /**
     * Gets all associated hitmarks.
     *
     * @return a non-null List.
     */
    @Override
    public List<Hitmark> getHitmarks() {
        return hitmarks;
    }

    /**
     * Get all associated hitmarks with the given id.
     *
     * @param id the id of the hitmark, which can also be thought of as type.
     * @return A non-null List
     */
    @Override
    public List<Hitmark> getHitmarks(int id) {
        return getHitmarks().stream().filter(h -> h.getId() == id).toList();
    }

    @Override
    public int getTotalNumber(HitmarkType type) {
        return HitmarkManager.MANAGER.getTotalHitFor(serverIndex, type);
    }

    @Override
    public boolean clearHitmark(HitmarkType type) {
        return HitmarkManager.MANAGER.clear(serverIndex, type);
    }

    @Override
    public boolean clearHitmarks() {
        return HitmarkManager.MANAGER.clear(serverIndex);
    }

    /**
     * Retrieves the id of the playing animation.
     *
     * @return The id of the playing animation, or -1 if no animation is playing.
     */
    public int getAnimationId() {
        return animationId;
    }

    /**
     * Determines if this spirit has an animation playing.
     *
     * @return If this spirit has an animation playing.
     */
    public boolean isAnimationPlaying() {
        return isAnimationPlaying;
    }

    /**
     * Retrieves the server index of the spirit being interacted with.
     *
     * @return The server index of the spirit being interacted with.
     */
    public int getInteractingIndex() {
        return interactingIndex;
    }

    /**
     * Retrieves the directional offset of this spirit.
     *
     * @return The directional offset of this spirit.
     */
    public Vector2 getDirectionOffset() {
        return directionOffset;
    }

    /**
     * Retrieves the spirit being interacted with.
     *
     * @return The spirit being interacted with.
     */
    public PathingEntity getInteracting() {
        int index = getInteractingIndex();
        if (index == -1) {
            return null;
        }

        return (PathingEntity) Entities.byServerIndex(index);
    }

    /**
     * Interacts with this entity.
     */
    public void interact(int type) {
        Actions.entity(this, type);
    }


    public abstract boolean isReachable();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PathingEntity pathingEntity = (PathingEntity) o;
        return serverIndex == pathingEntity.serverIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), serverIndex);
    }
}
