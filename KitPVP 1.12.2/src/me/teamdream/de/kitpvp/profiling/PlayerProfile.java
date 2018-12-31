package me.teamdream.de.kitpvp.profiling;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.teamdream.de.kitpvp.KitPvp;
import me.teamdream.de.kitpvp.special.SpecialItem;

public class PlayerProfile {
	
	private Player p = null;
	private ArrayList<SpecialItem> specialItems = new ArrayList<SpecialItem>();
	private ArrayList<String> ownKits = new ArrayList<String>();
	private String currentKit = null;
	private double credits = 0D;
	private int kills = 0;
	private int deaths = 0;
	private int killstreakCount = 0;
	private Killstreak killstreak = Killstreak.ZERO;
	
	public PlayerProfile(Player p) {
		this.p = p;
	}
	
	double creditsPerKill = 0.25;
	
	public void addKill() {
		kills++;
		double resultingCreditsPerKill = creditsPerKill;
		for(SpecialItem i : specialItems) {
			resultingCreditsPerKill = resultingCreditsPerKill*i.getMultiplier();
		}
		credits+=resultingCreditsPerKill;
		updateKillStreak();
	}
	public int getKills() {
		return kills;
	}
	public double getCredits() {
		return credits;
	}
	public void addDeath() {
		deaths++;
		killstreakCount = 0;
	}
	public int getDeaths() {
		return deaths;
	}
	public void setCurrentKit(String kit) {
		this.currentKit = kit;
	}
	public String getCurrentKit() {
		return currentKit;
	}
	public int getKillStreakCount() {
		return killstreakCount;
	}
	public Killstreak getKillstreak() {
		return killstreak;
	}
	public ArrayList<String> getOwnKits() {
		return ownKits;
	}
	
	public void addToKits(String kitname) {
		File file = new File(p.getUniqueId().toString());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		String uuid = p.getUniqueId().toString();
		ArrayList<String> kits = new ArrayList<String>();
		if(!file.exists()) {
			kits.add(KitPvp.getInstance().getKitManager().default_kit_name);
			kits.add(kitname);
			cfg.set(uuid+".Exp", 0);
			cfg.set(uuid+".Level", 0);
			cfg.set(uuid+".Kills Total", 0);
			cfg.set(uuid+".Deaths Total", 0);
			cfg.set(uuid+".Playtime.First Join", 0);
			cfg.set(uuid+".Playtime.Day", 0);
			cfg.set(uuid+".Playtime.Hour", 0);
			cfg.set(uuid+".Playtime.Minute", 0);
			cfg.set(uuid+".Playtime.Second", 0);
			cfg.set(uuid+".Active Effects", "none");
			cfg.set(uuid+".Kits", kits);
			try {cfg.save(file);} catch (IOException e) {e.printStackTrace();}
		}else{
			kits = (ArrayList<String>) cfg.getStringList(uuid+".Kits");
			kits.add(kitname);
			cfg.set(uuid+".Kits", kits);
			try {cfg.save(file);} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	public void updateKillStreak() {
		killstreakCount++;
		/*
		 * 
		 * Muss gemacht werden: killstreak.BLABLA
		 * 
		 */
	}
	
	enum Killstreak {
		ZERO("", null),
		ONE("", null),
		TWO("", null),
		THREE("", null),
		FOUR("", null),
		FIVE("", null),
		SIX("", null),
		SEVEN("", null),
		EIGHT("", null),
		NINE("", null),
		TEN("", null);
		
		String name = "";
		
		Killstreak(String name, ItemStack[] rewards) {
			this.name= name;
		}
		
		public String getName() {
			return name;
		}
	}

	public boolean addSpecialItem(SpecialItem si) {
		if(this.specialItems.size() != 8){
			this.specialItems.add(si);
		}else return false;
		return true;
	}
}
