package fr.ender.drawroad.selector;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import fr.ender.drawroad.drawer.DrawLine;

public class Selector {
	
	public static Map<String, List<Vector>> selections = new HashMap<>();
	public static Map<String, Double> presicions = new HashMap<>();
	
	public static List<Vector> getSelections(Player player) {
		return selections.get(player.getDisplayName());
	}
	
	public static void clearSelections(Player player) {
		List<Vector> list = selections.get(player.getDisplayName());
		
		if (list == null) return;
		
		list.clear();
		SelectSimulator.setSimulation(player, null);
		changePrecision(player, 0);
	}
	
	public static void popSelection(Player player) {
		List<Vector> list = selections.get(player.getDisplayName());
		
		if (list == null) return;
		list.remove(list.size() - 1);
		SelectSimulator.setSimulation(player, DrawLine.getCurve(list, getPrecision(player)));
	}
	
	public static void changePrecision(Player player, double value) {
		List<Vector> list = selections.get(player.getDisplayName());
		
		presicions.put(player.getDisplayName(), value);
		if (list == null) return;
		SelectSimulator.setSimulation(player, DrawLine.getCurve(list, value));
	}
	
	public static double getPrecision(Player player) {
		Double value = presicions.get(player.getDisplayName());
		
		if (value == null)
			return 0;
		return value;
	}
	
	public static void addPoint(Player player, Location location) {
		List<Vector> list = selections.get(player.getDisplayName());
		
		if (list == null) {
			list = new LinkedList<>();
			selections.put(player.getDisplayName(), list);
			changePrecision(player, 0);
		}
		list.add(new Vector(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
		SelectSimulator.setSimulation(player, DrawLine.getCurve(list, getPrecision(player)));
	}

}
