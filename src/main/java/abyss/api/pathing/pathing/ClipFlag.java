package abyss.api.pathing.pathing;

import java.util.ArrayList;

/*
public static final int FLOOR_BLOCKSWALK = 0x200000;
    public static final int FLOORDECO_BLOCKSWALK = 0x40000;

    public static final int OBJ = 0x100;
    public static final int OBJ_BLOCKSFLY = 0x20000;
    public static final int OBJ_BLOCKSWALK_ALTERNATIVE = 0x40000000;

    public static final int WALLOBJ_NORTH = 0x2;
    public static final int WALLOBJ_EAST = 0x8;
    public static final int WALLOBJ_SOUTH = 0x20;
    public static final int WALLOBJ_WEST = 0x80;

    public static final int CORNEROBJ_NORTHWEST = 0x1;
    public static final int CORNEROBJ_NORTHEAST = 0x4;
    public static final int CORNEROBJ_SOUTHEAST = 0x10;
    public static final int CORNEROBJ_SOUTHWEST = 0x40;

    public static final int WALLOBJ_NORTH_BLOCKSFLY = 0x400;
    public static final int WALLOBJ_EAST_BLOCKSFLY = 0x1000;
    public static final int WALLOBJ_SOUTH_BLOCKSFLY = 0x4000;
    public static final int WALLOBJ_WEST_BLOCKSFLY = 0x10000;

    public static final int CORNEROBJ_NORTHWEST_BLOCKSFLY = 0x200;
    public static final int CORNEROBJ_NORTHEAST_BLOCKSFLY = 0x800;
    public static final int CORNEROBJ_SOUTHEAST_BLOCKSFLY = 0x2000;
    public static final int CORNEROBJ_SOUTHWEST_BLOCKSFLY = 0x8000;

    public static final int WALLOBJ_NORTH_BLOCKSWALK_ALTERNATIVE = 0x800000;
    public static final int WALLOBJ_EAST_BLOCKSWALK_ALTERNATIVE = 0x2000000;
    public static final int WALLOBJ_SOUTH_BLOCKSWALK_ALTERNATIVE = 0x8000000;
    public static final int WALLOBJ_WEST_BLOCKSWALK_ALTERNATIVE = 0x20000000;

    public static final int CORNEROBJ_NORTHWEST_BLOCKSWALK_ALTERNATIVE = 0x400000;
    public static final int CORNEROBJ_NORTHEAST_BLOCKSWALK_ALTERNATIVE = 0x1000000;
    public static final int CORNEROBJ_SOUTHEAST_BLOCKSWALK_ALTERNATIVE = 0x4000000;
    public static final int CORNEROBJ_SOUTHWEST_BLOCKSWALK_ALTERNATIVE = 0x10000000;
 */

public enum ClipFlag {
	EMPTY(0xFFFFFFFF), // -1

	BW_NW(0x1), // 1
	BW_N(0x2), // 2
	BW_NE(0x4), // 4
	BW_E(0x8), // 8
	BW_SE(0x10), // 16
	BW_S(0x20), // 32
	BW_SW(0x40), // 64
	BW_W(0x80), // 128
	BW_FULL(0x100), // 256

	BP_NW(0x200), // 512
	BP_N(0x400), // 1024
	BP_NE(0x800), // 2048
	BP_E(0x1000), // 4096
	BP_SE(0x2000), // 8192
	BP_S(0x4000), // 16384
	BP_SW(0x8000), // 32768
	BP_W(0x10000), // 65536
	BP_FULL(0x20000), // 131072
	
	PFBW_GROUND_DECO(0x40000), // 262144
	PFBW_FLOOR(0x200000), // 2097152

	PF_NW(0x400000), // 4194304
	PF_N(0x800000), // 8388608
	PF_NE(0x1000000), // 16777216
	PF_E(0x2000000), // 33554432
	PF_SE(0x4000000), // 67108864
	PF_S(0x8000000), // 134217728
	PF_SW(0x10000000), // 268435456
	PF_W(0x20000000), // 536870912
	PF_FULL(0x40000000); // 1073741824

	public final int flag;

	ClipFlag(int flag) {
		this.flag = flag;
	}

	public static ClipFlag[] values = values();

	public static ArrayList<ClipFlag> getFlags(int value) {
		ArrayList<ClipFlag> flags = new ArrayList<>();
		for (ClipFlag f : ClipFlag.values()) {
			if ((value & f.flag) != 0 && f != ClipFlag.EMPTY)
				flags.add(f);
		}
		return flags;
	}

	public static boolean flagged(int value, ClipFlag... flags) {
		int flag = 0;
		for (ClipFlag f : flags)
			flag |= f.flag;
		return (value & flag) != 0;
	}
	
	public static int or(ClipFlag... flags) {
		int flag = 0;
		for (ClipFlag f : flags)
			flag |= f.flag;
		return flag;
	}
}