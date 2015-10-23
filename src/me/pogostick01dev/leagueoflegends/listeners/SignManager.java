package me.pogostick01dev.leagueoflegends.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.pogostick01dev.leagueoflegends.Arena;
import me.pogostick01dev.leagueoflegends.ArenaManager;
import me.pogostick01dev.leagueoflegends.LobbySign;
import me.pogostick01dev.leagueoflegends.SettingsManager;
import me.pogostick01dev.leagueoflegends.Main;

public class SignManager implements Listener {

	private ArrayList<LobbySign> signs;
	
	public SignManager() {
		signs = new ArrayList<>();
		
		SettingsManager.getSigns().getKeys().stream().forEach((key) -> {
			ConfigurationSection s = SettingsManager.getSigns().get(key);
			
			Location loc = (Location) s.get("location"); // TODO: Fix?
			Arena arena = ArenaManager.getInstance().getArena(s.getString("arena"));
			
			if (loc.getBlock() == null || !(loc.getBlock().getState() instanceof Sign)) {
				SettingsManager.getSigns().set(key, null);
				System.err.println("Removed broken sign at location " + loc + ".");
			}
			
			if (arena == null) {
				SettingsManager.getSigns().set(key, null);
				System.err.println("Removed sign for nonexistent arena at location " + loc + ".");
			}
			
			signs.add(new LobbySign(loc, arena));
		});
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			public void run() {
				signs.stream().forEach((sign) -> {
					sign.update();
				});
			}
		}, 0, 20);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		
		Block b = e.getClickedBlock();
		Material type = b.getType();
		
		if (type != Material.SIGN && type != Material.WALL_SIGN && type != Material.SIGN_POST) {
			return;
		}
	
		for (LobbySign sign : signs){
			if (sign.getLocation().equals(b.getLocation())) {
				sign.getArena().addPlayer(e.getPlayer());
				break;
			}
		}
	}
}
