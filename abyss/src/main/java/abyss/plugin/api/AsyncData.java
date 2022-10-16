package abyss.plugin.api;

/**
 * Provides state related to asynchronous data.
 */
public abstract class AsyncData {

    /**
     * If the data has been loaded or not.
     */
    private boolean loaded = false;

    /**
     * Determines if the data has been loaded or not.
     *
     * @return If the data has been loaded or not.
     */
    public boolean isLoaded() {
        return loaded;
    }
}
