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







package com.hackathon.backend.service;

import com.hackathon.backend.dto.LicensePlateDTO;
import com.hackathon.backend.dto.LicensePlateDetailDTO;

import java.util.List;

public interface LicensePlateService {

    List<LicensePlateDetailDTO> findAll();

    LicensePlateDetailDTO findById(Integer id);

    LicensePlateDTO create(LicensePlateDTO dto);

    LicensePlateDTO update(Integer id, LicensePlateDTO dto);

    void delete(Integer id);

}
