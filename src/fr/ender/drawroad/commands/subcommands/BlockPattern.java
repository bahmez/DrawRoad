package fr.ender.drawroad.commands.subcommands;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.ender.drawroad.commands.SubCommandExecutor;

public class BlockPattern implements SubCommandExecutor {

	@Override
	public boolean onCommand(Player player, Command cmd, String label, String[] args) {
		Block block = player.getWorld().getBlockAt(player.getLocation().add(0, -1, 0));

		if (block.isEmpty()) {
			block.setType(Material.SPONGE);
			player.sendMessage("Success !");
			player.sendMessage("If you want to increase the size, do it in the X axis with the sponge block");
			player.sendMessage("If you have finish. use the command '/DrawRoad save' to save your pattern");
		} else {
			player.sendMessage("Error: You must be above nothing");
		}
		return true;
	}

	@Override
	public List<String> getTabCompletion(Player player, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValidSub(String arg) {
		return arg.equalsIgnoreCase(getCommandName());
	}

	@Override
	public void displayHelp(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCommandName() {
		return "blockPattern";
	}

}
