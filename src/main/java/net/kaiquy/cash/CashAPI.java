package net.kaiquy.cash;

import org.bukkit.entity.Player;

public class CashAPI {

    public static double get(Player player) {
        return CashPlugin.getPlugin(CashPlugin.class).getUserCache().getByPlayer(player.getPlayer()).getCash();
    }

    public static void set(Player player, double value) {
        CashPlugin.getPlugin(CashPlugin.class).getUserCache().getByPlayer(player.getPlayer()).setCash(value);
    }

    public static void add(Player player, double value) {
        CashPlugin.getPlugin(CashPlugin.class).getUserCache().getByPlayer(player.getPlayer()).setCash(CashPlugin.getPlugin(CashPlugin.class).getUserCache().getByPlayer(player.getPlayer()).getCash() + value);
    }

    public static void remove(Player player, double value) {
        CashPlugin.getPlugin(CashPlugin.class).getUserCache().getByPlayer(player.getPlayer()).setCash(CashPlugin.getPlugin(CashPlugin.class).getUserCache().getByPlayer(player.getPlayer()).getCash() - value);
    }

    public static void pay(Player player, Player target, double value) {
        CashPlugin.getPlugin(CashPlugin.class).getUserCache().getByPlayer(player.getPlayer()).setCash(CashPlugin.getPlugin(CashPlugin.class).getUserCache().getByPlayer(player.getPlayer()).getCash() - value);
        CashPlugin.getPlugin(CashPlugin.class).getUserCache().getByPlayer(target.getPlayer()).setCash(CashPlugin.getPlugin(CashPlugin.class).getUserCache().getByPlayer(target.getPlayer()).getCash() + value);
    }
}
