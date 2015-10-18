package me.pogostick01dev.summonersrift;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/*
- Create Arena
- Remove Arena
  Add Spawn
  Join
  Leave
 */
public class SummonersRift extends JavaPlugin {

	@Override
	public void onEnable() {
		ArenaManager.getInstance().setup();
		
		getCommand("summonersrift").setExecutor(new CommandManager());
	}
	
	public static Plugin getPlugin() {
		return Bukkit.getServer().getPluginManager().getPlugin("SummonersRift");
	}
}
