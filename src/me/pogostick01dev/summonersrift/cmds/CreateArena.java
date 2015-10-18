package me.pogostick01dev.summonersrift.cmds;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.pogostick01dev.summonersrift.ArenaManager;
import me.pogostick01dev.summonersrift.CommandInfo;
import me.pogostick01dev.summonersrift.GameCommand;
import me.pogostick01dev.summonersrift.SettingsManager;

@CommandInfo(description = "Create an arena.", usage = "<name>", aliases = { "createarena", "ca" })
public class CreateArena extends GameCommand {

	@Override
	public void onCommand(Player p, String[] args) {
		if(args.length == 0) {
			p.sendMessage(ChatColor.RED + "You must specify a name for the arena.");
			return;
		}
		
		String name = args[0];
		
		if (ArenaManager.getInstance().getArena(name) != null) {
			p.sendMessage(ChatColor.RED + "An arena with that name already exists.");
			return;
		}
		
		SettingsManager.getArenas().createSection(name);
	
		p.sendMessage(ChatColor.GREEN + "Created arena " + name + ". Now you must set up the spawns.");
	}
}
