package net.kaiquy.cash.objects.user;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User {

    private final UUID uuid;
    private final String username;

    private double cash;

    public User(UUID uuid, String username) {
        this.uuid = uuid;
        this.username = username;
        this.cash = 0.0;
    }

    public User(UUID uuid, String username, Double cash) {
        this.uuid = uuid;
        this.username = username;
        this.cash = cash;
    }

}
