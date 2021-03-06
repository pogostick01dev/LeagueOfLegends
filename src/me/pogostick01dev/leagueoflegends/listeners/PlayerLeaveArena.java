package me.pogostick01dev.leagueoflegends.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.pogostick01dev.leagueoflegends.Arena;
import me.pogostick01dev.leagueoflegends.ArenaManager;

public class PlayerLeaveArena implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		handle(e.getPlayer());
	}
	
	private void handle(Player p) {
		Arena a = ArenaManager.getInstance().getArena(p);
		
		if (a == null) {
			return;
		}
		
		a.removePlayer(p);
	}
}
