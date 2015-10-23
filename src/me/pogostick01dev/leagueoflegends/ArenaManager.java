package me.pogostick01dev.leagueoflegends;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class ArenaManager {
	
	private static ArenaManager instance = new ArenaManager();
	
	public static ArenaManager getInstance() {
		return instance;
	}
	
	private ArrayList<Arena> arenas;
	
	private ArenaManager() {
		this.arenas = new ArrayList<Arena>();
	}
	
	public void setup() {
		arenas.clear();
		
		SettingsManager.getArenas().getKeys().stream().forEach((arenaID) -> {
			arenas.add(new Arena(arenaID));
		});
	}
	
	public Arena getArena(String id) {
		for (Arena arena : arenas) {
			if (arena.getID().equals(id)) {
				return arena;
			}
		}
		
		return null;
	}
	
	public Arena getArena(Player p) {
		for (Arena arena : arenas) {
			if (arena.hasPlayer(p)) {
				return arena;
			}
		}
		
		return null;
	}
}
