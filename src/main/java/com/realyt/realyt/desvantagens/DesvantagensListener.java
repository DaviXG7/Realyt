package com.realyt.realyt.desvantagens;

import com.realyt.realyt.element.EType;
import com.realyt.realyt.element.ElementManager;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class DesvantagensListener implements Listener {
    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            EType eType = ElementManager.getElementByPlayer(p);
            if (eType == null) return;
            switch (eType) {
                case GELO:

                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1));

                    break;
                case FOGO:

                    if (
                            p.getLocation().getBlock().getBiome().equals(Biome.SNOWY_BEACH) ||
                                    p.getLocation().getBlock().getBiome().equals(Biome.SNOWY_PLAINS) ||
                                    p.getLocation().getBlock().getBiome().equals(Biome.SNOWY_SLOPES) ||
                                    p.getLocation().getBlock().getBiome().equals(Biome.SNOWY_TAIGA) ||
                                    p.getLocation().getBlock().getBiome().equals(Biome.ICE_SPIKES)
                    ) {
                        e.setDamage(e.getDamage() * 0.5);
                    }

                    break;
                case SKULK:

                    p.damage(0.5);

                    break;
                case AR:

                    e.setDamage(e.getDamage() * 0.8);

                    break;
            }
        }
    }
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            EType eType = ElementManager.getElementByPlayer(p);
            if (eType == null) return;
            switch (eType) {
                case AGUA:
                case GELO:
                    if (e.getCause() == EntityDamageEvent.DamageCause.LAVA) e.setDamage(e.getDamage() * 2);
            }
        }
    }
}
