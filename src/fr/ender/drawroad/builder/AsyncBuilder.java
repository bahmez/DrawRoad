package fr.ender.drawroad.builder;

import java.util.Stack;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;


public class AsyncBuilder {
	
	public static int loop = 100;
	
	public interface lambdaAsync {
		void execute();
	}
	
	//public static Stack<BlockBuilder> waitingList = new Stack<>();
	public static Stack<lambdaAsync> waitingList = new Stack<>();

	public static void run(JavaPlugin plugin, BukkitScheduler scheduler) {
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
            public void run() {
				for (int i = 0; i < waitingList.size() && i < loop; i++) {
					waitingList.remove(0).execute();
				}
            }
        }, 0L, 20L);
	}
}
