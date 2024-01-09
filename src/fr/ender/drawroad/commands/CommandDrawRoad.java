package fr.ender.drawroad.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import fr.ender.drawroad.commands.subcommands.BlockPattern;
import fr.ender.drawroad.commands.subcommands.ChangePrecision;
import fr.ender.drawroad.commands.subcommands.ClearSelection;
import fr.ender.drawroad.commands.subcommands.Draw;
import fr.ender.drawroad.commands.subcommands.PopSelection;
import fr.ender.drawroad.commands.subcommands.SavePattern;
import fr.ender.drawroad.commands.subcommands.SetAsyncLoop;
import fr.ender.drawroad.commands.subcommands.SetUndoLoop;
import fr.ender.drawroad.commands.subcommands.Tool;
import fr.ender.drawroad.commands.subcommands.Undo;
import fr.ender.drawroad.commands.subcommands.WatchPattern;

public class CommandDrawRoad implements TabExecutor {
	
	
	private List<SubCommandExecutor> commands;
	
	public CommandDrawRoad() {
		commands = new ArrayList<>();
		commands.add(new BlockPattern());
		commands.add(new ChangePrecision());
		commands.add(new ClearSelection());
		commands.add(new Draw());
		commands.add(new PopSelection());
		commands.add(new SavePattern());
		commands.add(new Tool());
		commands.add(new Undo());
		commands.add(new WatchPattern());
		commands.add(new WatchPattern());
		commands.add(new SetAsyncLoop());
		commands.add(new SetUndoLoop());
	}
	
	private boolean printHelp(Player player) {
		player.sendMessage("commands list :");
		player.sendMessage("     draw -> ");
		player.sendMessage("     undo -> ");
		player.sendMessage("     blockPattern -> ");
		player.sendMessage("     watchPattern -> ");
		player.sendMessage("     savePattern -> ");
		player.sendMessage("     tool -> ");
		player.sendMessage("     changePrecision -> ");
		player.sendMessage("     clearSelection -> ");
		player.sendMessage("     popSelection -> ");
		player.sendMessage("     setAsyncLoop -> ");
		player.sendMessage("     setUndoLoop -> ");
		return true;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to execute this command :)");
			return false;
		}
		Player player = (Player) sender;

		if (args.length == 0)
			return printHelp(player);
		if (args[0].equalsIgnoreCase("help")) {
			for (SubCommandExecutor subCommand : commands)
				if (subCommand.isValidSub(args[0])) {
					subCommand.displayHelp(player);
					return true;
				}
			return printHelp(player);
		}
		for (SubCommandExecutor subCommand : commands)
			if (subCommand.isValidSub(args[0]))
				return subCommand.onCommand(player, cmd, label, args);
		return printHelp(player);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
			return null;
		Player player = (Player) sender;
		if (args.length == 1) {
			List<String> subCommands = new ArrayList<>();
			
			for (SubCommandExecutor subCommand : commands)
				if (subCommand.getCommandName().startsWith(args[0]))
					subCommands.add(subCommand.getCommandName());
			if ("help".startsWith(args[0]))
				subCommands.add("help");
			return subCommands;
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("help")) {
				List<String> subCommands = new ArrayList<>();
				
				for (SubCommandExecutor subCommand : commands)
					if (subCommand.getCommandName().startsWith(args[1]))
						subCommands.add(subCommand.getCommandName());
				return subCommands;
			}
			for (SubCommandExecutor subCommand : commands)
				if (subCommand.isValidSub(args[0]))
					return subCommand.getTabCompletion(player, cmd, label, args);
		}
		return null;
	}

}
