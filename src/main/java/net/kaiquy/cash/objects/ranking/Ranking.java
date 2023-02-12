package net.kaiquy.cash.objects.ranking;

import lombok.Data;
import org.bukkit.Bukkit;

import java.util.UUID;

@Data
public class Ranking {

    private final UUID uuid;
    private final Double cash;

    public String getName() {
        return Bukkit.getOfflinePlayer(uuid).getName();
    }
}
