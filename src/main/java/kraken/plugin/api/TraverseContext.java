package kraken.plugin.api;

/**
 * Contains contextual information about movement along a path.
 */
public class TraverseContext {

    private double skipThreshold = 30000.0;
    private Vector3i destination;
    private Vector3i lastTile;
    private Vector3i lastWalk;
    private boolean finished;

    /**
     * @param destination The tile to walk to.
     */
    public TraverseContext(Vector3i destination) {
        this.destination = destination;
    }

    /**
     * Retrieves the skip threshold to use for walking. A higher value means
     * further away tiles will be selected as targets.
     *
     * @return The skip threshold to use for walking.
     */
    public double getSkipThreshold() {
        return skipThreshold;
    }

    /**
     * Changes the skip threshold to use for walking.
     *
     * @see TraverseContext#getSkipThreshold()  for more information
     * @param skipThreshold The new skip threshold to use for walking.
     */
    public void setSkipThreshold(double skipThreshold) {
        this.skipThreshold = skipThreshold;
    }

    /**
     * Retrieves the last web node that the traversal algorithm moved to.
     *
     * @return The last web node that the traversal algorithm moved to.
     */
    public Vector3i getLastTile() {
        return lastTile;
    }

    /**
     * Retrieves the last tile that the traversal algorithm moved to.
     *
     * @return The last tile that the traversal algorithm moved to.
     */
    public Vector3i getLastWalk() {
        return lastWalk;
    }

    /**
     * Determines if traversal is complete.
     *
     * @return If traversal is complete.
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Retrieves the tile being traversed to.
     *
     * @return The destination tile being traversed to.
     */
    public Vector3i getDestination() {
        return destination;
    }

    /**
     * Resets this traversal context so that it may be used again.
     */
    public void reset() {
        finished = false;
    }
}
