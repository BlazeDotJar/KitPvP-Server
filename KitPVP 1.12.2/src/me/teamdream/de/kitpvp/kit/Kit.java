package me.teamdream.de.kitpvp.kit;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import me.teamdream.de.kitpvp.special.SpecialItem;


public class Kit {
	
	@SuppressWarnings("unused")
	private String creator=null;
	private String creatorName = "";
	private String kitName="";
	private String kitDisplayName="";
	private Inventory inv=null;
	private HashMap<String, ItemStack> armor=new HashMap<String, ItemStack>();
	private HashMap<String, PotionEffect> potionEffects=new HashMap<String, PotionEffect>();
	private Material displayMaterial = Material.STONE;
	private int price = 0;
	private boolean premiumOnly = false;
	private boolean eventOnly = false;
	private float playerSpeed = 0.2f;
	
	private SpecialItem specialItemSlot1=null;
	private SpecialItem specialItemSlot2=null;
	private SpecialItem specialItemSlot3=null;
	private SpecialItem specialItemSlot4=null;
	private SpecialItem specialItemSlot5=null;
	private SpecialItem specialItemSlot6=null;
	private SpecialItem specialItemSlot7=null;
	private SpecialItem specialItemSlot8=null;
	private SpecialItem specialItemSlot9=null;
	
	public Kit(Player creator, String kitName, Inventory inv, HashMap<String, ItemStack> armor, HashMap<String, PotionEffect> potionEffects, Material displayMaterial, int price, float playerSpeed, boolean premiumOnly, boolean eventOnly) {
		if(creator == null) this.creatorName="Console";
		else{
			this.creatorName = creator.getName();
			this.creator = creator.getName();
		}
		this.kitName = kitName;
		this.kitDisplayName+=kitDisplayName;
		this.inv = inv;
		this.armor = armor;
		this.potionEffects = potionEffects;
		this.displayMaterial = displayMaterial;
		this.price = price;
		this.playerSpeed = playerSpeed;
		this.premiumOnly = premiumOnly;
		this.eventOnly = eventOnly;
	}
	public Kit(Player creator, String kitName) {
		if(creator == null) this.creatorName="Console";
		else{
			this.creatorName = creator.getName();
			this.creator = creator.getName();
		}
		this.kitName = kitName;
		this.kitDisplayName+=kitDisplayName;
	}

	public Kit(String creator, String kitName) {
		this.creator = creator;
		this.creatorName = creator;
		this.kitName = kitName;
		this.kitDisplayName+=kitDisplayName;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreator(Player creator) {
		if(creator == null) this.creatorName="Console";
		else{
			this.creatorName = creator.getName();
			this.creator = creator.getName();
		}
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getKitName() {
		return kitName;
	}
	public void setKitName(String kitName) {
		this.kitName = kitName;
		this.kitDisplayName+=kitDisplayName;
	}
	public Inventory getInv() {
		return inv;
	}
	public void setInv(Inventory inv) {
		this.inv = inv;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
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
	public void setDisplayMaterial(String material) {
		this.displayMaterial = Material.valueOf(material);
	}
	public void setDisplayMaterial(Material material) {
		this.displayMaterial = material;
	}
	public void setDisplayName(String displayname) {
		this.kitDisplayName+=displayname;
	}
	public Object getDisplayName() {
		return kitDisplayName;
	}
	public float getPlayerSpeed() {
		return playerSpeed;
	}
	public void setPlayerSpeed(float playerSpeed) {
		this.playerSpeed = playerSpeed;
	}
	public void setSpecialItem(int slot, SpecialItem specialItem) {
		switch(slot) {
		case 0:
			this.specialItemSlot1 = specialItem; break;
		case 1:
			this.specialItemSlot2 = specialItem; break;
		case 2:
			this.specialItemSlot3 = specialItem; break;
		case 3:
			this.specialItemSlot4 = specialItem; break;
		case 4:
			this.specialItemSlot5 = specialItem; break;
		case 5:
			this.specialItemSlot6 = specialItem; break;
		case 6:
			this.specialItemSlot7 = specialItem; break;
		case 7:
			this.specialItemSlot8 = specialItem; break;
		case 8:
			this.specialItemSlot9 = specialItem; break;
		}
	}
	public SpecialItem getSpecialItem(int slot) {
		switch(slot) {
		case 0:
			return this.specialItemSlot1;
		case 1:
			return this.specialItemSlot2;
		case 2:
			return this.specialItemSlot3;
		case 3:
			return this.specialItemSlot4;
		case 4:
			return this.specialItemSlot5;
		case 5:
			return this.specialItemSlot6;
		case 6:
			return this.specialItemSlot7;
		case 7:
			return this.specialItemSlot8;
		case 8:
			return this.specialItemSlot9;
		}
		return null;
	}
	
	
}
