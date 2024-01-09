package fr.ender.drawroad.pattern;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockPattern {
	
	private Material material;
	private int x;
	private int y;
	private byte data;
	
	public static BlockPattern EMPTY = new BlockPattern(Material.AIR, 0, 0, (byte) 0);
	
	public BlockPattern(Material material, int x, int y, byte data) {
		this.x = x;
		this.y = y;
		this.material = material;
		this.data = data;
	}
	
	@SuppressWarnings("deprecation")
	public void placeBlockInWorld(Location location) {
		Block block = location.getWorld().getBlockAt(
				(int) (location.getX() + this.getX()),
				(int) (location.getY() + this.getY()),
				(int) (location.getZ()));
		block.setType(this.material);
		block.setData((byte) this.data);
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getData() {
		return data;
	}

	public void setData(byte data) {
		this.data = data;
	}
}
