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
package abyss.map;

import com.abyss.definitions.ObjectType;
import abyss.plugin.api.world.WorldTile;

public class ClipMap {

    private int regionX;
    private int regionY;
    private int flags[][][];
    private boolean projectile;

    public ClipMap(int regionId, boolean projectile) {
        regionX = (regionId >> 8) * 64;
        regionY = (regionId & 0xff) * 64;
        flags = new int[4][64][64];
        this.projectile = projectile;
    }

    public int[][][] getMasks() {
        return flags;
    }

    public int getRegionX() {
        return regionX;
    }

    public int getRegionY() {
        return regionY;
    }

    public void addBlockedTile(int plane, int x, int y) {
        addFlag(plane, x, y, ClipFlag.PFBW_FLOOR.flag);
    }

    public void removeBlockedTile(int plane, int x, int y) {
        removeFlag(plane, x, y, ClipFlag.PFBW_FLOOR.flag);
    }

    public void addBlockWalkAndProj(int plane, int x, int y) {
        addFlag(plane, x, y, ClipFlag.PFBW_GROUND_DECO.flag);
    }

    public void removeBlockWalkAndProj(int plane, int x, int y) {
        removeFlag(plane, x, y, ClipFlag.PFBW_GROUND_DECO.flag);
    }

    public void addObject(int plane, int x, int y, int sizeX, int sizeY, boolean solid, boolean notAlternative) {
        int flag = ClipFlag.BW_FULL.flag;
        if (solid)
            flag |= ClipFlag.BP_FULL.flag;
        if (notAlternative)
            flag |= ClipFlag.PF_FULL.flag;
        for (int tileX = x; tileX < x + sizeX; tileX++) {
            for (int tileY = y; tileY < y + sizeY; tileY++) {
                addFlag(plane, tileX, tileY, flag);
            }
        }
    }

    public void removeObject(int plane, int x, int y, int sizeX, int sizeY, boolean solid, boolean notAlternative) {
        int flag = ClipFlag.BW_FULL.flag;
        if (solid)
            flag |= ClipFlag.BP_FULL.flag;
        if (notAlternative)
            flag |= ClipFlag.PF_FULL.flag;
        for (int tileX = x; tileX < sizeX + x; tileX++) {
            for (int tileY = y; tileY < y + sizeY; tileY++) {
                this.removeFlag(plane, tileX, tileY, flag);
            }
        }
    }

