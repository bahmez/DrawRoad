package fr.ender.drawroad.selector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class SelectSimulator {
	
	public static Map<Player, List<Vector>> selectSimulator = new HashMap<>();
	
	public static List<Vector> getSimulation(Player player) {
		return selectSimulator.get(player);
	}
	
	public static void setSimulation(Player player, List<Vector> points) {
		selectSimulator.put(player, points);
	}

}
