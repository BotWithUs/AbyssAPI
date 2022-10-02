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

import abyss.Utils;
import abyss.pathing.ObjectType;
import abyss.plugin.api.Cache;
import abyss.plugin.api.CacheObject;
import abyss.plugin.api.Debug;
import abyss.plugin.api.world.WorldTile;
import com.rshub.filesystem.Archive;
import com.rshub.filesystem.ArchiveFile;
import com.rshub.filesystem.Filesystem;
import com.rshub.filesystem.ReferenceTable;
import com.rshub.filesystem.sqlite.SqliteFilesystem;

import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.*;

public class Region {

    private final static Filesystem FS = new SqliteFilesystem(Paths.get("C:\\ProgramData\\Jagex\\RuneScape"));

    public static final int OBJECTS = 0, UNDERWATER = 1, NPCS = 2, TILES = 3, WATER_TILES = 4;
    private static Map<Integer, Region> REGIONS = new HashMap<>();

    private final int regionId;
    private ClipMap clipMap;
    private ClipMap clipMapProj;

    public WorldObject[][][][] objects;
    public List<WorldObject> objectList;
    public int[][][] overlayIds;
    public int[][][] underlayIds;
    public byte[][][] overlayPathShapes;
    public byte[][][] overlayRotations;
    public byte[][][] tileFlags;
    private boolean loaded = false;

    public Region(int regionId, boolean load) {
        this.regionId = regionId;
        if (load)
            load();
    }

    public Region(int regionId) {
        this(regionId, true);
    }

    public void load() {
        int regionX = regionId >> 8;
        int regionY = regionId & 0xff;
        // System.out.println("Loading region " + regionId + "(" + regionX + ", " +
        // regionY + ")");
        ReferenceTable table = FS.getReferenceTable(5, false);
        if(table == null) {
            return;
        }
        Archive archive = table.loadArchive(regionX | regionY << 7);
        if (archive == null) {
            return;
        }
        ArchiveFile objects = archive.getFiles().get(OBJECTS);
        ArchiveFile tiles = archive.getFiles().get(TILES);
        if (objects != null)
            decodeObjectData(objects);
        if (tiles != null)
            decodeTileData(tiles);
        loaded = true;
    }

    private void decodeTileData(ArchiveFile file) {
        byte[] data = file.getData();
        if (data.length == 0)
            return;
        ByteBuffer stream = ByteBuffer.wrap(data);
        overlayIds = new int[4][64][64];
        underlayIds = new int[4][64][64];
        overlayPathShapes = new byte[4][64][64];
        overlayRotations = new byte[4][64][64];
        tileFlags = new byte[4][64][64];

        for (int plane = 0; plane < 4; plane++) {
            for (int x = 0; x < 64; x++) {
                for (int y = 0; y < 64; y++) {
                    int flags = stream.get() & 0xff;

                    if ((flags & 0x10) != 0)
                        System.err.println("Flag 0x10 found for tile (" + x + ", " + y + ", " + plane + ") at region " + regionId);
                    if ((flags & 0x20) != 0)
                        System.err.println("Flag 0x20 found for tile (" + x + ", " + y + ", " + plane + ") at region " + regionId);
                    if ((flags & 0x40) != 0)
                        System.err.println("Flag 0x40 found for tile (" + x + ", " + y + ", " + plane + ") at region " + regionId);
                    if ((flags & 0x80) != 0)
                        System.err.println("Flag 0x80 found for tile (" + x + ", " + y + ", " + plane + ") at region " + regionId);

                    if ((flags & 0x1) != 0) {
                        int shapeHash = stream.get() & 0xff;
                        overlayIds[plane][x][y] = getUnsignedSmart(stream);
                        overlayPathShapes[plane][x][y] = (byte) (shapeHash >> 2);
                        overlayRotations[plane][x][y] = (byte) (shapeHash & 0x3);
                    }
                    if ((flags & 0x2) != 0) {
                        tileFlags[plane][x][y] = (byte) (stream.get() & 0xff);
                    }
                    if ((flags & 0x4) != 0) {
                        underlayIds[plane][x][y] = Utils.getUnsignedSmart(stream);
                    }
                    if ((flags & 0x8) != 0) {
                        // tile heights (unsigned)
                        stream.get();
                    }
                }
            }
        }
        for (int plane = 0; plane < 4; plane++) {
            for (int x = 0; x < 64; x++) {
                for (int y = 0; y < 64; y++) {
                    if (RenderFlag.flagged(tileFlags[plane][x][y], RenderFlag.CLIPPED)) {
                        int finalPlane = plane;
                        if (RenderFlag.flagged(tileFlags[1][x][y], RenderFlag.LOWER_OBJECTS_TO_OVERRIDE_CLIPPING))
                            finalPlane--;
                        if (finalPlane >= 0) {
                            getClipMap().addBlockedTile(finalPlane, x, y);
                        }
                    }
                }
            }
        }
//		byte[] remaining = new byte[stream.remaining()];
//		stream.get(remaining);
//		System.out.println("Done parsing tile data... bytes not read: " + remaining.length + " " + Arrays.toString(remaining));
    }

