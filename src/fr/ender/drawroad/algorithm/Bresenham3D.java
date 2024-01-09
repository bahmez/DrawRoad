package fr.ender.drawroad.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.util.Vector;

public class Bresenham3D {
    
	public static List<Vector> bresenham(int x1, int y1, int x2, int y2) {
		List<Vector> points = new ArrayList<>();
		int dx = x2 - x1;
		int dy = y2 - y1;
		int dx1 = Math.abs(dx);
		int dy1 = Math.abs(dy);
		int px = 2 * dy1 - dx1;
		int py = 2 * dx1 - dy1;
		
		int x, y, xe, ye;
		
		if (dy1 <= dx1) {
			if (dx >= 0) {
				x = x1;
				y = y1;
				xe = x2;
			} else {
				x = x2;
				y = y2;
				xe = x1;
			}
			points.add(new Vector(x, 0, y));
			while (x < xe) {
				x = x + 1;
				if (px < 0) {
					px = px + 2 * dy1;
				} else {
					if ((dx < 0 && dy < 0) || (dx > 0 && dy > 0)) {
	                    y = y + 1;
	                } else {
	                    y = y - 1;
	                }
	                px = px + 2 * (dy1 - dx1);
				}
				points.add(new Vector(x, 0, y));
			}
		} else {
	        if (dy >= 0) {
	            x = x1; y = y1; ye = y2;
	        } else {
	            x = x2; y = y2; ye = y1;
	        }
	        points.add(new Vector(x, 0, y));
	        while (y < ye) {
	            y = y + 1;
	            if (py <= 0) {
	                py = py + 2 * dx1;
	            } else {
	                if ((dx < 0 && dy<0) || (dx > 0 && dy > 0)) {
	                    x = x + 1;
	                } else {
	                    x = x - 1;
	                }
	                py = py + 2 * (dx1 - dy1);
	            }
	            points.add(new Vector(x, 0, y));
	        }
	    }
		return points;
	}
	
	public static List<Vector> bresenham3D(int x1, int y1, int z1, int x2, int y2, int z2) {
	    List<Vector> ListOfPoints = new ArrayList<>();
	    ListOfPoints.add(new Vector(x1, y1, z1));
	    int dx = Math.abs(x2 - x1);
	    int dy = Math.abs(y2 - y1);
	    int dz = Math.abs(z2 - z1);
	    int xs;
	    int ys;
	    int zs;
	    if (x2 > x1) {
	      xs = 1;
	    } else {
	      xs = -1;
	    }
	    if (y2 > y1) {
	      ys = 1;
	    } else {
	      ys = -1;
	    }
	    if (z2 > z1) {
	      zs = 1;
	    } else {
	      zs = -1;
	    }
	 
	    // Driving axis is X-axis"
	    if (dx >= dy && dx >= dz) {
	      int p1 = 2 * dy - dx;
	      int p2 = 2 * dz - dx;
	      while (x1 != x2) {
	        x1 += xs;
	        if (p1 >= 0) {
	          y1 += ys;
	          p1 -= 2 * dx;
	        }
	        if (p2 >= 0) {
	          z1 += zs;
	          p2 -= 2 * dx;
	        }
	        p1 += 2 * dy;
	        p2 += 2 * dz;
	        ListOfPoints.add(new Vector(x1, y1, z1));
	      }
	 
	      // Driving axis is Y-axis"
	    } else if (dy >= dx && dy >= dz) {
	      int p1 = 2 * dx - dy;
	      int p2 = 2 * dz - dy;
	      while (y1 != y2) {
	        y1 += ys;
	        if (p1 >= 0) {
	          x1 += xs;
	          p1 -= 2 * dy;
	        }
	        if (p2 >= 0) {
	          z1 += zs;
	          p2 -= 2 * dy;
	        }
	        p1 += 2 * dx;
	        p2 += 2 * dz;
	        ListOfPoints.add(new Vector(x1, y1, z1));
	      }
	 
	      // Driving axis is Z-axis"
	    } else {
	      int p1 = 2 * dy - dz;
	      int p2 = 2 * dx - dz;
	      while (z1 != z2) {
	        z1 += zs;
	        if (p1 >= 0) {
	          y1 += ys;
	          p1 -= 2 * dz;
	        }
	        if (p2 >= 0) {
	          x1 += xs;
	          p2 -= 2 * dz;
	        }
	        p1 += 2 * dy;
	        p2 += 2 * dx;
	        ListOfPoints.add(new Vector(x1, y1, z1));
	      }
	    }
	    return ListOfPoints;
	  }

}
