package fr.ender.drawroad.commands.subcommands;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.ender.drawroad.commands.SubCommandExecutor;
import fr.ender.drawroad.pattern.PatternFactory;

public class SavePattern implements SubCommandExecutor {

	@Override
	public boolean onCommand(Player player, Command cmd, String label, String[] args) {
		Block block = player.getTargetBlock(null, 7);
		
		if (block == null) {
			player.sendMessage("Error: You must target a block");
			return true;
		}
		int saved = PatternFactory.createPattern(player, block.getLocation());
		player.sendMessage("Success !");
		player.sendMessage(saved + " Blocks have registed");
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
		return "savePattern";
	}

}
