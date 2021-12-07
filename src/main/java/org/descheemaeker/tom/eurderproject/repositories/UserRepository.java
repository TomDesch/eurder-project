package org.descheemaeker.tom.eurderproject.repositories;

import org.descheemaeker.tom.eurderproject.api.users.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private final Map<UUID, User> users = new ConcurrentHashMap<>();

    public User addUser(User user) {
        users.put(user.getUserId(), user);
        return user;
    }

    public User getUser(UUID userId) {
        return users.get(userId);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }


}
