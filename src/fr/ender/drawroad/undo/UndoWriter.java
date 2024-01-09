package fr.ender.drawroad.undo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fr.ender.drawroad.builder.BlockBuilder;

public class UndoWriter extends FileWriter {
	
	public static String generateName() throws IOException {
		Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd_hh-mm-ss");
        String name = "./undoFile/" + dateFormat.format(date);
        File file = new File(name);
        file.createNewFile();
        return name;
	}
	
	public String filename;
	
	public UndoWriter(String name, boolean append) throws IOException {
		super(name, append);
		filename = name;
	}

	public void write(BlockBuilder block) {
		String info = "";

		info += block.getLocation().getWorld().getName() + " ";
		info += block.getLocation().getBlockX() + " ";
		info += block.getLocation().getBlockY() + " ";
		info += block.getLocation().getBlockZ() + " ";
		info += block.data + " ";
		info += block.getMaterial().name() + "\n";
		try {
			this.write(info);	
		} catch (Exception e) { }
	}

}
