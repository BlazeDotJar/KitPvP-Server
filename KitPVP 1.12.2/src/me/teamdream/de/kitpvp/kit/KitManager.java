package me.teamdream.de.kitpvp.kit;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.teamdream.de.kitpvp.KitPvp;

public class KitManager {
	
	public KitManager(KitPvp kitpvp) {
		File file = new File(KitPvp.getKits_path());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		Bukkit.broadcastMessage("Kit File exists: "+file.exists());
		if(!file.exists()) {
			cfg.set("Server.Config.Created On", KitPvp.getDateInString()+" / "+KitPvp.getTimeInString());
			
			/* Standart-Kit wird erstellt */
			Inventory kitInv = Bukkit.createInventory(null, 40);
			kitInv.setItem(0, new ItemStack(Material.DIAMOND_SWORD));
			kitInv.setItem(1, new ItemStack(Material.BOW));
			kitInv.setItem(2, new ItemStack(Material.ARROW));
			kitInv.setItem(3, new ItemStack(Material.DIAMOND_HELMET));
			kitInv.setItem(4, new ItemStack(Material.DIAMOND_CHESTPLATE));			
			kitInv.setItem(5, new ItemStack(Material.DIAMOND_LEGGINGS));
			kitInv.setItem(6, new ItemStack(Material.DIAMOND_BOOTS));
			kitInv.setItem(8, new ItemStack(Material.APPLE));
			saveKit(new Kit(null, "Standart-Kit", kitInv, 50, false, false));
			try {cfg.save(file);} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	public void saveKit(Kit kit) {
		File file = new File(KitPvp.getKits_path());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		cfg.set("Kits.Name", kit.getKitName());
		cfg.set("Kits.Name."+kit.getKitName()+".Creator", kit.getCreatorName());
		cfg.set("Kits.Name."+kit.getKitName()+".Price", String.valueOf(kit.getPrice()));
		cfg.set("Kits.Name."+kit.getKitName()+".Premium Only", String.valueOf(kit.isPremiumOnly()));
		cfg.set("Kits.Name."+kit.getKitName()+".Event Only", String.valueOf(kit.isEventOnly()));
		int itemSlot = 0;
		for(ItemStack i : kit.getInv().getContents()) {
			if(i != null && i.getType() != Material.AIR) {
				cfg.set("Kits.Name."+kit.getKitName()+".Items", i);
			}
			itemSlot ++;
		}
		try {cfg.save(file); Bukkit.broadcastMessage("Kit: "+kit.getKitName()+" wurde erstellt"); } catch (IOException e) {e.printStackTrace();}
	}
	
}
