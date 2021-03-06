package me.pogostick01dev.leagueoflegends.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.pogostick01dev.leagueoflegends.Arena;
import me.pogostick01dev.leagueoflegends.ArenaManager;
import me.pogostick01dev.leagueoflegends.Arena.ArenaState;

public class EntityDamage implements Listener {

	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		
		Player p = (Player) e.getEntity();
		
		Arena a = ArenaManager.getInstance().getArena(p);
		
		if (a == null || a.getState() == ArenaState.STARTED) {
			return;
		}
		
		e.setCancelled(true);
	}
}
