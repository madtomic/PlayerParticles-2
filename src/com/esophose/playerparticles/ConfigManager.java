package com.esophose.playerparticles;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.esophose.playerparticles.ParticleEffect.ParticleType;

public class ConfigManager {
	
	private static ConfigManager instance = new ConfigManager("effectData");
	private File file;
	private FileConfiguration config;
	
	public static ConfigManager getInstance() {
		return instance;
	}
	
	private ConfigManager(String fileName) {		
		if (!PlayerParticles.getPlugin().getDataFolder().exists()) PlayerParticles.getPlugin().getDataFolder().mkdir();
		
		file = new File(PlayerParticles.getPlugin().getDataFolder(), fileName + ".yml");
		
		if (!file.exists()) {
			try { file.createNewFile(); }
			catch (Exception e) { e.printStackTrace(); }
		}
		
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	public void setParticle(ParticleType type, Player player){
		config.set(player.getName(), type.toString().toLowerCase().replace("_", ""));
		try {config.save(file);}
		catch (IOException e) {e.printStackTrace();}
	}
	
	public void resetParticle(Player player){
		config.set(player.getName(), null);
		try {config.save(file);}
		catch (IOException e) {e.printStackTrace();}
	}
	
	public ParticleType getParticle(Player player){
		String effectToLowerCase = (String) config.getString(player.getName());
		return ParticleCreator.particleFromString(effectToLowerCase);
	}
	
}