    public void addWall(int plane, int x, int y, ObjectType type, int rotation, boolean solid, boolean notAlternative) {
        switch (type) {
            case WALL_STRAIGHT -> {
                if (rotation == 0) {
                    addFlag(plane, x, y, ClipFlag.BW_W);
                    addFlag(plane, x - 1, y, ClipFlag.BW_E);
                }
                if (rotation == 1) {
                    addFlag(plane, x, y, ClipFlag.BW_N);
                    addFlag(plane, x, y + 1, ClipFlag.BW_S);
                }
                if (rotation == 2) {
                    addFlag(plane, x, y, ClipFlag.BW_E);
                    addFlag(plane, x + 1, y, ClipFlag.BW_W);
                }
                if (rotation == 3) {
                    addFlag(plane, x, y, ClipFlag.BW_S);
                    addFlag(plane, x, y - 1, ClipFlag.BW_N);
                }
            }
            case WALL_DIAGONAL_CORNER, WALL_STRAIGHT_CORNER -> {
                if (rotation == 0) {
                    addFlag(plane, x, y, ClipFlag.BW_NW);
                    addFlag(plane, x - 1, y + 1, ClipFlag.BW_SE);
                }
                if (rotation == 1) {
                    addFlag(plane, x, y, ClipFlag.BW_NE);
                    addFlag(plane, x + 1, y + 1, ClipFlag.BW_SW);
                }
                if (rotation == 2) {
                    addFlag(plane, x, y, ClipFlag.BW_SE);
                    addFlag(plane, x + 1, y - 1, ClipFlag.BW_NW);
                }
                if (rotation == 3) {
                    addFlag(plane, x, y, ClipFlag.BW_SW);
                    addFlag(plane, x - 1, y - 1, ClipFlag.BW_NE);
                }
            }
            case WALL_WHOLE_CORNER -> {
                if (rotation == 0) {
                    addFlag(plane, x, y, ClipFlag.BW_N, ClipFlag.BW_W);
                    addFlag(plane, x - 1, y, ClipFlag.BW_E);
                    addFlag(plane, x, y + 1, ClipFlag.BW_S);
                }
                if (rotation == 1) {
                    addFlag(plane, x, y, ClipFlag.BW_N, ClipFlag.BW_E);
                    addFlag(plane, x, y + 1, ClipFlag.BW_S);
                    addFlag(plane, x + 1, y, ClipFlag.BW_W);
                }
                if (rotation == 2) {
                    addFlag(plane, x, y, ClipFlag.BW_E, ClipFlag.BW_S);
                    addFlag(plane, x + 1, y, ClipFlag.BW_W);
                    addFlag(plane, x, y - 1, ClipFlag.BW_N);
                }
                if (rotation == 3) {
                    addFlag(plane, x, y, ClipFlag.BW_S, ClipFlag.BW_W);
                    addFlag(plane, x, y - 1, ClipFlag.BW_N);
                    addFlag(plane, x - 1, y, ClipFlag.BW_E);
                }
            }
            default -> {
            }
        }
        if (solid) {
            switch (type) {
                case WALL_STRAIGHT -> {
                    if (rotation == 0) {
                        addFlag(plane, x, y, ClipFlag.BP_W);
                        addFlag(plane, x - 1, y, ClipFlag.BP_E);
                    }
                    if (rotation == 1) {
                        addFlag(plane, x, y, ClipFlag.BP_N);
                        addFlag(plane, x, y + 1, ClipFlag.BP_S);
                    }
                    if (rotation == 2) {
                        addFlag(plane, x, y, ClipFlag.BP_E);
                        addFlag(plane, x + 1, y, ClipFlag.BP_W);
                    }
                    if (rotation == 3) {
                        addFlag(plane, x, y, ClipFlag.BP_S);
                        addFlag(plane, x, y - 1, ClipFlag.BP_N);
                    }
                }
                case WALL_DIAGONAL_CORNER, WALL_STRAIGHT_CORNER -> {
                    if (rotation == 0) {
                        addFlag(plane, x, y, ClipFlag.BP_NW);
                        addFlag(plane, x - 1, y + 1, ClipFlag.BP_SE);
                    }
                    if (rotation == 1) {
                        addFlag(plane, x, y, ClipFlag.BP_NE);
                        addFlag(plane, x + 1, y + 1, ClipFlag.BP_SW);
                    }
                    if (rotation == 2) {
                        addFlag(plane, x, y, ClipFlag.BP_SE);
                        addFlag(plane, x + 1, y - 1, ClipFlag.BP_NW);
                    }
                    if (rotation == 3) {
                        addFlag(plane, x, y, ClipFlag.BP_SW);
                        addFlag(plane, x - 1, y - 1, ClipFlag.BP_NE);
                    }
                }
                case WALL_WHOLE_CORNER -> {
                    if (rotation == 0) {
                        addFlag(plane, x, y, ClipFlag.BP_N, ClipFlag.BP_W);
                        addFlag(plane, x - 1, y, ClipFlag.BP_E);
                        addFlag(plane, x, y + 1, ClipFlag.BP_S);
                    }
                    if (rotation == 1) {
                        addFlag(plane, x, y, ClipFlag.BP_N, ClipFlag.BP_E);
                        addFlag(plane, x, y + 1, ClipFlag.BP_S);
                        addFlag(plane, x + 1, y, ClipFlag.BP_W);
                    }
                    if (rotation == 2) {
                        addFlag(plane, x, y, ClipFlag.BP_E, ClipFlag.BP_S);
                        addFlag(plane, x + 1, y, ClipFlag.BP_W);
                        addFlag(plane, x, y - 1, ClipFlag.BP_N);
                    }
                    if (rotation == 3) {
                        addFlag(plane, x, y, ClipFlag.BP_S, ClipFlag.BP_W);
                        addFlag(plane, x, y - 1, ClipFlag.BP_N);
                        addFlag(plane, x - 1, y, ClipFlag.BP_E);
                    }
                }
                default -> {
                }
            }
        }
        if (notAlternative) {
            switch (type) {
                case WALL_STRAIGHT -> {
                    if (rotation == 0) {
                        addFlag(plane, x, y, ClipFlag.PF_W);
                        addFlag(plane, x - 1, y, ClipFlag.PF_E);
                    }
                    if (rotation == 1) {
                        addFlag(plane, x, y, ClipFlag.PF_N);
                        addFlag(plane, x, y + 1, ClipFlag.PF_S);
                    }
                    if (rotation == 2) {
                        addFlag(plane, x, y, ClipFlag.PF_E);
                        addFlag(plane, x + 1, y, ClipFlag.PF_W);
                    }
                    if (rotation == 3) {
                        addFlag(plane, x, y, ClipFlag.PF_S);
                        addFlag(plane, x, y - 1, ClipFlag.PF_N);
                    }
                }
                case WALL_DIAGONAL_CORNER, WALL_STRAIGHT_CORNER -> {
                    if (rotation == 0) {
                        addFlag(plane, x, y, ClipFlag.PF_NW);
                        addFlag(plane, x - 1, y + 1, ClipFlag.PF_SE);
                    }
                    if (rotation == 1) {
                        addFlag(plane, x, y, ClipFlag.PF_NE);
                        addFlag(plane, x + 1, y + 1, ClipFlag.PF_SW);
                    }
                    if (rotation == 2) {
                        addFlag(plane, x, y, ClipFlag.PF_SE);
                        addFlag(plane, x + 1, y - 1, ClipFlag.PF_NW);
                    }
                    if (rotation == 3) {
                        addFlag(plane, x, y, ClipFlag.PF_SW);
                        addFlag(plane, x - 1, y - 1, ClipFlag.PF_NE);
                    }
                }
                case WALL_WHOLE_CORNER -> {
                    if (rotation == 0) {
                        addFlag(plane, x, y, ClipFlag.PF_N, ClipFlag.PF_W);
                        addFlag(plane, x - 1, y, ClipFlag.PF_E);
                        addFlag(plane, x, y + 1, ClipFlag.PF_S);
                    }
                    if (rotation == 1) {
                        addFlag(plane, x, y, ClipFlag.PF_N, ClipFlag.PF_E);
                        addFlag(plane, x, y + 1, ClipFlag.PF_S);
                        addFlag(plane, x + 1, y, ClipFlag.PF_W);
                    }
                    if (rotation == 2) {
                        addFlag(plane, x, y, ClipFlag.PF_E, ClipFlag.PF_S);
                        addFlag(plane, x + 1, y, ClipFlag.PF_W);
                        addFlag(plane, x, y - 1, ClipFlag.PF_N);
                    }
                    if (rotation == 3) {
                        addFlag(plane, x, y, ClipFlag.PF_S, ClipFlag.PF_W);
                        addFlag(plane, x, y - 1, ClipFlag.PF_N);
                        addFlag(plane, x - 1, y, ClipFlag.PF_E);
                    }
                }
                default -> {
                }
            }
        }
    }

