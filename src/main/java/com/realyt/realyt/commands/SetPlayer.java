package com.realyt.realyt.commands;

import com.realyt.realyt.element.EType;
import com.realyt.realyt.element.ElementManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetPlayer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!commandSender.isOp()) return true;

        if (strings.length != 2) return false;

        Player player = Bukkit.getPlayer(strings[0]);

        if (player == null) {
            commandSender.sendMessage("Player not found");
            return true;
        }
        try  {
            EType type = EType.valueOf(strings[1].toUpperCase());

            ElementManager.resetPlayer(player);

            ElementManager.setPlayer(type, player);
            ElementManager.save();
            player.sendMessage("Você agora é do elemento " + type.name().toLowerCase() + "!");

        } catch (Exception e) {
            player.sendMessage("Element not found!");
            return true;
        }





        return true;
    }
}
