package fr.ender.drawroad.commands.subcommands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.ender.drawroad.commands.SubCommandExecutor;
import fr.ender.drawroad.selector.Selector;

public class ChangePrecision implements SubCommandExecutor {

	@Override
	public boolean onCommand(Player player, Command cmd, String label, String[] args) {
		if (args.length < 2)
			Selector.changePrecision(player, 0);
		else
			try {
				Selector.changePrecision(player, Double.parseDouble(args[1]));
			} catch (Exception e) {
				System.out.println(e);
				player.sendMessage("invalid input");
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
		return "changePrecision";
	}
}
