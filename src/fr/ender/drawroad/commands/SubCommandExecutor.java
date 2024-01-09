package fr.ender.drawroad.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public interface SubCommandExecutor {
	
	public boolean onCommand(Player Player, Command cmd, String label, String[] args);

	public List<String> getTabCompletion(Player player, Command cmd, String label, String[] args);

	public boolean isValidSub(String arg);
	
	public void displayHelp(Player player);

	public String getCommandName();
}
