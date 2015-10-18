package me.pogostick01dev.summonersrift.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.pogostick01dev.summonersrift.Arena;
import me.pogostick01dev.summonersrift.ArenaManager;

public class BlockBreak implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Arena a = ArenaManager.getInstance().getArena(e.getPlayer());
		
		if (a == null) {
			return;
		}
		
		e.setCancelled(true);
	}
}
