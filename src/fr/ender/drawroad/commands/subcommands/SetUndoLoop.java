package fr.ender.drawroad.commands.subcommands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.ender.drawroad.commands.SubCommandExecutor;
import fr.ender.drawroad.undo.UndoManager;

public class SetUndoLoop implements SubCommandExecutor {

	@Override
	public boolean onCommand(Player player, Command cmd, String label, String[] args) {
		if (args.length < 2) {
			player.sendMessage("You must put a value");
			return true;
		}
		try {
			UndoManager.loop = Integer.parseInt(args[1]);
		} catch (Exception e) {
			player.sendMessage("Invalid argument");
		}
		player.sendMessage("Success");
		return true;
	}

	@Override
	public List<String> getTabCompletion(Player player, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return "setUndoLoop";
	}

}
