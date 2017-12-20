package com.gmail.agentup;

import java.io.*;
import java.util.*;

public class UserList {
    private static final UserList userList = new UserList();
    private final List<User> list = new LinkedList<User>();

    public List<User> getList() {
        return list;
    }

    public static UserList getInstance() {
        return userList;
    }

    public synchronized void add(User user) {
        list.add(user);
    }
}
