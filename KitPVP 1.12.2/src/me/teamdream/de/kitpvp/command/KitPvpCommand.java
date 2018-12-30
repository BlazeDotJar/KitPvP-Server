package me.teamdream.de.kitpvp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.teamdream.de.kitpvp.KitPvp;
import me.teamdream.de.kitpvp.kit.KitManager;

public class KitPvpCommand implements CommandExecutor {

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