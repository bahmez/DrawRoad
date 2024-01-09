package fr.ender.drawroad.commands.subcommands;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.ender.drawroad.commands.SubCommandExecutor;

public class Tool implements SubCommandExecutor {

	@Override
	public boolean onCommand(Player player, Command cmd, String label, String[] args) {
		ItemStack itemStack = new ItemStack(Material.DIAMOND_HOE, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName("§cSelection's tool");
		itemStack.setItemMeta(meta);
		player.getInventory().addItem(itemStack);
		player.sendMessage("Success");
		player.sendMessage("Left click on the block to select the block");
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
		return "tool";
	}
}
