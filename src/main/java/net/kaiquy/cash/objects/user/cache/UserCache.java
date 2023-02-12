package net.kaiquy.cash.objects.user.cache;

import lombok.RequiredArgsConstructor;
import net.kaiquy.cash.CashPlugin;
import net.kaiquy.cash.objects.user.User;
import net.kaiquy.cash.utils.Cache;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class UserCache extends Cache<User> {


    private final CashPlugin plugin;
    private final List<User> CACHE = new ArrayList<>();

    public User getById(UUID id) {
        return getCached(user -> user.getUuid().equals(id));
    }
    public User getByPlayer(Player player) {
        return getCached(user -> user.getUuid().equals(player.getUniqueId()));
    }


    public List<User> get() {
        return CACHE;
    }

}