    @SuppressWarnings("unused")
    private void decodeObjectData(ArchiveFile file) {
        try {
            int regionX = regionId >> 8;
            int regionY = regionId & 0xff;
            byte[] data = file.getData();
            if (data.length == 0)
                return;
            ByteBuffer stream = ByteBuffer.wrap(data);
            int objectId = -1;
            int incr;
            while ((incr = Utils.readSmartSizeVar(stream)) != 0) {
                objectId += incr;
                int location = 0;
                int incr2;
                while ((incr2 = Utils.getUnsignedSmart(stream)) != 0) {
                    location += incr2 - 1;
                    int localX = (location >> 6 & 0x3f);
                    int localY = (location & 0x3f);
                    int plane = location >> 12;
                    int objectData = stream.get() & 0xff;
                    boolean flag = (objectData & 0x80) != 0;
                    int type = objectData >> 2 & 0x1f;
                    int rotation = objectData & 0x3;
                    int metaDataFlag = 0;
                    if (flag) {
                        metaDataFlag = stream.get() & 0xff;
//						if (metaDataFlag != 0)
//							System.err.println("Metadata flag: " + metaDataFlag);
                        if ((metaDataFlag & 0x1) != 0) {
                            float f1 = 0.0F, f2 = 0.0F, f3 = 0.0F, f4 = 1.0F;
                            f1 = (float) stream.getShort() / 32768.0F;
                            f2 = (float) stream.getShort() / 32768.0F;
                            f3 = (float) stream.getShort() / 32768.0F;
                            f4 = (float) stream.getShort() / 32768.0F;
//							System.err.println("4 float flag: " + f1 + ", " + f2 + ", " + f3 + ", " + f4);
                        }
                        float f1 = 0.0f, f2 = 0.0f, f3 = 0.0f;
                        boolean print = false;
                        if ((metaDataFlag & 0x2) != 0) {
                            f1 = stream.getShort();
                            print = true;
                        }
                        if ((metaDataFlag & 0x4) != 0) {
                            f2 = stream.getShort();
                            print = true;
                        }
                        if ((metaDataFlag & 0x8) != 0) {
                            f3 = stream.getShort();
                            print = true;
                        }
//						if (print)
//							System.err.println("3 float flag: " + f1 + ", " + f2 + ", " + f3);

                        f1 = f2 = f3 = 1.0f;
                        print = false;
                        if ((metaDataFlag & 0x10) != 0) {
                            f1 = f2 = f3 = stream.getShort();
                            print = true;
                        } else {
                            if ((metaDataFlag & 0x20) != 0) {
                                f1 = stream.getShort();
                                print = true;
                            }
                            if ((metaDataFlag & 0x40) != 0) {
                                f2 = stream.getShort();
                                print = true;
                            }
                            if ((metaDataFlag & 0x80) != 0) {
                                f3 = stream.getShort();
                                print = true;
                            }
                        }
//						if (print)
//							System.err.println("3 float flag 2: " + f1 + ", " + f2 + ", " + f3);
                    }
                    int objectPlane = plane;
                    if (tileFlags != null && (tileFlags[1][localX][localY] & 0x2) != 0)
                        objectPlane--;
                    if (objectPlane < 0 || objectPlane >= 4 || plane < 0 || plane >= 4)
                        continue;
//					if (flag)
//						System.out.println(new WorldObject(objectId, ObjectType.forId(type), rotation, localX + regionX * 64, localY + regionY * 64, objectPlane) + ", " + metaDataFlag + ", " + tileFlags[plane][localX][localY]);
                    if (ObjectType.forId(type) == null) {
                        Debug.log(new WorldObject(objectId, ObjectType.forId(type), rotation, localX + regionX * 64, localY + regionY * 64, objectPlane).toString());
                        Debug.log("Invalid object type: " + type + ", " + objectData + " obj: " + Cache.getObject(objectId));
                        continue;
                    }
                    WorldObject obj = new WorldObject(objectId, ObjectType.forId(type), rotation, localX + regionX * 64, localY + regionY * 64, objectPlane);
                    spawnObject(obj, objectPlane, localX, localY);
                }
            }
        } catch (Exception e) {
            Debug.printStackTrace(e.getCause().getMessage(), e);
            tileFlags = null;
            objects = null;
        }
    }

