package abyss.plugin.api.entities.markers;

import abyss.Utils;
import abyss.plugin.api.Area3di;
import abyss.plugin.api.Players;
import abyss.plugin.api.Vector3i;
import abyss.plugin.api.entities.Player;
import abyss.plugin.api.query.players.PlayerQuery;
import abyss.plugin.api.world.WorldTile;

import java.util.List;

@FunctionalInterface
public interface Locatable {

    Vector3i getGlobalPosition();

    default Area3di getGlobalArea() {
        Vector3i position = getGlobalPosition();
        return new Area3di(position, position);
    }

    /*
    //TODO
    default boolean isReachableFrom(Locatable position) {
        return Utils.isTileReachable(position.getGlobalPosition());
    }

    default boolean isReachable() {
        return isReachableFrom(Players.self());
    }

    */
}