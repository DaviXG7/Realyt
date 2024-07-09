package com.realyt.realyt.commands;

import com.realyt.realyt.element.EType;
import com.realyt.realyt.element.ElementManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Random;

public class SetRandom implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!commandSender.isOp()) return true;

        ElementManager.reset();


        Bukkit.getOnlinePlayers().forEach(this::putRandom);

        try {
            ElementManager.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return true;
    }

    private void putRandom(Player player) {
        Random rand = new Random();
        EType type = EType.values()[rand.nextInt(EType.values().length)];
        if (ElementManager.getPlayer(type) != null) {
            putRandom(player);
            return;
        }
        ElementManager.setPlayer(type, player);

        player.sendMessage("Você agora é do elemento " + type.name().toLowerCase() + "!");


    }
}
