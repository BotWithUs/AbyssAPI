package abyss.plugin.api.entities.markers;

/**
 * This provides methods that help identify pathing entities across deaths.
 * This is not a reliable method to retain identify over Jagex server restarts.
 */

public interface Identifiable {
    int getIdentifier();
}
