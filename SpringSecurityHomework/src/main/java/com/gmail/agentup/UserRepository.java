package com.gmail.agentup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<CustomUser, Long> {
    @Query("SELECT u FROM CustomUser u WHERE u.login = :login")
    CustomUser findByLogin(@Param("login") String login);

    @Query("SELECT u FROM CustomUser u")
    List<CustomUser> findAll();

    @Query("DELETE FROM CustomUser u WHERE u.login = :login")
    void deleteByLogin(@Param("login") String login);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM CustomUser u WHERE u.login = :login")
    boolean existsByLogin(@Param("login") String login);
}