    public void spawnObject(WorldObject obj, int plane, int localX, int localY) {
        if (objects == null)
            objects = new WorldObject[4][64][64][4];
        if (objectList == null)
            objectList = new ArrayList<>();
        objectList.add(obj);
        objects[plane][localX][localY][obj.getSlot()] = obj;
        clip(obj, localX, localY);
    }

    public void clip(WorldObject object, int x, int y) {
        if (object.getId() == -1)
            return;
        if (clipMap == null)
            clipMap = new ClipMap(regionId, false);
        if (clipMapProj == null)
            clipMapProj = new ClipMap(regionId, true);
        int plane = object.getZ();
        ObjectType type = object.getType();
        int rotation = object.getRotation();
        if (x < 0 || y < 0 || x >= clipMap.getMasks()[plane].length || y >= clipMap.getMasks()[plane][x].length)
            return;
        CacheObject defs = object.getDef();

        if (defs.getClipType() == 0)
            return;

        switch (type) {
            case WALL_STRAIGHT:
            case WALL_DIAGONAL_CORNER:
            case WALL_WHOLE_CORNER:
            case WALL_STRAIGHT_CORNER:
                clipMap.addWall(plane, x, y, type, rotation, defs.isBlocksProjectile(), !defs.isIgnoreAltClip());
                if (defs.isBlocksProjectile())
                    clipMapProj.addWall(plane, x, y, type, rotation, defs.isBlocksProjectile(), !defs.isIgnoreAltClip());
                break;
            case WALL_INTERACT:
            case SCENERY_INTERACT:
            case GROUND_INTERACT:
            case STRAIGHT_SLOPE_ROOF:
            case DIAGONAL_SLOPE_ROOF:
            case DIAGONAL_SLOPE_CONNECT_ROOF:
            case STRAIGHT_SLOPE_CORNER_CONNECT_ROOF:
            case STRAIGHT_SLOPE_CORNER_ROOF:
            case STRAIGHT_FLAT_ROOF:
            case STRAIGHT_BOTTOM_EDGE_ROOF:
            case DIAGONAL_BOTTOM_EDGE_CONNECT_ROOF:
            case STRAIGHT_BOTTOM_EDGE_CONNECT_ROOF:
            case STRAIGHT_BOTTOM_EDGE_CONNECT_CORNER_ROOF:
                int sizeX;
                int sizeY;
                if (rotation != 1 && rotation != 3) {
                    sizeX = defs.getSizeX();
                    sizeY = defs.getSizeY();
                } else {
                    sizeX = defs.getSizeY();
                    sizeY = defs.getSizeX();
                }
                clipMap.addObject(plane, x, y, sizeX, sizeY, defs.isBlocksProjectile(), !defs.isIgnoreAltClip());
                if (defs.getClipType() != 0)
                    clipMapProj.addObject(plane, x, y, sizeX, sizeY, defs.isBlocksProjectile(), !defs.isIgnoreAltClip());
                break;
            case GROUND_DECORATION:
                if (defs.getClipType() == 1)
                    clipMap.addBlockWalkAndProj(plane, x, y);
                break;
            default:
                break;
        }
    }

