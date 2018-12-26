package me.teamdream.de.kitpvp.kit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.teamdream.de.kitpvp.KitPvp;

public class KitManager {
	
	public final String default_kit_name = "Standart-Kit";
	
	public KitManager(KitPvp kitpvp) {
		File file = new File(KitPvp.getKits_path());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage("Kit File exists: "+file.exists());
		}
		if(!file.exists()) {
			cfg.set("Server.Config.Created On", KitPvp.getDateInString()+" / "+KitPvp.getTimeInString());
			try {cfg.save(file);} catch (IOException e) {e.printStackTrace();}
			createDefaultKit();
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
		for(int slot = 0; slot != kit.getInv().getSize(); slot++) {
			ItemStack i = kit.getInv().getItem(slot);
			if(i != null) {
				cfg.set("Kits.Name."+kit.getKitName()+".Items."+slot, i);
			}else cfg.set("Kits.Name."+kit.getKitName()+".Items."+slot, new ItemStack(Material.AIR));
		}
		for(String s : kit.getArmor().keySet()) {
			if(s != null && s.equals("Helmet")) cfg.set("Kits.Name."+kit.getKitName()+".Helmet", kit.getArmor().get(s));
			else cfg.set("Kits.Name."+kit.getKitName()+".Helmet.", new ItemStack(Material.AIR));
			
			if(s != null && s.equals("Chestplate")) cfg.set("Kits.Name."+kit.getKitName()+".Chestplate", kit.getArmor().get(s));
			else cfg.set("Kits.Name."+kit.getKitName()+".Chestplate.", new ItemStack(Material.AIR));
			
			if(s != null && s.equals("Leggings")) cfg.set("Kits.Name."+kit.getKitName()+".Leggings", kit.getArmor().get(s));
			else cfg.set("Kits.Name."+kit.getKitName()+".Leggings.", new ItemStack(Material.AIR));
			
			if(s != null && s.equals("Boots")) cfg.set("Kits.Name."+kit.getKitName()+".Boots", kit.getArmor().get(s));
			else cfg.set("Kits.Name."+kit.getKitName()+".Boots.", new ItemStack(Material.AIR));
		}
		
		/*
		 * 
		 * POTION EFFECTS WERDEN NOCH NICHT GESPEICHERT
		 * 
		 * */
		try {cfg.save(file);
		for(Player p : Bukkit.getOnlinePlayers())p.sendMessage("Kit: "+kit.getKitName()+" wurde erstellt");
		} catch (IOException e) {e.printStackTrace();}
	}
	
	private void createDefaultKit() {
		/* Standart-Kit wird erstellt */
		Inventory kitInv = Bukkit.createInventory(null, 36);
		ItemStack item = new ItemStack(Material.AIR);
		ItemMeta meta = null;
		ArrayList<String> lore = new ArrayList<String>();
		HashMap<String, ItemStack> armor = new HashMap<String, ItemStack>();
		HashMap<String, PotionEffect> potionEffects = new HashMap<String, PotionEffect>();
		
		item = new ItemStack(Material.STONE_SWORD);
		meta = item.getItemMeta();
		lore.add("");
		lore.add("§7Kit: §a"+default_kit_name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		kitInv.setItem(0, item);
		
		item = new ItemStack(Material.BOW);
		meta = item.getItemMeta();
		lore.add("");
		lore.add("§7Kit: §a"+default_kit_name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		kitInv.setItem(1, item);
		
		item = new ItemStack(Material.ARROW, 64);
		meta = item.getItemMeta();
		lore.add("");
		lore.add("§7Kit: §a"+default_kit_name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		kitInv.setItem(2, item);
		
		item = new ItemStack(Material.APPLE, 32);
		meta = item.getItemMeta();
		lore.add("");
		lore.add("§7Kit: §a"+default_kit_name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		kitInv.setItem(8, item);
		
		/* Armor */
		item = new ItemStack(Material.LEATHER_CHESTPLATE);
		meta = item.getItemMeta();
		lore.add("");
		lore.add("§7Kit: §a"+default_kit_name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		armor.put("Chestplate", item);
		
		item = new ItemStack(Material.DIAMOND_BOOTS);
		meta = item.getItemMeta();
		lore.add("");
		lore.add("§7Kit: §a"+default_kit_name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		armor.put("Boots", item);
		
		/* PotionEffects */
		PotionEffect regeneration = new PotionEffect(PotionEffectType.REGENERATION, 10*20, 10*20);
		potionEffects.put("Regenration", regeneration);
		
		saveKit(new Kit(null, default_kit_name, kitInv, armor, potionEffects, 50, false, false));
	}
	
}
