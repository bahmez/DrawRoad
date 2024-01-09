package fr.ender.drawroad.undo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import fr.ender.drawroad.builder.BlockBuilder;

public class UndoReader {

	BufferedReader bufferedReader;
	File file;
	//RandomAccessFile raf;
	long filePointer;
	
	public UndoReader(String filename) throws Exception {
		//raf = new RandomAccessFile(filename, "r");
		file = new File(filename);
		//filePointer = raf.length() - 1;
		Process process = Runtime.getRuntime().exec("tac " + filename);
		bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	}
	
	public BlockBuilder read() {
        String line;
        try {
            line = bufferedReader.readLine();
        } catch (Exception e) {
            line = null;
        }
        
        if (line == null) return null;
        
        String[] parts = line.split(" ");

        World world = Bukkit.getWorld(parts[0]);
        int x = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);
        int z = Integer.parseInt(parts[3]);
        Location location = new Location(world, x, y, z);
        return new BlockBuilder(location, Material.getMaterial(parts[5]), (byte) Integer.parseInt(parts[4]));
    }
	
	public void close() throws IOException {
		System.out.println(file.delete());
	}

}