    public static Region get(int regionId) {
        return get(regionId, true);
    }

    public static Region get(int regionId, boolean load) {
        Region region = REGIONS.get(regionId);
        if (region == null) {
            region = new Region(regionId, load);
            REGIONS.put(regionId, region);
        }
        if (load && !region.loaded)
            region.load();
        return region;
    }

    public ClipMap getClipMap() {
        if (clipMap == null)
            clipMap = new ClipMap(regionId, false);
        return clipMap;
    }

    public ClipMap getClipMapProj() {
        if (clipMapProj == null)
            clipMapProj = new ClipMap(regionId, true);
        return clipMapProj;
    }

    public List<WorldObject> getObjectList() {
        return objectList;
    }

    public static boolean validateObjCoords(WorldObject object) {
        Region region = Region.get(object.getRegionId());
        List<WorldObject> realObjects = region.getObjectList();
        if (realObjects == null || realObjects.size() <= 0)
            return false;
        Map<Integer, WorldObject> distanceMap = new TreeMap<Integer, WorldObject>();
        for (WorldObject real : realObjects) {
            if (object.getZ() != real.getZ() || real.getId() != object.getId())
                continue;
            int distance = Utils.getDistanceTo(object, real);
            if (distance != -1)
                distanceMap.put(distance, real);
        }
        if (distanceMap.isEmpty())
            return false;
        List<Integer> sortedKeys = new ArrayList<Integer>(distanceMap.keySet());
        Collections.sort(sortedKeys);
        WorldObject closest = distanceMap.get(sortedKeys.get(0));
        if (Utils.getDistanceTo(object, closest) <= Utils.larger(object.getDef().getSizeX(), object.getDef().getSizeY())) {
            object.setX(closest.getX());
            object.setY(closest.getY());
            object.setZ(closest.getZ());
            return true;
        }
        return false;
    }

    public static int getClip(WorldTile tile) {
        return Region.get(tile.getRegionId()).getClipMap().getMasks()[tile.getZ()][tile.getXInRegion()][tile.getYInRegion()];
    }



    public static int getUnsignedSmart(ByteBuffer buffer) {
        int i = buffer.get() & 0xff;
        if (i >= 0x80) {
            i -= 0x80;
            return i << 8 | (buffer.get() & 0xff);
        }
        return i;
    }

    public static int getSmartInt(ByteBuffer buffer) {
        if (buffer.get(buffer.position()) < 0)
            return buffer.getInt() & 0x7fffffff;
        return buffer.getShort() & 0xffff;
    }

    public static String getString(ByteBuffer buffer) {
        int origPos = buffer.position();
        int length = 0;
        while (buffer.get() != 0)
            length++;
        if (length == 0)
            return "";
        byte[] byteArray = new byte[length];
        buffer.position(origPos);
        buffer.get(byteArray);
        buffer.position(buffer.position() + 1);
        return new String(byteArray);
    }

    public static void skip(ByteBuffer buffer, int bytes) {
        buffer.position(buffer.position() + bytes);
    }

    public static int getSmallSmartInt(ByteBuffer buffer) {
        if ((buffer.get(buffer.position()) & 0xff) < 128)
            return buffer.get() & 0xff;
        return (buffer.getShort() & 0xffff) - 0x8000;
    }
}
