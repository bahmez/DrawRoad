package fr.ender.drawroad.commands.subcommands;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.ender.drawroad.commands.SubCommandExecutor;
import fr.ender.drawroad.pattern.BlockPattern;
import fr.ender.drawroad.pattern.PatternFactory;

public class WatchPattern implements SubCommandExecutor {

	@Override
	public boolean onCommand(Player player, Command cmd, String label, String[] args) {
		List<List<BlockPattern>> pattern = PatternFactory.getPattern(player);
		Location playerLocation = player.getLocation();

		if (pattern == null) {
			player.sendMessage("Error: You don't have a registered pattern");
			return true;
		}
		for (List<BlockPattern> listBlock : pattern) {
			for (BlockPattern blockPattern : listBlock) {
				blockPattern.placeBlockInWorld(playerLocation);
			}
		}
		player.sendMessage("Success !");
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
		return "watchPattern";
	}

}
