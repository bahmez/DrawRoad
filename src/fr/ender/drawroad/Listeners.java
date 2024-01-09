package fr.ender.drawroad;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.ender.drawroad.selector.Selector;

public class Listeners implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Action action = e.getAction();
		ItemStack itemStack = e.getItem();
		
		if (itemStack == null || player.getGameMode() != GameMode.CREATIVE)
			return;
		if (itemStack.getType() == Material.DIAMOND_HOE && action == Action.LEFT_CLICK_BLOCK) {
			Block block = e.getClickedBlock();
			Selector.addPoint(player, block.getLocation());
			player.sendMessage("Point added !");
			e.setCancelled(true);
		}
	}
	
}
