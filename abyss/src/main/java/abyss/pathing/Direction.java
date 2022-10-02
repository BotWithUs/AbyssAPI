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
package abyss.pathing;

public enum Direction {
	NORTH(0, 0, 1),
	NORTHEAST(1, 1, 1),
	EAST(2, 1, 0),
	SOUTHEAST(3, 1, -1),
	SOUTH(4, 0, -1),
	SOUTHWEST(5, -1, -1),
	WEST(6, -1, 0),
	NORTHWEST(7, -1, 1);
	
	private int id;
	private int dx;
	private int dy;
	
	private Direction(int id, int dx, int dy) {
		this.id = id;
		this.dx = dx;
		this.dy = dy;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isDiagonal() {
		return dx != 0 && dy != 0;
	}
	
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}

	public static Direction getById(int id) {
		return switch (id) {
			case 0 -> NORTH;
			case 1 -> NORTHEAST;
			case 2 -> EAST;
			case 3 -> SOUTHEAST;
			case 4 -> SOUTH;
			case 5 -> SOUTHWEST;
			case 6 -> WEST;
			case 7 -> NORTHWEST;
			default -> SOUTH;
		};
	}
	
	public static Direction forDelta(int dx, int dy) {
		if (dy >= 1 && dx >= 1) {
			return NORTHEAST;
		} else if (dy <= -1 && dx >= 1) {
			return SOUTHEAST;
		} else if (dy <= -1 && dx <= -1) {
			return SOUTHWEST;
		} else if (dy >= 1 && dx <= -1) {
			return NORTHWEST;
		} else if (dy >= 1) {
			return NORTH;
		} else if (dx >= 1) {
			return EAST;
		} else if (dy <= -1) {
			return SOUTH;
		} else if (dx <= -1) {
			return WEST;
		} else {
			return null;
		}
	}
}
