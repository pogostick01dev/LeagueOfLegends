package me.pogostick01dev.leagueoflegends.cmds;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.pogostick01dev.leagueoflegends.Arena;
import me.pogostick01dev.leagueoflegends.ArenaManager;
import me.pogostick01dev.leagueoflegends.CommandInfo;
import me.pogostick01dev.leagueoflegends.GameCommand;

@CommandInfo(description = "Leave a game.", usage = "", aliases = { "leave", "l" })
public class Leave extends GameCommand {

	@Override
	public void onCommand(Player p, String[] args) {
		Arena a = ArenaManager.getInstance().getArena(p);
		
		if (ArenaManager.getInstance().getArena(p) == null) {
			p.sendMessage(ChatColor.RED + "You are not in a game.");
			return;
		}
		
		a.removePlayer(p);
	}
}
