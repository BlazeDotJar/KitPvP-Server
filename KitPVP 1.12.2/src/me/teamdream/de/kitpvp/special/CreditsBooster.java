package me.teamdream.de.kitpvp.special;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.teamdream.de.kitpvp.ValidTime;

public class CreditsBooster extends Booster {

	public CreditsBooster(Player owner, ValidTime validTime, double multiplier) {
		super(owner, validTime);
		this.multiplier = multiplier;
		this.type = SpecialItemType.CREDITS_BOOSTER;
		ItemStack i = new ItemStack(Material.WATCH);
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName("§5CREDITS-BOOSTER");
		i.setItemMeta(meta);
		this.displayItem = i;
	}
	public CreditsBooster(double multiplier) {
		super();
		this.multiplier = multiplier;
		this.type = SpecialItemType.CREDITS_BOOSTER;
	}

	@Override
	public int apply(int value) {
		value = (int) (value+(value*multiplier));
		return value;
	}

	@Override
	public double apply(double value) {
		value = (double) (value+(value*multiplier));
		return value;
	}

	@Override
	public float apply(float value) {
		value = (float) (value+(value*multiplier));
		return value;
	}

}
