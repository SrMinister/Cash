package net.hyren.cash.objects.user.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import net.hyren.cash.CashPlugin;
import net.hyren.cash.objects.user.User;
import org.bukkit.entity.Player;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UserController {

    private final CashPlugin plugin;
    private final ExecutorService executorService;


    public UserController(CashPlugin plugin) {
        this.plugin = plugin;
        this.executorService = new ThreadPoolExecutor(
                2,4,
                15, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder()
                        .setNameFormat("User Controller - %d")
                        .build()
        );

    }

    public void load(Player player) {
        executorService.submit(() -> {
           User user = plugin.getUserStorage().findUser(player.getUniqueId());
           if (user == null) {
               user = User.builder()
                       .uuid(player.getUniqueId())
                       .username(player.getName())
                       .cash(0)
                       .build();
               plugin.getUserStorage().storeAndUpdate(user);
           }
            plugin.getUserStorage().storeAndUpdate(user);
           plugin.getUserCache().addCachedElement(user);
        });
    }

    public void save(Player player) {
        final User user = plugin.getUserCache().getById(player.getUniqueId());
        if(user == null) return;

        executorService.submit(() -> {
            plugin.getUserCache().removeCachedElement(user);
            plugin.getUserStorage().storeAndUpdate(user);
        });
    }
}
