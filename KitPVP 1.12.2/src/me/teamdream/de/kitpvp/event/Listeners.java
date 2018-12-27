package me.teamdream.de.kitpvp.event;

import org.bukkit.event.Listener;

import me.teamdream.de.kitpvp.KitPvp;

public class Listeners implements Listener {
	
	public Listeners(KitPvp kitpvp) {
		kitpvp.getServer().getPluginManager().registerEvents(this, kitpvp);		
	}
	
}
