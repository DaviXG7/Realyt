package com.realyt.realyt;

import com.realyt.realyt.commands.SetPlayer;
import com.realyt.realyt.commands.SetRandom;
import com.realyt.realyt.desvantagens.DesvantagensListener;
import com.realyt.realyt.desvantagens.DesvantagensTask;
import com.realyt.realyt.element.ElementManager;
import com.realyt.realyt.vantagens.VantagensListener;
import com.realyt.realyt.vantagens.VantagensTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Realyt extends JavaPlugin {

    public static Realyt INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        try {
            ElementManager.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getCommand("putrandom").setExecutor(new SetRandom());
        getCommand("putplayer").setExecutor(new SetPlayer());
        getServer().getPluginManager().registerEvents(new DesvantagensListener(), this);
        getServer().getPluginManager().registerEvents(new VantagensListener(), this);

        new VantagensTask().runTaskTimer(this, 0, 10L);
        new DesvantagensTask().runTaskTimer(this, 0, 20L);
        new ParticulasTask().runTaskTimer(this, 0, 10L);

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
