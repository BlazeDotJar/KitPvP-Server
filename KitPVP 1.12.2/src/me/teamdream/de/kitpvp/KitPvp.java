package me.teamdream.de.kitpvp;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.teamdream.de.kitpvp.event.Listeners;
import me.teamdream.de.kitpvp.kit.KitManager;
//@SuppressWarnings({ "deprecation", "unused" })
@SuppressWarnings("deprecation")
public class KitPvp extends JavaPlugin {
	
	
	/* Main System Variablen */
	private static KitPvp instance = null;
	public static KitPvp getInstance() {return instance;}
	public String home_path = "plugins/TeamDream/";
	public String config_path = home_path+"/config.yml";
	public String stats_path = home_path+"/stats/stats.yml";
	
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
	}
	
	/* Bearbeitung */
	public void init() {
		/*
		 * Prüft die Verfügbarkeit der Config:
		 * Wenn nicht vorhanden: Config wird erstellt
		 * Wenn vorhanden: Wird geladen und Daten werden Ausgelesen */
		File file = new File(config_path);
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if(!file.exists()) {
			cfg.set("Server.Config.Main.Home-Path", home_path);
			cfg.set("Server.Config.Main.Config-Path", config_path);
			cfg.set("Server.Config.Main.Stats-Path", stats_path);
			
			try {cfg.save(file);} catch (IOException e) {e.printStackTrace();}
		}else{
		}
	}
	/* Fertigstellung */
	public void postInit() {
		new Listeners(getInstance());
		this.kitManager = new KitManager(getInstance());
	}
	
	/* Verschiedenes */
	public static String getTimeInString() {
		/*
		 * Gibt die Aktuelle Zeit wieder
		 * */
		SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar date = sdf.getCalendar();
		Date d = date.getTime();
		return d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
	}
	public static String getDateInString() {
		/*
		 * Gibt das aktuelle Datum wieder
		 * */
		SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar date = sdf.getCalendar();
		Date d = date.getTime();
		int day = d.getDate();
		int mon = d.getMonth();
		int year = d.getYear();
		return day+":"+mon+":"+year;
	}
}