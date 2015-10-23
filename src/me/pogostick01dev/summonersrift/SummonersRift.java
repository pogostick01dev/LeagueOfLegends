package me.pogostick01dev.summonersrift;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.pogostick01dev.summonersrift.listeners.BlockBreak;
import me.pogostick01dev.summonersrift.listeners.EntityDamage;
import me.pogostick01dev.summonersrift.listeners.PlayerLeaveArena;
import me.pogostick01dev.summonersrift.listeners.PlayerMove;
import me.pogostick01dev.summonersrift.listeners.SignManager;

public class SummonersRift extends JavaPlugin {

	@Override
	public void onEnable() {
		ArenaManager.getInstance().setup();
		
		getCommand("summonersrift").setExecutor(new CommandManager());
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new EntityDamage(), this);
		pm.registerEvents(new PlayerLeaveArena(), this);
		pm.registerEvents(new PlayerMove(), this);
		pm.registerEvents(new SignManager(), this);
	}
	
	public static Plugin getPlugin() {
		return Bukkit.getServer().getPluginManager().getPlugin("SummonersRift");
	}
}
