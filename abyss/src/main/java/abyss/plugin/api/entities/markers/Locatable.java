package abyss.plugin.api.entities.markers;

import abyss.Utils;
import abyss.map.Region;
import abyss.plugin.api.Area3di;
import abyss.plugin.api.Players;
import abyss.plugin.api.Vector3i;

import java.util.*;

@FunctionalInterface
public interface Locatable {

    private static boolean hasFlags(int flags, int[] flagOffsets) {
        for (int offset : flagOffsets) {
            if (((flags >> offset) & 1) == 1) {
                return true;
            }
        }
        return false;
    }

    Vector3i getGlobalPosition();

    default Area3di getGlobalArea() {
        Vector3i position = getGlobalPosition();
        return new Area3di(position, position);
    }

    default Collection<Vector3i> getReachableTiles() {
        Vector3i root = getGlobalPosition();
        if (root == null) {
            return Collections.emptyList();
        }
        //TODO Switch it to all loaded regions
        Region region = Region.get(root.getRegionId());
        int[][][] all_flags = region.getStandardCollisionMap().getFlags();
        final Set<Vector3i> results = new HashSet<>(512);
        if (all_flags != null) {
            //Should be a priority queue but this implementation was quicker to write for now.
            final Queue<Vector3i> queue = new LinkedList<>(Collections.singleton(root));
            while (!queue.isEmpty()) {
                final Vector3i next = queue.poll();
                final int flags = all_flags[root.getZ()][root.getXInRegion()][root.getYInRegion()];
                if (hasFlags(flags, ClippingFlagOffsets.OBJECTS) || results.contains(next)) {
                    continue;
                }
                results.add(next);
                if (!hasFlags(flags, ClippingFlagOffsets.NORTH)) {
                    queue.offer(next.derive(0, 1, 0));
                }
                if (!hasFlags(flags, ClippingFlagOffsets.EAST)) {
                    queue.offer(next.derive(1, 0, 0));
                }
                if (!hasFlags(flags, ClippingFlagOffsets.SOUTH)) {
                    queue.offer(next.derive(0, -1, 0));
                }
                if (!hasFlags(flags, ClippingFlagOffsets.WEST)) {
                    queue.offer(next.derive(-1, 0, 0));
                }
            }
        }
        return results;
    }

    interface ClippingFlagOffsets {

        /**
         * All north boundary flag offsets
         */
        int[] NORTH = {1, 10, 23};

        /**
         * All east boundary flag offsets
         */
        int[] EAST = {3, 12, 25};

        /**
         * All south boundary flag offsets
         */
        int[] SOUTH = {5, 14, 27};

        /**
         * All west boundary flag offsets
         */
        int[] WEST = {7, 16, 29};

        /**
         * All north-east boundary flag offsets
         */
        int[] NORTH_EAST = {2, 11, 24};

        /**
         * All north-west boundary flag offsets
         */
        int[] NORTH_WEST = {0, 9, 22};

        /**
         * All south-east boundary flag offsets
         */
        int[] SOUTH_EAST = {4, 13, 26};

        /**
         * All south-west boundary flag offsets
         */
        int[] SOUTH_WEST = {6, 15, 28};

        /**
         * All object boundary flag offsets
         */
        int[] OBJECTS = {8, 17, 18, 21};
    }
}