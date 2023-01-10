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
public class CashPayCommand {

    private final CashPlugin plugin;

    @Command(
            name = "cash.pay",
            target = CommandTarget.PLAYER
    )

    public void execute(Context<CommandSender> context, Player target, String value) {
        val player = (Player) context.getSender();
        val parse = Formats.parse(value);

        if (player == target) {
            player.sendMessage("§cVocê não pode enviar cash pra sí mesmo!");
            return;
        }
        if (parse < 1) {
            player.sendMessage("§cVocê não possuí cash para enviar!");
            return;
        }

        if (target == null){
            player.sendMessage("§cEste jogador não existe!");
            return;
        }

        CashAPI.pay(player, target, parse);
        player.sendMessage(plugin.getConfig().getString("Messages.pay_cash")
                .replace("&", "§")
                .replace("{target}", target.getName())
                .replace("{value}", Formats.format(parse))
                .replace("{balance}", "" + Formats.format(CashAPI.get(target)))
        );

        target.sendMessage(plugin.getConfig().getString("Messages.target_pay_cash")
                .replace("&", "§")
                .replace("{player}", player.getName())
                .replace("{value}", Formats.format(parse))
        );
    }
}
