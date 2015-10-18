package me.pogostick01dev.summonersrift.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.pogostick01dev.summonersrift.Arena;
import me.pogostick01dev.summonersrift.Arena.ArenaState;
import me.pogostick01dev.summonersrift.ArenaManager;

public class PlayerMove implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Arena a = ArenaManager.getInstance().getArena(e.getPlayer());
		
		if (a == null || a.getState() == ArenaState.STARTED) {
			return;
		}
		
		if (e.getTo().getX() == e.getFrom().getX() && e.getTo().getZ() == e.getFrom().getZ()) {
			return;
		}
		e.setTo(e.getFrom());
	}
}
