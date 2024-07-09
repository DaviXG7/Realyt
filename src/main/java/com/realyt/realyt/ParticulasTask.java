package com.realyt.realyt;

import com.realyt.realyt.element.EType;
import com.realyt.realyt.element.ElementManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class ParticulasTask extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            EType type = ElementManager.getElementByPlayer(player);
            if (type == null) return;
            switch (type) {
                case AGUA:

                    player.getWorld().spawnParticle(Particle.FALLING_WATER, player.getLocation().add(0, 2.5, 0), 10, 0.5, 0.5, 0.5, 0.01);

                    break;
                case GELO:

                    player.getWorld().spawnParticle(Particle.SNOWFLAKE, player.getLocation().add(0, 2.5, 0), 10, 0.5, 0.5, 0.5, 0.01);

                    break;
                case FOGO:

                    player.getWorld().spawnParticle(Particle.LAVA, player.getLocation().add(0, 2.5, 0), 10, 0.5, 0.5, 0.5, 0.01);

                    break;
                case SKULK:

                    player.getWorld().spawnParticle(Particle.SCULK_SOUL, player.getLocation().add(0, 2.5, 0), 10, 0.5, 0.5, 0.5, 0.01);

                    break;
                case AR:

                    player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, player.getLocation().add(0, 2.5, 0), 10, 0.5, 0.5, 0.5, 0.01);

                    break;
                case TERRA:

                    player.getWorld().spawnParticle(Particle.HEART, player.getLocation().add(0, 2.5, 0), 10, 0.5, 0.5, 0.5, 0.01);

                    break;
            }
        });
    }
}
