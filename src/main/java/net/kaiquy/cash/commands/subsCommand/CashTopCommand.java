package net.kaiquy.cash.commands.subsCommand;

import lombok.RequiredArgsConstructor;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import net.kaiquy.cash.objects.user.storage.UserStorage;
import net.kaiquy.cash.views.RankingView;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class CashTopCommand {

    private final UserStorage userStorage;

    @Command(
            name = "cash.top",
            target = CommandTarget.PLAYER,
            async = true
    )

    public void execute(Context<CommandSender> context) {
        val player = (Player) context.getSender();

        RankingView rankingView = new RankingView(userStorage);
        rankingView.open(player);
    }
}
