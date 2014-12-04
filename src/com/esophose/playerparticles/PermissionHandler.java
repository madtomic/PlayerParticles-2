package com.esophose.playerparticles;

import org.bukkit.entity.Player;

import com.esophose.playerparticles.ParticleEffect.ParticleType;

public class PermissionHandler {

	public static boolean hasPermission(Player player, ParticleType effect){
		if(player.hasPermission("playerparticles.*")) return true;
		if(player.hasPermission("playerparticles." + effect.getName().toLowerCase().replace("_", ""))) return true;
		return false;
	}
	
}
