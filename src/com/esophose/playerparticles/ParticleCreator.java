package com.esophose.playerparticles;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.esophose.playerparticles.ParticleEffect.ParticleType;

public class ParticleCreator extends BukkitRunnable implements Listener {
	
	private static HashMap<String, ParticleType> map = new HashMap<String, ParticleType>();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		if(ConfigManager.getInstance().getParticle(e.getPlayer()) == null) return;
		map.put(e.getPlayer().getName(), ConfigManager.getInstance().getParticle(e.getPlayer()));
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e){
		if(map.containsKey(e.getPlayer().getName())){
			map.remove(e.getPlayer().getName());
		}
	}
	
	public static void addMap(Player player, ParticleType effect){
		map.remove(player.getName());
		map.put(player.getName(), ConfigManager.getInstance().getParticle(player));
	}
	
	public static void removeMap(Player player){
		map.remove(player.getName());
	}
	
	public static void updateMap(){
		map.clear();
		for(Player player : Bukkit.getOnlinePlayers()){
			if(ConfigManager.getInstance().getParticle(player) == null) continue;
			map.put(player.getName(), ConfigManager.getInstance().getParticle(player));
		}
	}
	
	public static ParticleType particleFromString(String particle){
		for(ParticleType effect : ParticleType.values()){
			if(effect.toString().toLowerCase().replace("_", "").equals(particle)) return effect;
		}
		return null;
	}

	@Override
	public void run() {
		for(Player player : Bukkit.getOnlinePlayers()){
			if(!map.containsKey(player.getName())) continue;
			ParticleType effect = map.get(player.getName());
			if(PermissionHandler.hasPermission(player, effect)){
				Location loc = player.getLocation();
				loc.setY(loc.getY() + 1);
				displayParticle(effect, loc);
			}
		}
	}
	
	private void displayParticle(ParticleType effect, Location location){
		if(effect.equals(ParticleType.ANGRY_VILLAGER)){
			ParticleEffect particle = new ParticleEffect(effect, 0.6F, 0.6F, 0.6F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.BUBBLE)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.CLOUD)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.CRIT)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.DEPTH_SUSPEND)){
			ParticleEffect particle = new ParticleEffect(effect, 0.5F, 0.5F, 0.5F, 0.0F, 5);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.DRIP_LAVA)){
			ParticleEffect particle = new ParticleEffect(effect, 0.6F, 0.6F, 0.6F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.DRIP_WATER)){
			ParticleEffect particle = new ParticleEffect(effect, 0.6F, 0.6F, 0.6F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.ENCHANTMENT_TABLE)){
			ParticleEffect particle = new ParticleEffect(effect, 0.6F, 0.6F, 0.6F, 0.05F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.EXPLODE)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.FIREWORKS_SPARK)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.FLAME)){
			ParticleEffect particle = new ParticleEffect(effect, 0.1F, 0.1F, 0.1F, 0.05F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.FOOTSTEP)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.HAPPY_VILLAGER)){
			ParticleEffect particle = new ParticleEffect(effect, 0.5F, 0.5F, 0.5F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.HEART)){
			ParticleEffect particle = new ParticleEffect(effect, 0.6F, 0.6F, 0.6F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.HUGE_EXPLOSION)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.INSTANT_SPELL)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.LARGE_EXPLODE)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.LARGE_SMOKE)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.LAVA)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.MAGIC_CRIT)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.MOB_SPELL)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.MOB_SPELL_AMBIENT)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.NOTE)){
			ParticleEffect particle = new ParticleEffect(effect, 0.6F, 0.6F, 0.6F, 1.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.PORTAL)){
			ParticleEffect particle = new ParticleEffect(effect, 0.5F, 0.5F, 0.5F, 0.05F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.RAINBOW)){
			ParticleEffect particle = new ParticleEffect(effect, 0.5F, 0.5F, 0.5F, 1.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.RED_DUST)){
			ParticleEffect particle = new ParticleEffect(effect, 0.5F, 0.5F, 0.5F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.SLIME)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.SMOKE)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.SNOW_SHOVEL)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.SNOWBALL_POOF)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.SPELL)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.SUSPENDED)){
			ParticleEffect particle = new ParticleEffect(effect, 0.8F, 0.8F, 0.8F, 0.0F, 5);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.WAKE)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 3);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.WITCH_MAGIC)){
			ParticleEffect particle = new ParticleEffect(effect, 0.4F, 0.4F, 0.4F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.BARRIER)){
			ParticleEffect particle = new ParticleEffect(effect, 1.2F, 1.2F, 1.2F, 0.0F, 1);
			particle.display(location);
			return;
		}else
		if(effect.equals(ParticleType.DROPLET)){
			ParticleEffect particle = new ParticleEffect(effect, 0.8F, 0.8F, 0.8F, 0.0F, 5);
			particle.display(location);
			return;
		}
	}

}
