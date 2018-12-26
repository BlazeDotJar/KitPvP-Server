package me.teamdream.de.kitpvp.kit;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Kit {
	
	@SuppressWarnings("unused")
	private Player creator=null;
	private String creatorName = "";
	private String kitName="";
	private Inventory inv=null;
	private double price = 0;
	private boolean premiumOnly = false;
	private boolean eventOnly = false;
	public Kit(Player creator, String kitName, Inventory inv, double price, boolean premiumOnly, boolean eventOnly) {
		super();
		this.creator = creator;
		if(creator == null) this.creatorName="Console";
		else this.creatorName = creator.getName();
		this.kitName = kitName;
		this.inv = inv;
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
	
	
	
}
