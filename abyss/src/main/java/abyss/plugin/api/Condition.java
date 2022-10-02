package abyss.plugin.api;

/**
 * A condition which may or may not be met.
 */
public interface Condition {

    /**
     * Determines if the condition is met.
     *
     * @return If the condition is met.
     */
    boolean met();

}
