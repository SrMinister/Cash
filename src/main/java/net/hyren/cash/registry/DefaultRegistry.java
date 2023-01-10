package net.hyren.cash.registry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.saiintbrisson.minecraft.View;
import me.saiintbrisson.minecraft.ViewFrame;
import me.saiintbrisson.minecraft.command.message.MessageHolder;
import me.saiintbrisson.minecraft.command.message.MessageType;
import net.hyren.cash.CashPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public class DefaultRegistry {

    @Getter
    private ViewFrame viewFrame;
    private final CashPlugin plugin;

    public void registryListener(Listener... listeners) {
        val pluginManager = Bukkit.getPluginManager();

        for (Listener listener : listeners) {
            pluginManager.registerEvents(listener, plugin);
        }
    }

    public void registerCommands(Object... objects) {
        final BukkitFrame frame = new BukkitFrame(plugin);
        final MessageHolder messageHolder = frame.getMessageHolder();

        messageHolder.setMessage(MessageType.NO_PERMISSION,"§cVocê não possuí permissão para isso.");
        messageHolder.setMessage(MessageType.INCORRECT_USAGE, "§cUtilize /{usage}.");

        frame.registerCommands(objects);
    }

    public void registerViews(View... views) {
        this.viewFrame = new ViewFrame(plugin);

        viewFrame.register(views);
    }
}
