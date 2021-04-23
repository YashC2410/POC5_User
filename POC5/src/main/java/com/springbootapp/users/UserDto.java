package com.springbootapp.users;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserDto {

    private final List<User> users;
    private final Map<String, Integer> page;

    public UserDto(final List<User> users, final Map<String, Integer> page) {
        this.users = new ArrayList<>(users);
        this.page = new HashMap<>(page);
    }

    public static UserDto create(final List<User> users, final Map<String, Integer> page) {
        return new UserDto(users, page);
    }

    public List<User> getUsers() {
        return users;
    }

    public Map<String, Integer> getPage() {
        return page;
    }
}
