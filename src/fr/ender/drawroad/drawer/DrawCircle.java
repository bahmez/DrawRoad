package fr.ender.drawroad.drawer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.util.Vector;

public class DrawCircle {
	
	public static List<Vector> drawCircle(Vector point, int r) {
        List<Vector> points = new ArrayList<Vector>();
        int x = 0;
        int y = r;
        int d = 3 - 2 * r;

        while (y >= x) {
            points.add(new Vector(point.getX() + x, point.getY(), point.getZ() + y));
            points.add(new Vector(point.getX() + y, point.getY(), point.getZ() + x));
            points.add(new Vector(point.getX() - x, point.getY(), point.getZ() + y));
            points.add(new Vector(point.getX() - y, point.getY(), point.getZ() + x));
            points.add(new Vector(point.getX() + x, point.getY(), point.getZ() - y));
            points.add(new Vector(point.getX() + y, point.getY(), point.getZ() - x));
            points.add(new Vector(point.getX() - x, point.getY(), point.getZ() - y));
            points.add(new Vector(point.getX() - y, point.getY(), point.getZ() - x));

            x += 1;
            if (d > 0) {
                y -= 1;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }
        }

        return points;
    }

}
