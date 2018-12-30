package me.teamdream.de.kitpvp;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.teamdream.de.kitpvp.event.Listeners;
import me.teamdream.de.kitpvp.kit.KitManager;
//@SuppressWarnings({ "deprecation", "unused" })
@SuppressWarnings("deprecation")
public class KitPvp extends JavaPlugin {
	
	/* Main System Variablen */
	private static KitPvp instance = null;
	public static KitPvp getInstance() {return instance;}
	public static String home_path = "plugins/TeamDream/";
	public static String config_path = home_path+"config.yml";
	public static String stats_path = home_path+"stats/stats.yml";
	public static String kits_path = home_path+"kits/kits.yml";
	public static String kitslist_path = home_path+"kits/kits-list.yml";
	public static String noPermission = "§cDu hast kein Recht dazu";
	
	/* Klassen-Instanzen */
	private KitManager kitManager;
	
	@Override
	public void onEnable() {
		preInit();
		init();
		postInit();
		super.onEnable();
	}
	@Override
	public void onDisable() {
		super.onDisable();
	}
	
	/* Vorbereitung */
	public void preInit() {
		KitPvp.instance = this;
		this.kitManager = new KitManager(getInstance());
	}
	
	/* Bearbeitung */
	public void init() {
		/* Prüft die Verfügbarkeit der Config:
		 * Wenn nicht vorhanden: Config wird erstellt
		 * Wenn vorhanden: Wird geladen und Daten werden Ausgelesen */
		File file = new File(config_path);
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if(!file.exists()) {
			cfg.set("Server.Config.Created On", getDateInString()+" / "+getTimeInString());
			cfg.set("Server.Config.Main.Home-Path", home_path);
			cfg.set("Server.Config.Main.Config-Path", config_path);
			cfg.set("Server.Config.Main.Stats-Path", stats_path);
			cfg.set("Server.Config.Main.Kits-Path", kits_path);
			cfg.set("Server.Config.Main.Kits-List-Path", kitslist_path);
			cfg.set("Server.Config.Main.No Permission", noPermission);
			
			try {cfg.save(file);} catch (IOException e) {e.printStackTrace();}
		}else{
			home_path = cfg.getString("Server.Config.Main.Home-Path");
			config_path = cfg.getString("Server.Config.Main.Config-Path");
			stats_path = cfg.getString("Server.Config.Main.Stats-Path");
			kits_path = cfg.getString("Server.Config.Main.Kits-Path");
			kitslist_path = cfg.getString("Server.Config.Main.Kits-List-Path");
			noPermission = cfg.getString("Server.Config.Main.No Permission");
		}
	}
	/* Fertigstellung */
	public void postInit() {
		new Listeners(getInstance());
		for(Player p : Bukkit.getOnlinePlayers()) getKitManager().loadKit(p, "Premium-Kit");
	}
	
	/* Verschiedenes */
	public static String getTimeInString() {
		/*
		 * Gibt die Aktuelle Zeit wieder
		 */
		SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar date = sdf.getCalendar();
		Date d = date.getTime();
		return d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
	}
	public static String getDateInString() {
		/*
		 * Gibt das aktuelle Datum wieder
		 */
		SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar date = sdf.getCalendar();
		Date d = date.getTime();
		int day = d.getDate();
		int mon = d.getMonth();
		int year = d.getYear();
		return day+":"+mon+":"+year;
	}
	
	/* Getters und Setters */
	public KitManager getKitManager() {
		return kitManager;
	}
	public void setKitManager(KitManager kitManager) {
		this.kitManager = kitManager;
	}
	public static String getHome_path() {
		return home_path;
	}
	public static String getConfig_path() {
		return config_path;
	}
	public static String getStats_path() {
		return stats_path;
	}
	public static String getKits_path() {
		return kits_path;
	}
	public static String getKitsList() {
		return kitslist_path;
	}
}