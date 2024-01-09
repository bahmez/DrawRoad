package fr.ender.drawroad.builder;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockBuilder {
	
	public Location location;
	public Material material;
	public byte data;

	@SuppressWarnings("deprecation")
	public BlockBuilder(Block block) {
		this.location = block.getLocation();
		this.material = block.getType();
		this.data = block.getData();
	}
	
	public BlockBuilder(Location location, Material material, byte data) {
		this.location = location;
		this.material = material;
		this.data = data;
	}
	
	@SuppressWarnings("deprecation")
	public void placeBlock() {
		Block block = location.getWorld().getBlockAt(location);
		block.setType(material);
		block.setData(data);
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public byte getData() {
		return data;
	}

	public void setData(byte data) {
		this.data = data;
	}
	
}
