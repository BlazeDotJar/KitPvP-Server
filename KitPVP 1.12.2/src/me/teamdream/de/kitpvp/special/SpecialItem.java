package me.teamdream.de.kitpvp.special;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.teamdream.de.kitpvp.ValidTime;

public abstract class SpecialItem {
	
	protected Player owner = null;
	protected ValidTime validTime = null;
	protected SpecialItemType type = SpecialItemType.NONE;
	protected double multiplier = 0.0D;
	protected ItemStack displayItem = new ItemStack(Material.AIR);
	
	public SpecialItem(Player owner, ValidTime validTime) {
		this.owner = owner;
		this.validTime = validTime;
		this.displayItem = new ItemStack(Material.WATCH);
		ItemMeta meta = this.displayItem.getItemMeta();
		meta.setDisplayName("Kein Titel");
		this.displayItem.setItemMeta(meta);
	}
	public SpecialItem() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract boolean isExpired(String timeStamp);
	
	public Player getOwner() {
		return owner;
	}
	
	public ValidTime getValidTime() {
		return validTime;
	}
	
	public SpecialItemType getType() {
		return this.type;
	}
	
	public double getMultiplier() {
		return multiplier;
	}
	public ItemStack getDisplayItem() {
		return displayItem;
	}
	
}
