package net.hyren.cash.views;

import me.saiintbrisson.minecraft.View;
import me.saiintbrisson.minecraft.ViewContext;
import me.saiintbrisson.minecraft.ViewItem;
import me.saiintbrisson.minecraft.utils.ItemBuilder;
import net.hyren.cash.objects.ranking.Ranking;
import net.hyren.cash.objects.user.storage.UserStorage;
import net.hyren.cash.utils.Formats;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

public class RankingView extends View {

    private final UserStorage userStorage;

    private static final int[] SLOTS = {
            10, 11, 12, 13, 14, 15, 16, 21, 22, 23
    };

    public RankingView(UserStorage userStorage) {
        super(4, "Cash ▸ Ranking");
        this.userStorage = userStorage;

        setCancelOnClick(true);
        setCancelOnPickup(true);
    }

    @Override
    protected void onRender(ViewContext context) {
        final List<Ranking> userList = userStorage.findAll().stream()
                .sorted(((o1, o2) -> Double.compare(o2.getCash(), o1.getCash())))
                .collect(Collectors.toList());

        for (int index = 0; index < 10; index++) {
            if ((index + 1) > userList.size()) continue;

            final ItemStack userItem = userItem((index + 1), userList.get(index));
            context.slot(SLOTS[index]).onRender(
                    $ -> $.setItem(userItem)
            );
        }

        for (int slot : SLOTS) {
            final ViewItem item = context.getItem(slot);
            if (item == null) context.slot(slot).onRender($ -> $.setItem(emptyItem()));
        }
    }

    private ItemStack userItem(int position, Ranking user) {
        return new ItemBuilder(Material.SKULL_ITEM, 1, 3)
                .skull(user.getName())
                .name("§a" + position + "º: " + user.getName())
                .name("§a" + position + "º: " + (user.getName() == null ? "§cNinguém" : user.getName()))
                .lore("§fCash: §6✪" + Formats.format(user.getCash()))
                .build();
    }

    private ItemStack emptyItem() {
        return new ItemBuilder(Material.SKULL_ITEM, 1, 3)
                .name("§cVazio.")
                .build();
    }
}