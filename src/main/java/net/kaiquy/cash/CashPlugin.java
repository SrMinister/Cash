package net.kaiquy.cash;

import lombok.Getter;
import lombok.SneakyThrows;
import net.kaiquy.cash.commands.CashCommand;
import net.kaiquy.cash.commands.subsCommand.CashAddCommand;
import net.kaiquy.cash.commands.subsCommand.CashPayCommand;
import net.kaiquy.cash.commands.subsCommand.CashSetCommand;
import net.kaiquy.cash.commands.subsCommand.CashTopCommand;
import net.kaiquy.cash.database.provider.sql.MySQLConnectionProvider;
import net.kaiquy.cash.database.provider.sql.SQLConnectionProvider;
import net.kaiquy.cash.events.CashListener;
import net.kaiquy.cash.objects.user.User;
import net.kaiquy.cash.objects.user.cache.UserCache;
import net.kaiquy.cash.objects.user.controller.UserController;
import net.kaiquy.cash.objects.user.storage.UserStorage;
import net.kaiquy.cash.registry.DefaultRegistry;
import net.kaiquy.cash.utils.PropertiesBuilder;
import net.kaiquy.cash.views.RankingView;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class CashPlugin extends JavaPlugin {

    private UserCache userCache;
    private DefaultRegistry defaultRegistry;

    private SQLConnectionProvider connectionProvider;

    private UserStorage userStorage;
    private UserController userController;

    @SneakyThrows
    @Override
    public void onLoad() {
        saveDefaultConfig();

        connectionProvider = new MySQLConnectionProvider();
        userCache = new UserCache(this);
        userStorage = new UserStorage(connectionProvider);
        userController = new UserController(this);
    }

    @Override
    public void onEnable() {
        loadMySQL();
        loadRegistry();

        userStorage.createTable();

    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            final User user = userCache.getByPlayer(player);
            if (user != null) {
                userStorage.storeAndUpdate(user);
            }
        });

    }

    public void loadMySQL() {
        connectionProvider.connect(new PropertiesBuilder()
                .with("driver", "mysql")
                .with("hostname", getConfig().getString("mysql.hostname"))
                .with("port", "3306")
                .with("database", getConfig().getString("mysql.database"))
                .with("username", getConfig().getString("mysql.username"))
                .with("password", getConfig().getString("mysql.password"))
                .wrap()
        );
    }

    public void loadRegistry() {

        defaultRegistry = new DefaultRegistry(this);
        defaultRegistry.registerCommands(
                new CashCommand(this),
                new CashAddCommand(this),
                new CashTopCommand(userStorage),
                new CashSetCommand(this),
                new CashPayCommand(this)
        );

        defaultRegistry.registryListener(new CashListener(this));

        defaultRegistry.registerViews(
                new RankingView(userStorage)
        );

    }

}
