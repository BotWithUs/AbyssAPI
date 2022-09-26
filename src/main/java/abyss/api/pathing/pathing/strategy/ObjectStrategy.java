// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
//  Copyright © 2021 Trenton Kress
//  This file is part of project: FreeDev
//
package abyss.api.pathing.pathing.strategy;

import abyss.api.pathing.pathing.RouteStrategy;
import abyss.definitions.map.ObjectType;
import kraken.plugin.api.Cache;
import kraken.plugin.api.CacheObject;
import kraken.plugin.api.SceneObject;
import kraken.plugin.api.Vector3i;

public class ObjectStrategy extends RouteStrategy {

    private final int x;
    private final int y;
    private final int routeType;
    private final ObjectType type;
    private final int rotation;
    private final int sizeX;
    private final int sizeY;
    private int accessBlockFlag;

    public ObjectStrategy(SceneObject object) {
        Vector3i tile = object.getGlobalPosition();
        this.x = tile.getX();
        this.y = tile.getY();
        this.type = ObjectType.SCENERY_INTERACT;
        this.routeType = getType(type);
        this.rotation = 0;
        CacheObject def = Cache.getObject(object.getId());
        this.sizeX = def.getSizeX();
        this.sizeY = def.getSizeY();
        this.accessBlockFlag = def.getBreakroutefinding() ? 1 : 0;
    }

    @Override
    public boolean canExit(int currentX, int currentY, int sizeXY, int[][] clip, int clipBaseX, int clipBaseY) {
        return switch (routeType) {
            case 0 ->
                    RouteStrategy.checkWallInteract(clip, currentX - clipBaseX, currentY - clipBaseY, sizeXY, x - clipBaseX, y - clipBaseY, type, rotation);
            case 1 ->
                    RouteStrategy.checkWallDecorationInteract(clip, currentX - clipBaseX, currentY - clipBaseY, sizeXY, x - clipBaseX, y - clipBaseY, type, rotation);
            case 2 ->
                    RouteStrategy.checkFilledRectangularInteract(clip, currentX - clipBaseX, currentY - clipBaseY, sizeXY, sizeXY, x - clipBaseX, y - clipBaseY, sizeX, sizeY, accessBlockFlag);
            case 3 -> currentX == x && currentY == y;
            default -> false;
        };
    }

    @Override
    public int getApproxDestinationX() {
        return x;
    }

    @Override
    public int getApproxDestinationY() {
        return y;
    }

    @Override
    public int getApproxDestinationSizeX() {
        return sizeX;
    }

    @Override
    public int getApproxDestinationSizeY() {
        return sizeY;
    }

    private int getType(ObjectType type) {
        switch (type) {
            case WALL_STRAIGHT:
            case WALL_DIAGONAL_CORNER:
            case WALL_WHOLE_CORNER:
            case WALL_STRAIGHT_CORNER:
            case WALL_INTERACT:
                return 0;
            case STRAIGHT_INSIDE_WALL_DEC:
            case STRAIGHT_OUSIDE_WALL_DEC:
            case DIAGONAL_OUTSIDE_WALL_DEC:
            case DIAGONAL_INSIDE_WALL_DEC:
            case DIAGONAL_INWALL_DEC:
                return 1;
            case SCENERY_INTERACT:
            case GROUND_INTERACT:
            case GROUND_DECORATION:
                return 2;
            default:
                return 3;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ObjectStrategy strategy))
            return false;
        return x == strategy.x && y == strategy.y && routeType == strategy.routeType && type == strategy.type && rotation == strategy.rotation && sizeX == strategy.sizeX && sizeY == strategy.sizeY && accessBlockFlag == strategy.accessBlockFlag;
    }

}
