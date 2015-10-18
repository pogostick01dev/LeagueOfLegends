package me.pogostick01dev.summonersrift.cmds;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.pogostick01dev.summonersrift.Arena;
import me.pogostick01dev.summonersrift.ArenaManager;
import me.pogostick01dev.summonersrift.CommandInfo;
import me.pogostick01dev.summonersrift.GameCommand;

@CommandInfo(description = "Join a game.", usage = "<arenaName>", aliases = { "join", "j" })
public class Join extends GameCommand {

	@Override
	public void onCommand(Player p, String[] args) {
		if (ArenaManager.getInstance().getArena(p) != null) {
			p.sendMessage(ChatColor.RED + "You are already in a game.");
			return;
		}
		
		if (args.length == 0) {
			p.sendMessage(ChatColor.RED + "You must specify the arena to join.");
			return;
		}
		
		Arena a = ArenaManager.getInstance().getArena(args[0]);
		
		if (a == null) {
			p.sendMessage(ChatColor.RED + "An arena by that name does not exist.");
			return;
		}
		
		a.addPlayer(p);
	}
}
