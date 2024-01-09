package fr.ender.drawroad.drawer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import fr.ender.drawroad.algorithm.BezierCurve3D;
import fr.ender.drawroad.algorithm.Bresenham3D;
import fr.ender.drawroad.builder.AsyncBuilder;
import fr.ender.drawroad.builder.BlockBuilder;
import fr.ender.drawroad.pattern.BlockPattern;
import fr.ender.drawroad.pattern.PatternFactory;
import fr.ender.drawroad.selector.Selector;
import fr.ender.drawroad.undo.UndoWriter;

public class DrawLine {
	
	public static List<Vector> getLine3D(Vector a, Vector b) {
		List<Vector> pointsList = Bresenham3D.bresenham3D(a.getBlockX(), a.getBlockY(), a.getBlockZ(), b.getBlockX(), b.getBlockY(), b.getBlockZ());

		return pointsList;
	}
	
	public static List<Vector> getCurve(List<Vector> points, double precision) {
		return BezierCurve3D.bezierCurve(points, precision);
	}
	
//	private static boolean getContainsListVector(List<Vector> list, Vector comparePoint) {
//		for (Vector point : list) {
//			if (point.getX() == comparePoint.getX()
//					&& point.getY() == comparePoint.getY()
//					&& point.getZ() == comparePoint.getZ())
//				return true;
//		}
//		return false;
//	}
	
	public static boolean drawRoad(List<Vector> points, Player player) throws IOException {
		List<Vector> newPoints = BezierCurve3D.bezierCurve(points, Selector.getPrecision(player));
		List<Vector> finalPoints = new ArrayList<>();
		List<List<BlockPattern>> patterns = PatternFactory.getPattern(player);
		World world = player.getWorld();

		if (patterns == null)
			return false;
		UndoWriter writer = new UndoWriter(UndoWriter.generateName(), false);
		for (int i = 1; i < newPoints.size(); i++) {
			List<Vector> pointsList = getLine3D(newPoints.get(i - 1), newPoints.get(i));
	
			finalPoints.addAll(pointsList);
		}
		for (int i = patterns.size() - 1; i >= 0; i--) {
			for (Vector point : finalPoints) {
				List<BlockPattern> listBlocks = patterns.get(i);
				List<Vector> circlePoints = DrawCircle.drawCircle(point.clone(), i);
				
				AsyncBuilder.waitingList.push(() -> {
					for (Vector circlePoint : circlePoints) {
						for (int j = 0; j < 5; j++) {
							if (listBlocks.size() <= j) {
								Vector fakePointVector = circlePoint.clone().setY(circlePoint.getY() + j);
								writer.write(new BlockBuilder(world.getBlockAt(fakePointVector.toLocation(world))));
								BlockPattern.EMPTY.placeBlockInWorld(fakePointVector.toLocation(world));
							} else {				
								Vector fakePointVector = circlePoint.clone();
								Location lastLocation = fakePointVector.toLocation(world);
								lastLocation.add(0, listBlocks.get(j).getY(), 0);
								writer.write(new BlockBuilder(world.getBlockAt(lastLocation)));
								listBlocks.get(j).setX(0);
								listBlocks.get(j).placeBlockInWorld(fakePointVector.toLocation(world));
							}
						}		
					}		
				});
			}
		}
		AsyncBuilder.waitingList.push(() -> {
			try {
				writer.close();
				player.sendMessage("Success construction - road's name : " + writer.filename);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return true;
	}

}
