package me.teamdream.de.kitpvp.event;

import org.bukkit.event.player.PlayerQuitEvent;

import me.teamdream.de.kitpvp.KitPvp;

public class QuitListener {
	
	public static void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage("§8[§c-§8] §7"+e.getPlayer().getName());
		KitPvp.getInstance().getProfiler().unregister(e.getPlayer());
	}
	
}
