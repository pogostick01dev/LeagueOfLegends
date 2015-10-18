package me.pogostick01dev.summonersrift.cmds;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.pogostick01dev.summonersrift.ArenaManager;
import me.pogostick01dev.summonersrift.CommandInfo;
import me.pogostick01dev.summonersrift.GameCommand;
import me.pogostick01dev.summonersrift.SettingsManager;

@CommandInfo(description = "Remove an arena.", usage = "<name>", aliases = { "removearena", "ra" })
public class RemoveArena extends GameCommand {

	@Override
	public void onCommand(Player p, String[] args) {
		if(args.length == 0) {
			p.sendMessage(ChatColor.RED + "You must specify the name of the arena.");
			return;
		}
		
		String name = args[0];
		
		if (ArenaManager.getInstance().getArena(name) != null) {
			p.sendMessage(ChatColor.RED + "An arena with that name does not exists.");
			return;
		}
		
		SettingsManager.getArenas().set(name, null);
	
		p.sendMessage(ChatColor.GREEN + "Removed arena " + name + ". This will be applied on reload.");
	}
}
