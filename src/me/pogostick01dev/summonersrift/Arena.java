package me.pogostick01dev.summonersrift;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Arena {

	public enum ArenaState {
		WAITING, PICKING, STARTED
	}
	
	private String id;
	private ArenaState state;
	
	private ArrayList<Location> spawns;
	
	private ArrayList<Player> players;
	
	protected Arena(String id) {
		this.id = id;
		this.state = ArenaState.WAITING;
		
		this.spawns = new ArrayList<Location>();
		
		SettingsManager.getArenas().<ConfigurationSection>get(id + ".spawns").getKeys(false).stream().forEach((spawnID) -> {
			spawns.add(SettingsManager.getArenas().<Location>get(id + ".spawns." + spawnID));
		});
		
		this.players = new ArrayList<Player>();
	}
	
	public String getID() {
		return id;
	}
	
	public ArenaState getState() {
		return state;
	}
	
	public Player[] getPlayers() {
		return players.toArray(new Player[players.size()]);
	}
	
	public boolean hasPlayer(Player p) {
		return players.contains(p);
	}
	
	public void addPlayer(Player p) {
		players.add(p);
		p.teleport(spawns.get(players.size() - 1));
	}
	
	public void addSpawn(Location loc) {
		spawns.add(loc);
	}
}