    public void setFlag(int plane, int x, int y, int flag) {
        if (x >= 64 || y >= 64 || x < 0 || y < 0) {
            WorldTile tile = new WorldTile(regionX + x, regionY + y, plane);
            int regionId = tile.getRegionId();
            int newRegionX = (regionId >> 8) * 64;
            int newRegionY = (regionId & 0xff) * 64;
            if (projectile)
                Region.get(tile.getRegionId(), false).getClipMapProj().setFlag(plane, tile.getX() - newRegionX, tile.getY() - newRegionY, flag);
            else
                Region.get(tile.getRegionId(), false).getClipMap().setFlag(plane, tile.getX() - newRegionX, tile.getY() - newRegionY, flag);
            return;
        }
        flags[plane][x][y] = flag;
    }

    public void addFlag(int plane, int x, int y, int flag) {
        if (x >= 64 || y >= 64 || x < 0 || y < 0) {
            WorldTile tile = new WorldTile(regionX + x, regionY + y, plane);
            int regionId = tile.getRegionId();
            int newRegionX = (regionId >> 8) * 64;
            int newRegionY = (regionId & 0xff) * 64;
            if (projectile)
                Region.get(tile.getRegionId(), false).getClipMapProj().addFlag(plane, tile.getX() - newRegionX, tile.getY() - newRegionY, flag);
            else
                Region.get(tile.getRegionId(), false).getClipMap().addFlag(plane, tile.getX() - newRegionX, tile.getY() - newRegionY, flag);
            return;
        }
        flags[plane][x][y] |= flag;
    }

    public void removeFlag(int plane, int x, int y, int flag) {
        if (x >= 64 || y >= 64 || x < 0 || y < 0) {
            WorldTile tile = new WorldTile(regionX + x, regionY + y, plane);
            int regionId = tile.getRegionId();
            int newRegionX = (regionId >> 8) * 64;
            int newRegionY = (regionId & 0xff) * 64;
            if (projectile)
                Region.get(tile.getRegionId(), false).getClipMapProj().removeFlag(plane, tile.getX() - newRegionX, tile.getY() - newRegionY, flag);
            else
                Region.get(tile.getRegionId(), false).getClipMap().removeFlag(plane, tile.getX() - newRegionX, tile.getY() - newRegionY, flag);
            return;
        }
        flags[plane][x][y] &= ~flag;
    }

    void removeFlag(int plane, int x, int y, ClipFlag... flags) {
        int flag = 0;
        for (ClipFlag f : flags)
            flag |= f.flag;
        removeFlag(plane, x, y, flag);
    }

    void addFlag(int plane, int x, int y, ClipFlag... flags) {
        int flag = 0;
        for (ClipFlag f : flags)
            flag |= f.flag;
        addFlag(plane, x, y, flag);
    }

}
