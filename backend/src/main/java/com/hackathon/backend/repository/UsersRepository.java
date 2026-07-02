package com.hackathon.backend.repository;

import com.hackathon.backend.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByUsernameAndPassword(String username, String password);
}