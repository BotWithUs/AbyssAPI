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
package abyss;

import abyss.map.Region;
import abyss.map.WorldObject;
import abyss.pathing.EntityStrategy;
import abyss.pathing.FixedTileStrategy;
import abyss.pathing.LocalPathing;
import abyss.pathing.ObjectStrategy;
import abyss.plugin.api.*;
import abyss.plugin.api.world.WorldTile;

import java.nio.ByteBuffer;

public class Utils {

    public static int getUnsignedSmart(ByteBuffer buffer) {
        int i = buffer.get() & 0xff;
        if (i >= 0x80) {
            i -= 0x80;
            return i << 8 | (buffer.get() & 0xff);
        }
        return i;
    }

    public static int readSmartSizeVar(ByteBuffer stream) {
        int value = 0;
        int curr = getUnsignedSmart(stream);
        while (curr == 32767) {
            curr = getUnsignedSmart(stream);
            value += 32767;
        }
        value += curr;
        return value;
    }

    public static boolean isTileReachable(Vector3i tile) {
        if(tile == null)
            return false;
        Player self = Players.self();
        if(self == null)
            return false;
        return getRouteDistanceTo(self.getGlobalPosition(), WorldTile.Companion.toTile(tile)) != -1;
    }

    public static boolean isGroundItemReachable(GroundItem item) {
        if(item == null) {
            return false;
        }
        Player self = Players.self();
        if(self == null) {
            return false;
        }
        if(self.getGlobalPosition().getZ() != item.getGlobalPosition().getZ()) {
            return false;
        }
        return Utils.getRouteDistanceTo(self.getGlobalPosition(), item) != -1;
    }

    public static boolean isNpcReachable(Npc npc) {
        if(npc == null) {
            return false;
        }
        Player self = Players.self();
        if(self == null) {
            return false;
        }
        if(self.getGlobalPosition().getZ() != npc.getGlobalPosition().getZ()) {
            return false;
        }
        return Utils.getRouteDistanceTo(self.getGlobalPosition(), npc) != -1;
    }

    public static boolean isSceneObjectReachable(SceneObject so) {
        if(so == null) {
            return false;
        }
        Player self = Players.self();
        if(self == null) {
            return false;
        }
        if(self.getGlobalPosition().getZ() != so.getGlobalPosition().getZ()) {
            return false;
        }
        Vector3i pos = so.getGlobalPosition();
        Region region = Region.get(pos.getRegionId());
        WorldObject obj = null;
        for (WorldObject wo : region.objects[pos.getZ()][pos.getXInRegion()][pos.getYInRegion()]) {
            if(wo != null && wo.getId() == so.getId()) {
                obj = wo;
                break;
            }
        }
        if(obj == null) {
            return false;
        }
        int steps = getRouteDistanceTo(Players.self().getGlobalPosition(), obj);
        return steps != -1;
    }

    public static int getRouteDistanceTo(Vector3i start, GroundItem item) {
        return LocalPathing.getLocalStepsTo(start, 1, new FixedTileStrategy(item.getGlobalPosition()), false);
    }

    public static int getRouteDistanceTo(Vector3i start, WorldObject object) {
        return LocalPathing.getLocalStepsTo(start, 1, new ObjectStrategy(object), false);
    }

    public static int getRouteDistanceTo(Vector3i start, Npc npc) {
        return LocalPathing.getLocalStepsTo(start, 1, new EntityStrategy(npc), false);
    }

    public static int getRouteDistanceTo(Vector3i start, WorldTile tile) {
        return LocalPathing.getLocalStepsTo(start, 1, new FixedTileStrategy(tile), false);
    }

    public static int getDistanceTo(Vector3i start, Vector3i destination) {
        return start.distance(destination);
    }

    public static int larger(int v1, int v2) {
        return Math.max(v1, v2);
    }
}
