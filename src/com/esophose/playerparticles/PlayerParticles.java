package com.esophose.playerparticles;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.esophose.playerparticles.ParticleEffect.ParticleType;

public class PlayerParticles extends JavaPlugin {

	public void onEnable(){
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(new ParticleCreator(), this);
		ParticleCreator.updateMap();
		if(getConfig().getDouble("version") != Double.parseDouble(getDescription().getVersion())){
			File configFile = new File(getDataFolder(), "config.yml");
			configFile.delete();
			saveDefaultConfig();
			reloadConfig();
			getLogger().warning("config.yml has been updated!");
		}
		double ticks = getConfig().getDouble("ticks-per-particle");
		if(ticks == 0.5){
			new ParticleCreator().runTaskTimer(this, 20, 1);
			new ParticleCreator().runTaskTimer(this, 20, 1);
		}else
			new ParticleCreator().runTaskTimer(this, 20, (long) ticks);
	}
	
	public static Plugin getPlugin(){
		return Bukkit.getPluginManager().getPlugin("PlayerParticles");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) return true;
		Player p = (Player) sender;
		if(args.length != 1){
			MessageManager.getInstance().sendMessage(p, "Invalid arguments! " + ChatColor.GREEN + "/pp list", ChatColor.RED);
			return true;
		}
		String argument = args[0].replace("_", "");
		if(ParticleCreator.particleFromString(argument) != null){
			ParticleType effect = ParticleCreator.particleFromString(argument);
			if(!PermissionHandler.hasPermission(p, effect) && !p.hasPermission("playerparticles.*")){
				MessageManager.getInstance().sendMessage(p, "You don't have permission to use type " + ChatColor.AQUA + effect.getName().toLowerCase() + ChatColor.RED + " particles!", ChatColor.RED);
				return true;
			}
			ConfigManager.getInstance().setParticle(effect, p);
			ParticleCreator.addMap(p, effect);
			MessageManager.getInstance().sendMessage(p, "Now using type " + ChatColor.AQUA + (effect.equals(ParticleType.RAINBOW) ? "rainbow" : effect.getName().toLowerCase()) + ChatColor.GREEN + " particles!", ChatColor.GREEN);
			return true;
		}
		if(argument.equalsIgnoreCase("clear")){
			ConfigManager.getInstance().resetParticle(p);
			ParticleCreator.removeMap(p);
			MessageManager.getInstance().sendMessage(p, "Cleared your particles!", ChatColor.GREEN);
			return true;
		}
		if(argument.equalsIgnoreCase("list")){
			String toSend = "You can use: ";
			for(ParticleType effect : ParticleType.values()){
				if(PermissionHandler.hasPermission(p, effect) || p.hasPermission("playerparticles.*")){
					toSend = toSend + (effect.equals(ParticleType.RAINBOW) ? "rainbow" : effect.getName().toLowerCase()) + ", ";
					continue;
				}
			}
			if(toSend.equals("You can use: ")){
				MessageManager.getInstance().sendMessage(p, "You don't have permission to use any particles!", ChatColor.RED);
				return true;
			}
			toSend = toSend + "clear";
			MessageManager.getInstance().sendMessage(p, toSend, ChatColor.GREEN);
			MessageManager.getInstance().sendMessage(p, "Usage: " + ChatColor.AQUA + "/pp <Type>", ChatColor.YELLOW);
			return true;
		}
		
		MessageManager.getInstance().sendMessage(p, "Invalid particle type! " + ChatColor.GREEN + "/pp list", ChatColor.RED);
		
		return true;
	}
	
}
