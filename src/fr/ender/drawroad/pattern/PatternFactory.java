package fr.ender.drawroad.pattern;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;


public class PatternFactory {
	
	private static Map<String, List<List<BlockPattern>>> patterns = new HashMap<>();
	
	private static Vector getDirection(Player player, Location pos) {
		if (player.getWorld().getBlockAt(pos.getBlockX() + 1, pos.getBlockY(), pos.getBlockZ()).getType() == Material.SPONGE)
			return new Vector(1, 0, 0);
		if (player.getWorld().getBlockAt(pos.getBlockX() - 1, pos.getBlockY(), pos.getBlockZ()).getType() == Material.SPONGE)
			return new Vector(-1, 0, 0);
		if (player.getWorld().getBlockAt(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ() + 1).getType() == Material.SPONGE)
			return new Vector(0, 0, 1);
		if (player.getWorld().getBlockAt(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ() - 1).getType() == Material.SPONGE)
			return new Vector(0, 0, -1);
		return new Vector(1, 0, 0);
	}

	@SuppressWarnings("deprecation")
	public static int createPattern(Player player, Location pos) {
		World world = player.getWorld();
		Location locationA = pos.clone();
		List<List<BlockPattern>> blocks = new LinkedList<>();
		Vector vector = getDirection(player, pos);

		for (int x = 0; world.getBlockAt(locationA).getType() == Material.SPONGE; x++) {
			Location locationB = locationA.clone();
			locationB.add(0, 1, 0);
			blocks.add(new LinkedList<>());
			for (int y = 0; !world.getBlockAt(locationB).isEmpty(); y++) { 
				blocks.get(x).add(new BlockPattern(world.getBlockAt(locationB).getType(), x, y, world.getBlockAt(locationB).getData()));
				locationB.add(0, 1, 0);
			}
			locationA.add(vector);
		}
		patterns.put(player.getDisplayName(), blocks);
		return blocks.size();
	}
	
	public static List<List<BlockPattern>> getPattern(Player player) {
		return patterns.get(player.getDisplayName());
	}
}
