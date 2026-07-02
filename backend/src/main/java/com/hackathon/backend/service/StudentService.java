package com.hackathon.backend.service;

import com.hackathon.backend.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> findAll();

    StudentDTO findById(Integer id);

    StudentDTO create(StudentDTO dto);

    StudentDTO update(Integer id, StudentDTO dto);

    void delete(Integer id);

}
