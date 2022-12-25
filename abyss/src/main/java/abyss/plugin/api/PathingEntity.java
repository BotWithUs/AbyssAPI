package abyss.plugin.api;

import abyss.plugin.api.entities.Identifiable;

import java.util.*;

/**
 * A snapshot of a pathing entity (character) in the game world. This data is constant,
 * and will not be changed after this object is created.
 */
public abstract class PathingEntity extends Entity implements Identifiable {

    public static final int STATUS_HEALTH = 0;
    public static final int STATUS_ADRENALINE = 5;
    public static final int STATUS_MINING = 13;

    private int serverIndex;
    private boolean isMoving;
    private final List<Hitmark> hitmarks = new ArrayList<>(5);
    private final List<Headbar> headbars = new ArrayList<>(6);
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
     * Gets all associated hitmarks.
     *
     * @return a non-null List.
     */
    public List<Hitmark> getHitmarks() {
        return hitmarks;
    }

    /**
     * Get all associated hitmarks with the given id.
     *
     * @param id the id of the hitmark, which can also be thought of as type.
     * @return A non-null List
     */
    public List<Hitmark> getHitmarks(int id) {
        return getHitmarks().stream().filter(h -> h.getId() == id).toList();
    }

    public List<Headbar> getHeadbars() {
        return headbars;
    }

    public List<Headbar> getHeadbars(int id) {
        return getHeadbars().stream().filter(hb -> hb.getId() == id).toList();
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
