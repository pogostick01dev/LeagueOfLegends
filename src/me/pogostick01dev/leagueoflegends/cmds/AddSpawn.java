package me.pogostick01dev.leagueoflegends.cmds;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.pogostick01dev.leagueoflegends.Arena;
import me.pogostick01dev.leagueoflegends.ArenaManager;
import me.pogostick01dev.leagueoflegends.CommandInfo;
import me.pogostick01dev.leagueoflegends.GameCommand;
import me.pogostick01dev.leagueoflegends.SettingsManager;

@CommandInfo(description = "Add a spawn to an arena.", usage = "<arenaName>", aliases = { "addspawn", "as" })
public class AddSpawn extends GameCommand {

	@Override
	public void onCommand(Player p, String[] args) {
		if (args.length == 0) {
			p.sendMessage(ChatColor.RED + "You must specify the arena to which the spawn will be added.");
			return;
		}
		
		Arena a = ArenaManager.getInstance().getArena(args[0]);
		
		if (a == null) {
			p.sendMessage(ChatColor.RED + "An arena with that name does not exist.");
			return;
		}

		a.addSpawn(p.getLocation());
		
		int size = SettingsManager.getArenas().<ConfigurationSection>get(a.getID() + ".spawns").getKeys(false).size();

		SettingsManager.getArenas().set(a.getID() + ".spawns." + size, p.getLocation());
		p.sendMessage(ChatColor.GREEN + "Added spawn.");
	}
}
