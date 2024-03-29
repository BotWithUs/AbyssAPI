package abyss.plugin.api.entities;

import abyss.plugin.api.Vector2i;
import abyss.plugin.api.Vector3i;

/**
 * An animation in the scene that moves across tiles over a period of time.
 */
public final class ProjectileAnimation extends Entity {

    private int id;
    private Vector3i startPosition;
    private Vector3i endPosition;
    private long startCycle;
    private long endCycle;

    private int modelId;
    private int animationId;

    private int sourceIndex;
    private int sourceType;
    private int targetIndex;
    private int targetType;

    public int getId() {
        return id;
    }

    public Vector3i getStartPosition() {
        return startPosition;
    }

    public Vector3i getEndPosition() {
        return endPosition;
    }

    public long getStartCycle() {
        return startCycle;
    }

    public long getEndCycle() {
        return endCycle;
    }

    public int getModelId() {
        return modelId;
    }

    public int getAnimationId() {
        return animationId;
    }

    public int getSourceIndex() {
        return sourceIndex;
    }

    public int getSourceType() {
        return sourceType;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public int getTargetType() {
        return targetType;
    }

    /**
     * Calculates the velocity of this animation across the total distance.
     * This value is the average of the lowest and highest velocity.
     */
    public double getVelocity() {
        double distance = startPosition.distance(endPosition);
        return distance / (endCycle - startCycle);
    }

    //public double getAcceleration(){}

    public Vector2i getCurrentPosition(long currentCycle) {
        double elapsedTime = currentCycle - startCycle;
        double velocity = getVelocity();
        double distanceTraveled = velocity * elapsedTime;

        double xDiff = endPosition.getX() - startPosition.getX();
        double yDiff = endPosition.getY() - startPosition.getY();

        double dist = startPosition.distance(endPosition);
        double xTraveled = distanceTraveled * (xDiff / dist);
        double yTraveled = distanceTraveled * (yDiff / dist);

        int x = (int) (startPosition.getX() + xTraveled);
        int y = (int) (startPosition.getY() + yTraveled);

        return new Vector2i(x, y);
    }
}
