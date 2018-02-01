package com.gmail.agentup;

import java.util.List;

public interface UserService {
    CustomUser getUserByLogin(String login);
    List<CustomUser> getAllUsers();
    boolean existsByLogin(String login);
    void addUser(CustomUser customUser);
    void updateUser(CustomUser customUser);
    void deleteUser(CustomUser customUser);
    void deleteUserByLogin(String login);
}
