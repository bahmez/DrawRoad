package fr.ender.drawroad.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.util.Vector;

public class BezierCurve3D {
	
	private static double factorial(int n) {
		if (n < 0)
			return (-1);
		if (n == 0)
			return 1;
		else
			return (n * factorial(n - 1));
	}
	
	private static double nCr(int n, int r) {
		return (factorial(n) / (factorial(r) * factorial(n - r)));
	}

	public static List<Vector> bezierCurve(List<Vector> points, double precision) {
		int n = points.size();
		List<Vector> curvePoints = new ArrayList<>();
		double diff = precision;

		if (diff <= 0 || diff >= 1) {
			diff = (0.1 - (n * 0.005));
			diff = (diff <= 0) ? 0.001 : diff;
		}
		for (double u = 0; u <= 1; u += diff) {
			Vector p = new Vector(0, 0, 0);
			
			for (int i = 0; i < n; i++) {
				double B = nCr(n-1,i) * Math.pow((1 - u), (n - 1) - i) * Math.pow(u, i);
				double px = points.get(i).getX() * B;
				double py = points.get(i).getY() * B;
				double pz = points.get(i).getZ() * B;
				
				p.setX(p.getX() + px);
				p.setY(p.getY() + py);
				p.setZ(p.getZ() + pz);
			}
			p.setX(Math.round(p.getX()));
			p.setY(Math.round(p.getY()));
			p.setZ(Math.round(p.getZ()));
			curvePoints.add(p);
		}
		return curvePoints;
	}
}
