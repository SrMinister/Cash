package net.kaiquy.cash.commands;

import lombok.RequiredArgsConstructor;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import net.kaiquy.cash.CashPlugin;
import net.kaiquy.cash.CashAPI;
import net.kaiquy.cash.utils.Formats;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class CashCommand {

    private final CashPlugin plugin;

    @Command(
            name = "cash",
            usage = "cash (jogador)",
            target = CommandTarget.PLAYER
    )

    public void execute(Context<CommandSender> context, @Optional Player target) {
        val player = (Player) context.getSender();

        if (target == null) {
            player.sendMessage(
                    plugin.getConfig()
                            .getString("Messages.current_balance")
                            .replace("&", "§")
                            .replace("{balance}", "" + Formats.format(CashAPI.get(player)))
            );
        } else {
            player.sendMessage(
                    plugin.getConfig()
                            .getString("Messages.target_current_balance")
                            .replace("&", "§")
                            .replace("{target}", target.getName())
                            .replace("{balance}", "" + Formats.format(CashAPI.get(target)))
            );
        }
    }
}
