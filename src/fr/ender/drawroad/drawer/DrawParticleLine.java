package fr.ender.drawroad.drawer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import fr.ender.drawroad.algorithm.Bresenham3D;
import net.minecraft.server.v1_12_R1.EnumParticle;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldParticles;

public class DrawParticleLine {
	
	public static void printParticle(EnumParticle particle, Vector vector, int number) {
		List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
				particle, false,
				(float) vector.getX(), (float) vector.getY(), (float) vector.getZ(),
				0, 0, 0,
				0.001f, number);
		for (Player player : list) {
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
		}
	}
	
	public static void drawParticleLine(Vector aVector, Vector bVector) {
		aVector.setX(aVector.getX() * 10);
		aVector.setY(aVector.getY() * 10);
		aVector.setZ(aVector.getZ() * 10);
		bVector.setX(bVector.getX() * 10);
		bVector.setY(bVector.getY() * 10);
		bVector.setZ(bVector.getZ() * 10);
		List<Vector> points = Bresenham3D.bresenham3D(
				aVector.getBlockX(), aVector.getBlockY(), aVector.getBlockZ(),
				bVector.getBlockX(), bVector.getBlockY(), bVector.getBlockZ());
		
		for (Vector point : points) {
			point.setX(point.getX() / 10);
			point.setY(point.getY() / 10);
			point.setZ(point.getZ() / 10);
			DrawParticleLine.printParticle(EnumParticle.FLAME, point, 1);
		}
	}

}
