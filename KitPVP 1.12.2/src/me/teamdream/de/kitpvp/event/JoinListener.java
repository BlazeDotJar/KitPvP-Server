package me.teamdream.de.kitpvp.event;

import org.bukkit.event.player.PlayerJoinEvent;

import me.teamdream.de.kitpvp.KitPvp;

public class JoinListener {
	
	public static void onJoin(PlayerJoinEvent e) {
		KitPvp.getInstance().getProfiler().register(e.getPlayer());
		KitPvp.sendMessage(e.getPlayer(), "§aNutze §f/kit §aum alle Kits zu sehen");
	}
	
}
