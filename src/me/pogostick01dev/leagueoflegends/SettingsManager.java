package me.pogostick01dev.leagueoflegends;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SettingsManager {
	
	private static final SettingsManager 
			configuration = new SettingsManager("config"), 
			arenas = new SettingsManager("arenas"),
			signs = new SettingsManager("signs");
	
	public static SettingsManager getConfig() {
		return configuration;
	}
	
	public static SettingsManager getArenas() {
		return arenas;
	}
	
	public static SettingsManager getSigns() {
		return signs;
	}
	
	private File file;
	private FileConfiguration config;
	
	private SettingsManager(String fileName) {
		if (!Main.getPlugin().getDataFolder().exists()) {
			Main.getPlugin().getDataFolder().mkdir();
		}
		
		file = new File(Main.getPlugin().getDataFolder(), fileName + ".yml");
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String path) {
		return (T) config.get(path);
	}
	
	public Set<String> getKeys() {
		return config.getKeys(false);
	}
	
	public void set(String path, Object value) {
		config.set(path, value);
		save();
	}
	
	public boolean contains(String path) {
		return config.contains(path);
	}
	
	public ConfigurationSection createSection(String path) {
		ConfigurationSection section = config.createSection(path);
		save();
		return section;
	}
	
	private void save() {
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
