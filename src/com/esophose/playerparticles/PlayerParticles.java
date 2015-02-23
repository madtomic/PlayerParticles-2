package com.esophose.playerparticles;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.esophose.playerparticles.ConfigManager;
import com.esophose.playerparticles.MessageManager;
import com.esophose.playerparticles.ParticleCreator;
import com.esophose.playerparticles.ParticleEffect.ParticleType;
import com.esophose.playerparticles.PermissionHandler;

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
		startTasks();
	}
	
	public static Plugin getPlugin(){
		return Bukkit.getPluginManager().getPlugin("PlayerParticles");
	}
	
	private void startTasks() {
		double ticks = getConfig().getDouble("ticks-per-particle");
		if(ticks == 0.5){
			new ParticleCreator().runTaskTimer(this, 20, 1);
			new ParticleCreator().runTaskTimer(this, 20, 1);
		}else
			new ParticleCreator().runTaskTimer(this, 20, (long) ticks);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) return true;
		Player p = (Player) sender;
		if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
			if(!sender.hasPermission("playerparticles.reload")) return true;
			Bukkit.getScheduler().cancelTasks(this);
			MessageManager.getInstance().reload();
			if(getConfig().getDouble("version") != Double.parseDouble(getDescription().getVersion())){
				File configFile = new File(getDataFolder(), "config.yml");
				configFile.delete();
				saveDefaultConfig();
				getLogger().warning("config.yml has been updated!");
			}
			reloadConfig();
			startTasks();
			MessageManager.getInstance().sendMessage(p, (String)getConfig().get("message-reload"), ChatColor.GREEN);
			return true;
		}
		if(args.length != 1){
			MessageManager.getInstance().sendMessage(p, (String)getConfig().get("message-invalid-arguments") + ChatColor.GREEN + " /pp list", ChatColor.RED);
			return true;
		}
		String argument = args[0].replace("_", "");
		if(ParticleCreator.particleFromString(argument) != null){
			ParticleType effect = ParticleCreator.particleFromString(argument);
			if(!PermissionHandler.hasPermission(p, effect) && !p.hasPermission("playerparticles.*")){
				MessageManager.getInstance().sendMessage(p, ((String)getConfig().get("message-no-permission")).replace("{PARTICLE}", ChatColor.AQUA + effect.getName().toLowerCase() + ChatColor.RED), ChatColor.RED);
				return true;
			}
			ConfigManager.getInstance().setParticle(effect, p);
			ParticleCreator.addMap(p, effect);
			MessageManager.getInstance().sendMessage(p, ((String)getConfig().get("message-now-using")).replace("{PARTICLE}", ChatColor.AQUA + (effect.equals(ParticleType.RAINBOW) ? "rainbow" : effect.getName().toLowerCase())), ChatColor.GREEN);
			return true;
		}
		if(argument.equalsIgnoreCase("clear")){
			ConfigManager.getInstance().resetParticle(p);
			ParticleCreator.removeMap(p);
			MessageManager.getInstance().sendMessage(p, (String)getConfig().get("message-cleared-particles"), ChatColor.GREEN);
			return true;
		}
		if(argument.equalsIgnoreCase("list")){
			String toSend = getConfig().get("message-use") + "";
			for(ParticleType effect : ParticleType.values()){
				if(PermissionHandler.hasPermission(p, effect) || p.hasPermission("playerparticles.*")){
					toSend = toSend + (effect.equals(ParticleType.RAINBOW) ? "rainbow" : effect.getName().toLowerCase()) + ", ";
					continue;
				}
			}
			if(toSend.equals(getConfig().get("message-use") + "")){
				MessageManager.getInstance().sendMessage(p, (String)getConfig().get("message-no-particles"), ChatColor.RED);
				return true;
			}
			toSend = toSend + "clear";
			MessageManager.getInstance().sendMessage(p, toSend, ChatColor.GREEN);
			MessageManager.getInstance().sendMessage(p, (String)getConfig().get("message-usage") + ChatColor.AQUA + " /pp <Type>", ChatColor.YELLOW);
			return true;
		}
		
		MessageManager.getInstance().sendMessage(p, (String)getConfig().get("message-invalid-type") + ChatColor.GREEN + " /pp list", ChatColor.RED);
		
		return true;
	}
	
}
