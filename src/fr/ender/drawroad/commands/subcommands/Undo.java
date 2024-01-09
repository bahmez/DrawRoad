package fr.ender.drawroad.commands.subcommands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.ender.drawroad.commands.SubCommandExecutor;
import fr.ender.drawroad.undo.UndoManager;

public class Undo implements SubCommandExecutor {
	
	private File folder;
	
	public Undo() {
		folder = new File("./undoFile");
		if (!folder.exists())
			folder.mkdir();
	}

	@Override
	public boolean onCommand(Player player, Command cmd, String label, String[] args) {
		if (UndoManager.addUndo(args[1])) {
			player.sendMessage("Appling undo ...");
		} else {
			player.sendMessage("Error undo");
		}
		return true;
	}

	@Override
	public List<String> getTabCompletion(Player player, Command cmd, String label, String[] args) {
		List<String> fileName = new ArrayList<>();
		
		for (final File fileEntry : folder.listFiles())
			if (fileEntry.getName().startsWith(args[1]))
				fileName.add(fileEntry.getName());
		return fileName;
	}

	@Override
	public boolean isValidSub(String arg) {
		// TODO Auto-generated method stub
		return arg.equalsIgnoreCase(getCommandName());
	}

	@Override
	public void displayHelp(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCommandName() {
		return "undo";
	}
}
