package com.hackathon.backend.repository;

import com.hackathon.backend.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query(value = "SELECT TOP 1 * FROM Users WHERE usersname = :usersname AND password = :password", nativeQuery = true)
    Users findByUsersnameAndPassword(@Param("usersname") String usersname, @Param("password") String password);
}