package com.realyt.realyt.vantagens;

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
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class VantagensListener implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            EType eType = ElementManager.getElementByPlayer(p);
            if (eType == null) return;
            switch (eType) {
                case AGUA:
                    e.setDamage(e.getDamage() * 1.1);
                    if (p.getLocation().getBlock().getType().equals(Material.WATER)) e.setDamage(e.getDamage() * 1.25);

                    break;
                case GELO:

                    if (
                            p.getLocation().getBlock().getBiome().equals(Biome.SNOWY_BEACH) ||
                            p.getLocation().getBlock().getBiome().equals(Biome.SNOWY_PLAINS) ||
                            p.getLocation().getBlock().getBiome().equals(Biome.SNOWY_SLOPES) ||
                            p.getLocation().getBlock().getBiome().equals(Biome.SNOWY_TAIGA) ||
                            p.getLocation().getBlock().getBiome().equals(Biome.ICE_SPIKES)
                    )
                        e.setDamage(e.getDamage() * 1.25);

                    if (e.getEntity() instanceof LivingEntity) ((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 100, true, true));
                    if (e.getEntity() instanceof LivingEntity) ((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 100, true, true));

                    break;
                case FOGO:

                    if (p.getLocation().getBlock().getType().equals(Material.LAVA) || p.getWorld().getEnvironment().equals(World.Environment.NETHER))
                        e.setDamage(e.getDamage() * 1.25);
                    if (p.getLocation().getBlock().getType().equals(Material.LAVA) && p.getWorld().getEnvironment().equals(World.Environment.NETHER))
                        e.setDamage(e.getDamage() * 1.50);

                    if (
                            !p.getLocation().getBlock().getBiome().equals(Biome.SNOWY_BEACH) ||
                                    !p.getLocation().getBlock().getBiome().equals(Biome.SNOWY_PLAINS) ||
                                    !p.getLocation().getBlock().getBiome().equals(Biome.SNOWY_SLOPES) ||
                                   !p.getLocation().getBlock().getBiome().equals(Biome.SNOWY_TAIGA) ||
                                    !p.getLocation().getBlock().getBiome().equals(Biome.ICE_SPIKES)
                    )
                    {
                        e.getEntity().setFireTicks(100);
                    }

                    break;
                case SKULK:

                    if (e.getEntity() instanceof LivingEntity) ((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 2, true, true));
                    e.setDamage(e.getDamage() * 1.1);
                    if (p.getWorld().getEnvironment().equals(World.Environment.THE_END)) e.setDamage(e.getDamage() * 1.3);

                    break;
                case AR:

                    if (e.getEntity() instanceof LivingEntity) ((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 1, true, true));

                    break;
                case TERRA:

                    p.getNearbyEntities(3,3,3).forEach(entity -> {
                        entity.setVelocity(new Vector(entity.getVelocity().getX(), entity.getVelocity().getY() + 1.5, entity.getVelocity().getZ()));
                    });
                    e.setDamage(e.getDamage() * 1.1);
                    break;
            }
        }
    }

    @EventHandler
    public void onToggleShift(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        EType eType = ElementManager.getElementByPlayer(player);
        if (eType == EType.AR && player.isOnGround()) player.setVelocity(player.getEyeLocation().getDirection().multiply(2).setY(0.7));
        if (eType == EType.AGUA) {
            if (player.isSneaking()) {
                player.getWorld().setStorm(false);
                return;
            }
            player.getWorld().setStorm(true);
        }
    }
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            EType eType = ElementManager.getElementByPlayer(player);
            if (eType == EType.AR && event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) event.setCancelled(true);
        }
    }
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (player.getPotionEffect(PotionEffectType.SLOW)!= null && player.getPotionEffect(PotionEffectType.SLOW).getAmplifier() == 100) e.setCancelled(true);

        EType type = ElementManager.getElementByPlayer(player);
        if (type == EType.GELO) {
            if (player.getLocation().subtract(new Vector(0,1,0)).getBlock().getType().equals(Material.WATER)) {
                player.getLocation().subtract(new Vector(0,1,0)).getBlock().setType(Material.ICE);
            };
        }
    }

}
