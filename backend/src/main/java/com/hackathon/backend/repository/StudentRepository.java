package com.hackathon.backend.repository;

import com.hackathon.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByIsActiveTrue();

}
