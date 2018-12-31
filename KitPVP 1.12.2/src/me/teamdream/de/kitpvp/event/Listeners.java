package me.teamdream.de.kitpvp.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.teamdream.de.kitpvp.KitPvp;

public class Listeners implements Listener {
	
	public Listeners(KitPvp kitpvp) {
		kitpvp.getServer().getPluginManager().registerEvents(this, kitpvp);		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		JoinListener.onJoin(e);
	}
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		InventoryListener.onClick(e);
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		QuitListener.onQuit(e);
	}
	
}
