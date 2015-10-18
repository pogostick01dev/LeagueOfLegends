package me.pogostick01dev.summonersrift;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
	
	protected void setState(ArenaState state) {
		this.state = state;
	}
	
	public Player[] getPlayers() {
		return players.toArray(new Player[players.size()]);
	}
	
	public boolean hasPlayer(Player p) {
		return players.contains(p);
	}
	
	public void addPlayer(Player p) {
		if (players.size() + 1 > 10) {
			p.sendMessage(ChatColor.RED + "This arena is full");
		}
		
		players.add(p);
		p.teleport(spawns.get(players.size() - 1));
		p.sendMessage(ChatColor.GREEN + "You have joined arena " + id + ".");
		
		if (players.size() >= spawns.size() * 5 && state == ArenaState.WAITING) {
			this.state = ArenaState.PICKING;
			new Countdown(this, 60, 60, 30, 20, 10, 5, 4, 3, 2, 1).runTaskTimer(SummonersRift.getPlugin(), 0, 1000);
		}
	}
	
	public void removePlayer(Player p) {
		players.remove(p);
		p.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation()); // TODO: Temporary
	
		if (players.size() <= 1) {
			if (players.size() == 1) {
				Bukkit.getServer().broadcastMessage(players.get(0).getName() + " has won on arena " + id + "!");
			}
			
			else {
				Bukkit.getServer().broadcastMessage("Arena " + id + " has ended without a winner");
			}
			
			state = ArenaState.WAITING;
			players.clear();
		}
	}
	
	public void addSpawn(Location loc) {
		spawns.add(loc);
	}
}
