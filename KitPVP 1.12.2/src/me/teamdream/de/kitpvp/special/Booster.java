package me.teamdream.de.kitpvp.special;

import org.bukkit.entity.Player;

import me.teamdream.de.kitpvp.ValidTime;

public abstract class Booster extends SpecialItem {
	
	
	
	public Booster(Player owner, ValidTime validTime) {
		super(owner, validTime);
	}
	public Booster() {
		super();
	}

	@Override
	public boolean isExpired(String timeStamp) {
		return this.getValidTime().compareTime(timeStamp);
	}
	
	public void setValidTime(ValidTime validTime) {
		this.validTime = validTime;
	}
	
	public abstract int apply(int value);
	public abstract double apply(double value);
	public abstract float apply(float value);

}
