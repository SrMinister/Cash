package net.kaiquy.cash.events;

import lombok.RequiredArgsConstructor;
import net.kaiquy.cash.CashPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class CashListener implements Listener {

    private final CashPlugin plugin;

    @EventHandler(ignoreCancelled = true)
    public void on(PlayerJoinEvent event) {
        plugin.getUserController().load(event.getPlayer());
    }

    @EventHandler(ignoreCancelled = true)
    public void on(PlayerQuitEvent event) {
        plugin.getUserController().save(event.getPlayer());
    }
}
