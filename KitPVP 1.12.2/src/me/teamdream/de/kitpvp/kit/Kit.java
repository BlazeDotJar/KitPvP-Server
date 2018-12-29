package me.teamdream.de.kitpvp.kit;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;


public class Kit {
	
	@SuppressWarnings("unused")
	private Player creator=null;
	private String creatorName = "";
	private String kitName="";
	private Inventory inv=null;
	private HashMap<String, ItemStack> armor=new HashMap<String, ItemStack>();
	private HashMap<String, PotionEffect> potionEffects=new HashMap<String, PotionEffect>();
	private Material displayMaterial = Material.STONE;
	private double price = 0;
	private boolean premiumOnly = false;
	private boolean eventOnly = false;
	public Kit(Player creator, String kitName, Inventory inv, HashMap<String, ItemStack> armor, HashMap<String, PotionEffect> potionEffects, Material displayMaterial, double price, boolean premiumOnly, boolean eventOnly) {
		this.creator = creator;
		if(creator == null) this.creatorName="Console";
		else this.creatorName = creator.getName();
		this.kitName = kitName;
		this.inv = inv;
		this.armor = armor;
		this.potionEffects = potionEffects;
		this.displayMaterial = displayMaterial;
		this.price = price;
		this.premiumOnly = premiumOnly;
		this.eventOnly = eventOnly;
	}
	public Kit(Player creator, String kitName) {
		this.creator = creator;
		if(creator == null) this.creatorName="Console";
		else this.creatorName = creator.getName();
		this.kitName = kitName;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreator(Player creator) {
		this.creator = creator;
		if(creator == null) this.creatorName = "Console";
		else this.creatorName = creator.getName();
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getKitName() {
		return kitName;
	}
	public void setKitName(String kitName) {
		this.kitName = kitName;
	}
	public Inventory getInv() {
		return inv;
	}
	public void setInv(Inventory inv) {
		this.inv = inv;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isPremiumOnly() {
		return premiumOnly;
	}
	public void setPremiumOnly(boolean premiumOnly) {
		this.premiumOnly = premiumOnly;
	}
	public boolean isEventOnly() {
		return eventOnly;
	}
	public void setEventOnly(boolean eventOnly) {
		this.eventOnly = eventOnly;
	}
	public HashMap<String, ItemStack> getArmor() {
		return armor;
	}
	public HashMap<String, PotionEffect> getPotionEffects() {
		return potionEffects;
	}
	public Material getDisplayMaterial() {
		return displayMaterial;
	}
	
	
}
