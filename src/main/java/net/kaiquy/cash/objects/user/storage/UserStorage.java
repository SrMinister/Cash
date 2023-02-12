package net.kaiquy.cash.objects.user.storage;

import com.mysql.jdbc.PreparedStatement;
import lombok.SneakyThrows;
import net.kaiquy.cash.database.provider.sql.SQLConnectionProvider;
import net.kaiquy.cash.database.sql.function.SqlFunction;
import net.kaiquy.cash.objects.ranking.Ranking;
import net.kaiquy.cash.objects.user.User;
import net.kaiquy.cash.utils.SQLReader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

public class UserStorage {

    private final SQLReader sqlReader;

    private final SQLConnectionProvider connectionProvider;
    private final SqlFunction<ResultSet, User> function;

    public UserStorage(SQLConnectionProvider connectionProvider) throws IOException {
        this.connectionProvider = connectionProvider;

        this.sqlReader = new SQLReader();
        sqlReader.loadFromResources("sql/");

        this.function = set ->
                User.builder()
                .uuid(UUID.fromString(set.getString("uuid")))
                .username(set.getString("username"))
                .cash(set.getDouble("cash"))
                .build();
    }

    public void createTable() {
        connectionProvider.executeUpdate(sqlReader.getSql("create_table_query"));
    }

    public void createUserOnTable(User user) {
        connectionProvider.executeUpdate(sqlReader.getSql("create_user_query"), user.getUuid().toString(), user.getUsername(), user.getCash());
    }

    public boolean storeAndUpdate(User user) {
        return connectionProvider.executeUpdate(sqlReader.getSql("store_and_update_query"), user.getUsername(), user.getCash(), user.getUuid().toString()) > 0;
    }

    public User findUser(UUID uuid) {
        return connectionProvider.getFirstFromQuery(sqlReader.getSql("find_one_query"), function, uuid.toString());
    }

    @SneakyThrows
    public Ranking adapt(ResultSet set) {
        final UUID id = UUID.fromString(set.getString("uuid"));
        final String name = set.getString("username");

        final double cashBalance = set.getDouble("cash");

        return new Ranking(id, cashBalance);
    }

    @SneakyThrows
    public List<Ranking> findAll() {
        PreparedStatement statement = prepareStatement("find_all");

        List<Ranking> rankingList = new ArrayList<>();

        final ResultSet set = statement.executeQuery();
        while (set.next()) rankingList.add(adapt(set));

        return rankingList;
    }


    @SneakyThrows
    private PreparedStatement prepareStatement(String sqlFile) {
        final String query = sqlReader.getSql(sqlFile);
        final Connection connection = connectionProvider.getConnectionInstance();

        return (PreparedStatement) connectionProvider.getConnectionInstance().prepareStatement(query);
    }
}
