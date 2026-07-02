package com.hackathon.backend.repository;

import com.hackathon.backend.model.LicensePlate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LicensePlateRepository extends JpaRepository<LicensePlate, Integer> {

    List<LicensePlate> findByIsActiveTrue();

}
