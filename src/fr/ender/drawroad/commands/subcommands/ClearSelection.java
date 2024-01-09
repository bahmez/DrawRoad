package fr.ender.drawroad.commands.subcommands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.ender.drawroad.commands.SubCommandExecutor;
import fr.ender.drawroad.selector.Selector;

public class ClearSelection implements SubCommandExecutor {

	@Override
	public boolean onCommand(Player player, Command cmd, String label, String[] args) {
		Selector.clearSelections(player);
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
		return "clearSelection";
	}

}
