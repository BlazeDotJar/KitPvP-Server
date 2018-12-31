package me.teamdream.de.kitpvp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.teamdream.de.kitpvp.KitPvp;
import me.teamdream.de.kitpvp.kit.KitManager;

public class KitPvpCommand implements CommandExecutor {
	private KitPvp kitpvp = null;
	
	public KitPvpCommand(KitPvp kitpvp) {
		this.kitpvp = kitpvp;
		kitpvp.getCommand("kit").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		switch(args.length) { 
		case 0:
			if(sender.hasPermission("kitpvp.command.kit")) {
				if(sender instanceof Player) {
					KitManager.openKitsMenu(((Player)sender));
				}else sender.sendMessage("§cDu bist kein Spieler");
			}else sender.sendMessage(KitPvp.noPermission);
			break;
		case 1:
			if(sender.hasPermission("kitpvp.command.kit.give")) {
				if(sender instanceof Player) {
					String kitname = args[0];
					if(kitpvp.getKitManager().kitExist(kitname)){
						if(kitpvp.getKitManager().loadKit(((Player)sender), kitname)) KitPvp.sendMessage(((Player)sender), "§fDu hast das Kit §a"+kitname+" §ferhalten");
						else KitPvp.sendMessage(((Player)sender), "§cUps...", "§cEs ist ein Fehler aufgetreten beim Versuch dir ein Kit zu laden :C");
					}else KitPvp.sendMessage(((Player)sender), "§cDieses Kit existiert nicht");
				}else sender.sendMessage("§cDu bist kein Spieler");
			}else sender.sendMessage(KitPvp.noPermission);
			break;
		}
		
		return false;
	}
	
	public boolean hasPermission(CommandSender sender, String permission) {
		if(sender instanceof ConsoleCommandSender) return true;
		else return sender.hasPermission(permission);
	}
	public void sendCommandInfo(CommandSender sender) {
		sender.sendMessage("§a/kit help");
		sender.sendMessage("§a/kit <Kit> <Spieler>");
		sender.sendMessage("§a/kit create [Name]");
		sender.sendMessage("§a/kit displayitem [Kitname]");
	}
	
}