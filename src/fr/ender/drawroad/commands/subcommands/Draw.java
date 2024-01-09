package fr.ender.drawroad.commands.subcommands;

import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import fr.ender.drawroad.commands.SubCommandExecutor;
import fr.ender.drawroad.drawer.DrawLine;
import fr.ender.drawroad.selector.Selector;

public class Draw implements SubCommandExecutor {

	@Override
	public boolean onCommand(Player player, Command cmd, String label, String[] args) {
		List<Vector> positions = Selector.getSelections(player);
		
		if (positions == null) {
			player.sendMessage("The selection list is empty");
			return true;
		}
		try {
			if (!DrawLine.drawRoad(positions, player)) {
				player.sendMessage("Empty pattern");
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			player.sendMessage("Error writer");
		}
		Selector.clearSelections(player);
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
		return "draw";
	}

}
