package me.pogostick01dev.leagueoflegends;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

public class LobbySign implements ConfigurationSerializable {
	
	private Location loc;
	private Sign sign;
	private Arena arena;
	
	public LobbySign(Location loc, Arena arena) {
		this.loc = loc;
		this.sign = (Sign) loc.getBlock().getState();
		this.arena = arena;
	}

	public Location getLocation() {
		return loc;
	}

	public Sign getSign() {
		return sign;
	}

	public Arena getArena() {
		return arena;
	}
	
	public void update() {
		sign.setLine(0, "[SummonersRift]");
		sign.setLine(1, arena.getID());
		sign.setLine(2, arena.getState().toString());
		sign.setLine(3, arena.getPlayers().length + "/" + arena.getMaxPlayers());
		sign.update();
	}
	
	@Override
	public Map<String, Object> serialize() {
		HashMap<String, Object> data = new HashMap<>();
		
		data.put("location", loc);
		data.put("arena", arena.getID());
		
		return data;
	}
}
