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
import org.bukkit.inventory.PlayerInventory;
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
		}
		createDefaultKit();
		
	}
	
	public boolean saveKit(Kit kit) {
		File file = new File(KitPvp.getKits_path());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if(cfg.getString("Kits.Name."+kit.getKitName()+".Creator") != null) return false;
		cfg.set("Kits.Name."+kit.getKitName()+".Creator", kit.getCreatorName());
		cfg.set("Kits.Name."+kit.getKitName()+".Price", String.valueOf(kit.getPrice()));
		cfg.set("Kits.Name."+kit.getKitName()+".Premium Only", String.valueOf(kit.isPremiumOnly()));
		cfg.set("Kits.Name."+kit.getKitName()+".Event Only", String.valueOf(kit.isEventOnly()));
		cfg.set("Kits.Name."+kit.getKitName()+".Display Material", kit.getDisplayMaterial().toString());
		for(int slot = 0; slot != kit.getInv().getSize(); slot++) {
			ItemStack i = kit.getInv().getItem(slot);
			if(i != null) {
				cfg.set("Kits.Name."+kit.getKitName()+".Items."+slot, i);
			}else cfg.set("Kits.Name."+kit.getKitName()+".Items."+slot, new ItemStack(Material.AIR));
		}
		ItemStack helmet = kit.getArmor().get("Helmet");
		ItemStack chestplate = kit.getArmor().get("Chestplate");
		ItemStack leggings = kit.getArmor().get("Leggings");
		ItemStack boots = kit.getArmor().get("Boots");
		if(helmet != null)cfg.set("Kits.Name."+kit.getKitName()+".Helmet", helmet);
		else cfg.set("Kits.Name."+kit.getKitName()+".Helmet", new ItemStack(Material.AIR));
		
		if(chestplate != null)cfg.set("Kits.Name."+kit.getKitName()+".Chestplate", chestplate);
		else cfg.set("Kits.Name."+kit.getKitName()+".Chestplate", new ItemStack(Material.AIR));
		
		if(leggings != null)cfg.set("Kits.Name."+kit.getKitName()+".Leggings", leggings);
		else cfg.set("Kits.Name."+kit.getKitName()+".Leggings", new ItemStack(Material.AIR));
		
		if(boots != null)cfg.set("Kits.Name."+kit.getKitName()+".Boots", boots);
		else cfg.set("Kits.Name."+kit.getKitName()+".Boots", new ItemStack(Material.AIR));
		
		/*
		 * 
		 * POTION EFFECTS WERDEN NOCH NICHT GESPEICHERT
		 * 
		 */
		try {
			cfg.save(file);
			for(Player p : Bukkit.getOnlinePlayers())p.sendMessage("Kit: "+kit.getKitName()+" wurde erstellt");return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean loadKit(Player p, String kitName) {
		File file = new File(KitPvp.getKits_path());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if(cfg.getString("Kits.Name."+kitName+".Creator") != null) {
			PlayerInventory pInv = p.getInventory();
			pInv.clear();
			for(int i = 0; i != 36; i++) {
				ItemStack stack = cfg.getItemStack("Kits.Name."+kitName+".Items."+i);
				if(stack != null) pInv.setItem(i, stack);
				else pInv.setItem(i, new ItemStack(Material.AIR));
			}
			
			ItemStack stack = cfg.getItemStack("Kits.Name."+kitName+".Helmet");
			if(stack != null) p.getInventory().setHelmet(stack);
			else p.getInventory().setHelmet(new ItemStack(Material.AIR));
			
			stack = cfg.getItemStack("Kits.Name."+kitName+".Chestplate");
			if(stack != null) p.getInventory().setChestplate(stack);
			else p.getInventory().setChestplate(new ItemStack(Material.AIR));
			
			stack = cfg.getItemStack("Kits.Name."+kitName+".Leggings");
			if(stack != null) p.getInventory().setLeggings(stack);
			else p.getInventory().setLeggings(new ItemStack(Material.AIR));
			
			stack = cfg.getItemStack("Kits.Name."+kitName+".Boots");
			if(stack != null) p.getInventory().setBoots(stack);
			else p.getInventory().setBoots(new ItemStack(Material.AIR));
			p.getInventory().setHeldItemSlot(0);
		}else{
			for(Player t : Bukkit.getOnlinePlayers()) t.sendMessage("Not found");
			return false;
		}
		return true;
	}
	
	public Kit getKit(String kitName) {
		File file = new File(KitPvp.getKits_path());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		Kit kit = new Kit(null, kitName);
		if(cfg.getString("Kits.Name."+kitName+".Creator") != null) {
			for(int i = 0; i != 36; i++) {
				kit.getInv().setItem(i, cfg.getItemStack("Kits.Name."+kitName+".Items."+i));
			}
			kit.getArmor().put("Helmet", cfg.getItemStack("Kits.Name."+kitName+".Helmet"));
			kit.getArmor().put("Chestplate", cfg.getItemStack("Kits.Name."+kitName+".Chestplate"));
			kit.getArmor().put("Leggings", cfg.getItemStack("Kits.Name."+kitName+".Leggings"));
			kit.getArmor().put("Boots", cfg.getItemStack("Kits.Name."+kitName+".Boots"));
			return kit;
		}else{
			for(Player t : Bukkit.getOnlinePlayers()) t.sendMessage("Not found");
			return null;
		}
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
		meta.setLore(lore);
		item.setItemMeta(meta);
		kitInv.setItem(0, item);
		
		item = new ItemStack(Material.BOW);
		meta = item.getItemMeta();
		meta.setLore(lore);
		item.setItemMeta(meta);
		kitInv.setItem(1, item);
		
		item = new ItemStack(Material.ARROW, 64);
		meta = item.getItemMeta();
		meta.setLore(lore);
		item.setItemMeta(meta);
		kitInv.setItem(2, item);
		
		item = new ItemStack(Material.APPLE, 32);
		meta = item.getItemMeta();
		meta.setLore(lore);
		item.setItemMeta(meta);
		kitInv.setItem(8, item);
		
		/* Armor */
//		item = new ItemStack(Material.LEATHER_HELMET);
//		meta = item.getItemMeta();
//		meta.setLore(lore);
//		item.setItemMeta(meta);
//		armor.put("Helmet", item);
		
		item = new ItemStack(Material.LEATHER_CHESTPLATE);
		meta = item.getItemMeta();
		meta.setLore(lore);
		item.setItemMeta(meta);
		armor.put("Chestplate", item);
		
//		item = new ItemStack(Material.LEATHER_LEGGINGS);
//		meta = item.getItemMeta();
//		meta.setLore(lore);
//		item.setItemMeta(meta);
//		armor.put("Leggings", item);
		
		item = new ItemStack(Material.LEATHER_BOOTS);
		meta = item.getItemMeta();
		meta.setLore(lore);
		item.setItemMeta(meta);
		armor.put("Boots", item);
		
		/* PotionEffects */
		PotionEffect regeneration = new PotionEffect(PotionEffectType.REGENERATION, 10*20, 10*20);
		potionEffects.put("Regeneration", regeneration);
		
		saveKit(new Kit(null, default_kit_name, kitInv, armor, potionEffects, Material.STONE_SWORD, 50D, false, false));
	}
	
}
