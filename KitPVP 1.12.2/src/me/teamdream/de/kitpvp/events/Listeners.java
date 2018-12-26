package me.teamdream.de.kitpvp.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.teamdream.de.kitpvp.KitPvp;

public class Listeners implements Listener {
	
	public Listeners(KitPvp kitpvp) {
		kitpvp.getServer().getPluginManager().registerEvents(this, kitpvp);		
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		
	}
	
}
