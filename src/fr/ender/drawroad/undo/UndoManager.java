package fr.ender.drawroad.undo;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import fr.ender.drawroad.builder.BlockBuilder;

public class UndoManager {
	
	public static List<UndoReader> undoList = new LinkedList<>();
	
	public static int loop = 10000;

	public static boolean addUndo(String filename) {
		try {
			UndoReader reader = new UndoReader("./undoFile/" + filename);
			if (undoList.contains(reader))
				return false;
			undoList.add(reader);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static void AsyncUndo(JavaPlugin plugin, BukkitScheduler scheduler) {
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
            public void run() {
				for (UndoReader undoReader : undoList) {
					for (int i = 0; i < loop; i++) {
						BlockBuilder blockBuilder = undoReader.read();
						if (blockBuilder == null) {
							try {
								undoReader.close();
							} catch (Exception e) { }
							undoList.remove(undoReader);
							Bukkit.broadcastMessage("undo " + undoReader.file.getName() + " done !");
							break;
						}
						blockBuilder.placeBlock();
					}
				}
            }
        }, 0L, 20L);
	}
}
