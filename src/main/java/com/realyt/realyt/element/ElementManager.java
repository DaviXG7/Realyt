package com.realyt.realyt.element;

import com.google.gson.JsonObject;
import com.realyt.realyt.Realyt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ElementManager {

    private static HashMap<EType, UUID> players = new HashMap<>();

    public static void init() throws IOException {

        if (!Realyt.INSTANCE.getDataFolder().exists()) {
            Realyt.INSTANCE.getDataFolder().mkdirs();
        }
        File file = new File(Realyt.INSTANCE.getDataFolder(), "players.json");

        if (!file.exists()) {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write("{\"players\": []}");
            fw.flush();
            fw.close();
        }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(jsonBuilder.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("players");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject player = jsonArray.getJSONObject(i);
                players.put(EType.valueOf(player.getString("type")), UUID.fromString(player.getString("uuid")));

            }

            Bukkit.getConsoleSender().sendMessage("Player data loaded!");

    }

    public static void reset() {
        players.clear();
    }
    public static void resetPlayer(Player player) {
        players.remove(getElementByPlayer(player));
    }

    public static Player getPlayer(EType type) {
        return players.get(type) == null ? null : Bukkit.getPlayer(players.get(type));
    }
    public static void setPlayer(EType type, Player player) {
        players.put(type, player.getUniqueId());
    }
    public static EType getElementByPlayer(Player player) {
        return Arrays.stream(EType.values()).filter(type -> players.get(type) != null).filter(type -> players.get(type).equals(player.getUniqueId())).findFirst().orElse(null);
    }

    public static void save() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("players", new JSONArray());
        for (Map.Entry<EType, UUID> entry : players.entrySet()) {
            JSONArray array = jsonObject.getJSONArray("players");

            JSONObject item = new JSONObject();
            item.put("type", entry.getKey().name());
            item.put("uuid", entry.getValue().toString());

            array.put(item);
        }

        File file = new File(Realyt.INSTANCE.getDataFolder(), "players.json");
        FileWriter writer = new FileWriter(file);
        writer.write(jsonObject.toString());
        writer.close();

    }

}
