package fr.ender.drawroad;

import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import fr.ender.drawroad.builder.AsyncBuilder;
import fr.ender.drawroad.commands.CommandDrawRoad;
import fr.ender.drawroad.drawer.DrawParticleLine;
import fr.ender.drawroad.selector.SelectSimulator;
import fr.ender.drawroad.selector.Selector;
import fr.ender.drawroad.undo.UndoManager;
import net.minecraft.server.v1_12_R1.EnumParticle;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		System.out.println("DrawRoad loaded");
		getCommand("drawroad").setExecutor(new CommandDrawRoad());
		getServer().getPluginManager().registerEvents(new Listeners(), this);
		
		BukkitScheduler scheduler = getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	for (Map.Entry<Player, List<Vector>> mapEntry : SelectSimulator.selectSimulator.entrySet()) {
            		Player player = mapEntry.getKey();
            		List<Vector> simulationPoints = mapEntry.getValue();
            		List<Vector> points = Selector.getSelections(player);
            		Vector middleVector = new Vector(0.5, 0.5, 0.5);

            		if (simulationPoints == null || points == null || player == null)
            			continue;
            		for (int i = 1; i < simulationPoints.size(); i++) {
            			Vector aVector = simulationPoints.get(i - 1).clone().add(middleVector);
            			Vector bVector = simulationPoints.get(i).clone().add(middleVector);
            			
            			DrawParticleLine.drawParticleLine(aVector, bVector);
            		}
            		for (Vector point : points) {
            			Vector pointVector = point.clone();

            			pointVector.add(middleVector);
            			DrawParticleLine.printParticle(EnumParticle.SMOKE_LARGE, pointVector, 20);
            		}
            	}
            }
        }, 0L, 20L);
		AsyncBuilder.run(this, scheduler);
		UndoManager.AsyncUndo(this, scheduler);
	}
	
	@Override
	public void onDisable() {
		System.out.println("DrawRoad disabled");
	}

}
