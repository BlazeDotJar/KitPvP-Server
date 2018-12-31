package me.teamdream.de.kitpvp.profiling;

import java.util.HashMap;

import org.bukkit.entity.Player;

import me.teamdream.de.kitpvp.KitPvp;

public class Profiler {
	
	private KitPvp kitpvp=null;
	public HashMap<Player, PlayerProfile> profiles = new HashMap<Player, PlayerProfile>();
	
	public Profiler(KitPvp kitpvp) {
		this.kitpvp = kitpvp;
	}

	public void addToKits(Player p, String kitname) {
		profiles.get(p).addToKits(kitname);
	}
	
	public PlayerProfile getProfile(Player p) {
		return profiles.get(p);
	}
	public void register(Player p) {
		if(profiles.containsKey(p)) return;
		else profiles.put(p, new PlayerProfile(p));
	}
	public void unregister(Player p) {
		if(profiles.containsKey(p)) profiles.remove(p);
		else return;
	}
}
