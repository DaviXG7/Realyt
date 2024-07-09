package com.realyt.realyt.desvantagens;

import com.realyt.realyt.element.EType;
import com.realyt.realyt.element.ElementManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DesvantagensTask extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            EType type = ElementManager.getElementByPlayer(player);
            if (type == null) return;
            switch (type) {
                case GELO:
                    if (player.getWorld().hasStorm() || player.getLocation().getBlock().getType().equals(Material.WATER)) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 1, true, false));
                        player.damage(5);
                    }
                case FOGO:

                    if (player.getLocation().getBlock().getType().equals(Material.WATER)) player.damage(5);
                    if (player.getWorld().hasStorm()) {
                        player.damage(2);
                    }

                    break;
                case TERRA:

                    if (player.getLocation().getBlock().getType().equals(Material.WATER)) player.damage(0.2);

                    break;
            }
        });
    }
}
