package com.realyt.realyt.vantagens;

import com.realyt.realyt.element.EType;
import com.realyt.realyt.element.ElementManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class VantagensTask extends BukkitRunnable {

    @Override
    public void run() {

        Bukkit.getOnlinePlayers().forEach(player -> {
            EType type = ElementManager.getElementByPlayer(player);
            if (type == null) return;
            switch (type) {
                case AGUA:

                    if (player.getLocation().getBlock().getType().equals(Material.WATER) || player.getWorld().hasStorm()) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 400, 255, true, false));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2, true, false));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200, 255, true, false));
                    }

                    break;
                case GELO:

                    player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 200, 0, true, false));

                    break;
                case FOGO:

                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, 255, true, false));

                    break;
                case SKULK:

                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1, true, false));

                    break;
                case AR:

                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1, true, false));

                    break;
                case TERRA:

                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1, false, false));

                    break;
            }
        });



    }
}
