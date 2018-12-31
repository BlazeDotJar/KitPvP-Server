package me.teamdream.de.kitpvp.kit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.teamdream.de.kitpvp.KitPvp;
import me.teamdream.de.kitpvp.ValidTime;
import me.teamdream.de.kitpvp.bank.CurrencyManager;
import me.teamdream.de.kitpvp.special.CreditsBooster;
import me.teamdream.de.kitpvp.special.SpecialItemType;

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
	
	@SuppressWarnings({ "unchecked", "unused" })
	public boolean saveKit(Kit kit) {
		/* Ein Kit wird abgespeichert */
		File file = new File(KitPvp.getKits_path());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		File kitsfile = new File(KitPvp.getKitsList_path());
		FileConfiguration kitscfg = YamlConfiguration.loadConfiguration(kitsfile);
		ArrayList<String> kits = new ArrayList<String>();
		
		/* Kit Eigenschafte werden gespeichert */
		if(cfg.getString("Kits.Name."+kit.getKitName()+".Creator") != null) return false;
		cfg.set("Kits.Name."+kit.getKitName()+".Creator", kit.getCreatorName());
		cfg.set("Kits.Name."+kit.getKitName()+".Display Name", kit.getDisplayName());
		cfg.set("Kits.Name."+kit.getKitName()+".Display Material", kit.getDisplayMaterial().toString());
		cfg.set("Kits.Name."+kit.getKitName()+".Premium Only", String.valueOf(kit.isPremiumOnly()));
		cfg.set("Kits.Name."+kit.getKitName()+".Event Only", String.valueOf(kit.isEventOnly()));
		cfg.set("Kits.Name."+kit.getKitName()+".Player Walkspeed", kit.getPlayerSpeed());
		cfg.set("Kits.Name."+kit.getKitName()+".Price", kit.getPrice());
		
		/* Special item werden abgespeichert */
		for(int i = 0; i != 9; i++) {
			if(kit.getSpecialItem(i) != null) {
				cfg.set("Kits.Name."+kit.getKitName()+".SpecialItem"+(i+1)+".Type", kit.getSpecialItem(i).getType().toString());
				cfg.set("Kits.Name."+kit.getKitName()+".SpecialItem"+(i+1)+".Multiplier", kit.getSpecialItem(i).getMultiplier());
				cfg.set("Kits.Name."+kit.getKitName()+".SpecialItem"+(i+1)+".ValidTime.Day", kit.getSpecialItem(i).getValidTime().getDay());
				cfg.set("Kits.Name."+kit.getKitName()+".SpecialItem"+(i+1)+".ValidTime.Hour", kit.getSpecialItem(i).getValidTime().getHour());
				cfg.set("Kits.Name."+kit.getKitName()+".SpecialItem"+(i+1)+".ValidTime.Minute", kit.getSpecialItem(i).getValidTime().getMinute());
				cfg.set("Kits.Name."+kit.getKitName()+".SpecialItem"+(i+1)+".ValidTime.Second", kit.getSpecialItem(i).getValidTime().getSecond());				
			}else{
				cfg.set("Kits.Name."+kit.getKitName()+".SpecialItem"+(i+1)+".Type", "NONE");
				cfg.set("Kits.Name."+kit.getKitName()+".SpecialItem"+(i+1)+".Multiplier", 0D);
				cfg.set("Kits.Name."+kit.getKitName()+".SpecialItem"+(i+1)+".ValidTime.Day", 0D);
				cfg.set("Kits.Name."+kit.getKitName()+".SpecialItem"+(i+1)+".ValidTime.Hour", 0D);
				cfg.set("Kits.Name."+kit.getKitName()+".SpecialItem"+(i+1)+".ValidTime.Minute", 0D);
				cfg.set("Kits.Name."+kit.getKitName()+".SpecialItem"+(i+1)+".ValidTime.Second", 0D);	
			}
		}
		
		/* Kit Inventar wird gespeichert */
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
		
		kits = (ArrayList<String>) kitscfg.getList("Kits.List");
		if(kits != null && kits.isEmpty()) {
			for(String s : kits) {
				if("Kits.Name."+s+".Creator" == null) kits.remove(s);
			}
		}
		if(kits == null) kits = new ArrayList<String>();
		kits.add(kit.getKitName());
		kitscfg.set("Kits.List", kits);
		try {
			kitscfg.save(kitsfile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			cfg.save(file);
			for(Player p : Bukkit.getOnlinePlayers())p.sendMessage("Kit: "+kit.getKitName()+" wurde erstellt");return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean loadKit(Player p, String kitName) {
		/* Ein Spieler bekommt ein Kit geladen, welches erst ausgelesen werden muss */
		File file = new File(KitPvp.getKits_path());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if(cfg.getString("Kits.Name."+kitName+".Creator") != null) {
			PlayerInventory pInv = p.getInventory();
			pInv.clear();
			
			/* SpecialItems werden geladen */
			ItemStack noItem = new ItemStack(Material.BARRIER);
			ItemMeta meta = noItem.getItemMeta();
			ArrayList<String> lore = new ArrayList<String>();
			lore.add("§7§oHier werden deine aktuellen");
			lore.add("§7§oSpecial-Items angezeigt");
			meta.setLore(lore);
			meta.setDisplayName("§aSpecial-Item Slot");
			noItem.setItemMeta(meta);
			for(int i = 0; i != 9; i++) {				
				if(cfg.getString("Kits.Name."+kitName+".SpecialItem"+(i+1)+".Type") != null &&
						!cfg.getString("Kits.Name."+kitName+".SpecialItem"+(i+1)+".Type").equals("NONE")) {
					String type = cfg.getString("Kits.Name."+kitName+".SpecialItem"+(i+1)+".Type");
					if(type.toUpperCase().equals(SpecialItemType.CREDITS_BOOSTER.toString())) {
						ValidTime vTime = new ValidTime(cfg.getInt("Kits.Name."+kitName+".SpecialItem"+(i+1)+".ValidTime.Day"),
								cfg.getInt("Kits.Name."+kitName+".SpecialItem"+(i+1)+".ValidTime.Hour"),
								cfg.getInt("Kits.Name."+kitName+".SpecialItem"+(i+1)+".ValidTime.Minute"),
								cfg.getInt("Kits.Name."+kitName+".SpecialItem"+(i+1)+".ValidTime.Second"),
								KitPvp.getDateInString()+"/"+KitPvp.getTimeInString());
						CreditsBooster cb = new CreditsBooster(p, vTime, cfg.getDouble("Kits.Name."+kitName+".SpecialItem"+(i+1)+".Multiplier"));
						if(KitPvp.getInstance().getProfiler().getProfile(p).addSpecialItem(cb)) {
							int slot = (9+KitPvp.getInstance().getProfiler().getProfile(p).getOwnKits().size());
							pInv.setItem(slot, cb.getDisplayItem());
							KitPvp.sendMessage(p, "Dein Special-Item wurde hinzugefügt");
						}else KitPvp.sendMessage(p, "Dus hast zu viele Special-Items");
					}
					
					
				}else{
					pInv.setItem((9+i), noItem);
				}
			}
			
			

			for(int i = 0; i != 36; i++) {
				ItemStack stack = cfg.getItemStack("Kits.Name."+kitName+".Items."+i);
				if(stack != null && stack.getType() != Material.AIR) pInv.setItem(i, stack);
//				else pInv.setItem(i, new ItemStack(Material.AIR));
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
			p.setWalkSpeed((float)cfg.getDouble("Kits.Name."+kitName+".Player Walkspeed"));
			KitPvp.getInstance().getProfiler().getProfile(p).setCurrentKit(kitName);
		}else{
			for(Player t : Bukkit.getOnlinePlayers()) t.sendMessage("Not found");
			return false;
		}
		return true;
	}
	public boolean loadKit(Player p, Kit kit) {
		/* Ein Spieler bekommt ein Kit geladen, welches bereits ausgelesen wurde */
		if(kit == null) return false;
		PlayerInventory pInv = p.getInventory();
		pInv.clear();
		for(int i = 0; i != 36; i++) {
			ItemStack stack =  kit.getInv().getItem(i);
			if(stack != null) pInv.setItem(i, stack);
			else pInv.setItem(i, new ItemStack(Material.AIR));
		}
		ItemStack stack = kit.getArmor().get("Helmet");
		if(stack != null) p.getInventory().setHelmet(stack);
		else p.getInventory().setHelmet(new ItemStack(Material.AIR));
		
		stack = kit.getArmor().get("Chestplate");
		if(stack != null) p.getInventory().setChestplate(stack);
		else p.getInventory().setChestplate(new ItemStack(Material.AIR));
		
		stack = kit.getArmor().get("Leggings");
		if(stack != null) p.getInventory().setLeggings(stack);
		else p.getInventory().setLeggings(new ItemStack(Material.AIR));
		
		stack = kit.getArmor().get("Boots");
		if(stack != null) p.getInventory().setBoots(stack);
		else p.getInventory().setBoots(new ItemStack(Material.AIR));
		p.getInventory().setHeldItemSlot(0);
		p.setWalkSpeed(kit.getPlayerSpeed());
		return true;
	}
	
	public Kit getKit(String kitName) {
		/* Ein Kit wird als Objekt-Klasse zurückgegeben */
		File file = new File(KitPvp.getKits_path());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		Kit kit = new Kit("Console", kitName);
		if(cfg.getString("Kits.Name."+kitName+".Creator") != null) {
			kit.setCreatorName(cfg.getString("Kits.Name."+kitName+".Creator"));
			kit.setDisplayName(cfg.getString("Kits.Name."+kitName+".Display Name"));
			kit.setDisplayMaterial(cfg.getString("Kits.Name."+kitName+".Display Material"));
			kit.setPrice(cfg.getInt("Kits.Name."+kitName+".Price"));
			kit.setPremiumOnly(cfg.getBoolean("Kits.Name."+kitName+".Premium Only"));
			kit.setEventOnly(cfg.getBoolean("Kits.Name."+kitName+".Event Only"));
			
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
		item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
		kitInv.setItem(0, item);
		
		item = new ItemStack(Material.BOW);
		meta = item.getItemMeta();
		meta.setLore(lore);
		item.setItemMeta(meta);
		kitInv.setItem(1, item);
		
		meta = item.getItemMeta();
		item = new ItemStack(Material.ARROW, 64);
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
		meta = item.getItemMeta();
		meta.setLore(lore);
		item.setItemMeta(meta);
		armor.put("Boots", item);
		
		/* PotionEffects */
		PotionEffect regeneration = new PotionEffect(PotionEffectType.REGENERATION, 10*20, 10*20);
		potionEffects.put("Regeneration", regeneration);
		CreditsBooster booster = new CreditsBooster(0.5D);
		ValidTime vTime = new ValidTime();
		vTime.setDay(0);
		vTime.setHour(1);
		vTime.setMinute(10);
		vTime.setSecond(0);
		booster.setValidTime(vTime);
		Kit kit = new Kit(null, default_kit_name, kitInv, armor, potionEffects, Material.STONE_SWORD, 50, 0.25f, false, false);
		kit.setSpecialItem(0, booster);
		kit.setDisplayName("§a"+default_kit_name);
		saveKit(kit);
	}

	public static void openKitsMenu(Player player) {
		Inventory showInv = Bukkit.createInventory(null, 54, "Kits");
		
		File file = new File(KitPvp.getKits_path());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		File kitsfile = new File(KitPvp.getKitsList_path());
		FileConfiguration kitscfg = YamlConfiguration.loadConfiguration(kitsfile);
		ArrayList<String> kits = new ArrayList<String>();
		kits = (ArrayList<String>) kitscfg.getStringList("Kits.List");
		ArrayList<String> lore = new ArrayList<String>();
		if(kits.isEmpty()) {
			ItemStack displayItem = new ItemStack(Material.STONE);
			ItemMeta meta = displayItem.getItemMeta();
			lore.clear();
			lore.add("§c§oEs gibt der Zeit leider keine Kits");
			meta.setDisplayName("§7Ups..");
			meta.setLore(lore);
			displayItem.setItemMeta(meta);
			for(int i = 0; i != showInv.getSize(); i++) {
				showInv.setItem(i, displayItem);
			}
		}else {
			for(String kitname : kits) {
				if(cfg.getString("Kits.Name."+kitname+".Creator") != null) {
					
					ArrayList<String> ownKits = KitPvp.getInstance().getProfiler().getProfile(player).getOwnKits();
					boolean hasKit = ownKits.contains(kitname);
					
					ItemStack displayItem = new ItemStack(Material.valueOf(cfg.getString("Kits.Name."+kitname+".Display Material")));
//					String displayname = cfg.getString("Kits.Name."+kitname+".Display Name");
					String displayname = kitname;
					int price = cfg.getInt("Kits.Name."+kitname+".Price");
					float walkspeed = (float)cfg.getDouble("Kits.Name."+kitname+".Player Walkspeed");
					boolean eventOnly = cfg.getBoolean("Kits.Name."+kitname+".Event Only");
					boolean premiumOnly = cfg.getBoolean("Kits.Name."+kitname+".Premium Only");
					
					lore.clear();
					lore.add("");
					if(premiumOnly) lore.add("§7Premium exkl.: §aJa");
					else lore.add("§7Premium exkl.: §cNein");
					if(eventOnly) lore.add("§7Event exkl.: §aJa");
					else lore.add("§7Event exkl.: §cNein");
					lore.add("§7Spieler Geschwindigkeit: §bx"+(int)(walkspeed/0.2));
					lore.add("");
					lore.add("§aPreis: §7"+price+CurrencyManager.currency);
					lore.add("");
					lore.add("§b§oRechtsklick §f§oum das Kit anzuschauen");
					if(hasKit) lore.add("§a§nDu besitzt dieses Kit!");
					else lore.add("§b§oShift + Linksklick §f§oum das Kit zu kaufen");
					
					
					ItemMeta meta = displayItem.getItemMeta();
					meta.setDisplayName(displayname);
					meta.setLore(lore);
					meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					displayItem.setItemMeta(meta);
					showInv.addItem(displayItem);
				}
			}
		}
		player.openInventory(showInv);
	}
	
	public boolean kitExist(String kitname) {
		File kitsfile = new File(KitPvp.getKitsList_path());
		FileConfiguration kitscfg = YamlConfiguration.loadConfiguration(kitsfile);
		ArrayList<String> kits = (ArrayList<String>)kitscfg.getStringList("Kits.List");
		if(kits != null && !kits.isEmpty() && kits.contains(kitname)) return true;
		else return false;
	}
	
}
