package me.teamdream.de.kitpvp.event;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.teamdream.de.kitpvp.KitPvp;

public class InventoryListener {
	
	public static void onClick(InventoryClickEvent e) {
		@SuppressWarnings("unused")
		Player p = (Player)e.getWhoClicked();
		/*
		 * 
		 * Überprüfen, ob der Spieler im Kampfgebiet ist
		 * 					&
		 * überprüfen, ob der Spieler versucht hat ein Item
		 * in seinem Inventar zur verschieben
		 * 
		 * 
		 * */
		//e.getClickedInventory().getTitle().equals("§8Kits")
		if(e.getClickedInventory() != null) {
			e.setCancelled(true);
			if(e.getClick() == ClickType.CREATIVE) e.setCancelled(false);
			else if(e.getSlot() >= 0 && e.getSlot() <= 4) e.setCancelled(false);
			if(e.getClickedInventory().getTitle().equals("Kits")) {
				e.setCancelled(true);
				if(e.getCurrentItem() != null) {
					ItemStack item = e.getCurrentItem();
					if(item.hasItemMeta()) {
						ItemMeta meta = item.getItemMeta();
						String kitname = meta.getDisplayName();
						KitPvp.getInstance().getKitManager().loadKit((Player)e.getWhoClicked(), kitname);
					}
				}
			}
		}
	}
	
}
