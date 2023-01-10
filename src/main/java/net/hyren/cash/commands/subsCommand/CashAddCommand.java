package net.hyren.cash.commands.subsCommand;

import lombok.RequiredArgsConstructor;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import net.hyren.cash.CashAPI;
import net.hyren.cash.CashPlugin;
import net.hyren.cash.utils.Formats;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class CashAddCommand {

    private final CashPlugin plugin;

    @Command(
            name = "cash.add",
            permission = "hyren.cash.add",
            target = CommandTarget.ALL
    )

    public void execute(Context<CommandSender> context, Player target, double value) {
        val player = (Player) context.getSender();
        CashAPI.add(target, value);

        player.sendMessage(plugin.getConfig().getString("Messages.add_cash")
                .replace("&", "§")
                .replace("{target}", target.getName())
                .replace("{value}", Formats.format(value))
                .replace("{balance}", "" + Formats.format(CashAPI.get(target)))
        );
    }
}
