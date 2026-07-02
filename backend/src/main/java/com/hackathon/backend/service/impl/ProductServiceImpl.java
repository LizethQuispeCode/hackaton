package com.hackathon.backend.service.impl;

import com.hackathon.backend.dto.StudentDTO;
import com.hackathon.backend.model.Students;
import com.hackathon.backend.repository.StudentRepository;
import com.hackathon.backend.service.StudentService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<StudentDTO> findAll() {
        return repo.findByIsActiveTrue()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public StudentDTO findById(Integer id) {
        return repo.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public StudentDTO create(StudentDTO dto) {

        Students s = new Students();

        s.setName(dto.getName());
        s.setLastName(dto.getLastName());
        s.setEmail(dto.getEmail());
        s.setAddress(dto.getAddress());
        s.setAge(dto.getAge());
        s.setDni(dto.getDni());

        s.setIsActive(true);
        s.setCreatedAt(LocalDateTime.now());

        repo.save(s);

        return mapToDTO(s);
    }

    @Override
    public StudentDTO update(Integer id, StudentDTO dto) {

        Students s = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        s.setName(dto.getName());
        s.setLastName(dto.getLastName());
        s.setEmail(dto.getEmail());
        s.setAddress(dto.getAddress());
        s.setAge(dto.getAge());
        s.setDni(dto.getDni());

        s.setUpdatedAt(LocalDateTime.now());

        repo.save(s);

        return mapToDTO(s);
    }

    @Override
    public void delete(Integer id) {

        Students s = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // eliminación lógica
        s.setIsActive(false);
        s.setDeletedAt(LocalDateTime.now());

        repo.save(s);
    }

    private StudentDTO mapToDTO(Students s) {

        StudentDTO dto = new StudentDTO();

        dto.setStudentId(s.getStudentId());
        dto.setName(s.getName());
        dto.setLastName(s.getLastName());
        dto.setEmail(s.getEmail());
        dto.setAddress(s.getAddress());
        dto.setAge(s.getAge());
        dto.setDni(s.getDni());

        return dto;
    }
}









package com.hackathon.backend.service.impl;

import com.hackathon.backend.dto.LicensePlateDTO;
import com.hackathon.backend.dto.LicensePlateDetailDTO;
import com.hackathon.backend.model.LicensePlates;
import com.hackathon.backend.repository.LicensePlateRepository;
import com.hackathon.backend.service.LicensePlateService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LicensePlateServiceImpl implements LicensePlateService {

    private final LicensePlateRepository repo;

    public LicensePlateServiceImpl(LicensePlateRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<LicensePlateDetailDTO> findAll() {
        return repo.findByIsActiveTrue()
                .stream()
                .map(this::mapToDetailDTO)
                .toList();
    }

    @Override
    public LicensePlateDetailDTO findById(Integer id) {
        return repo.findById(id)
                .map(this::mapToDetailDTO)
                .orElse(null);
    }

    @Override
    public LicensePlateDTO create(LicensePlateDTO dto) {

        LicensePlates lp = new LicensePlates();

        lp.setOrderCode(dto.getOrderCode());
        lp.setStudentId(dto.getStudentId());
        lp.setRaceId(dto.getRaceId());
        lp.setCampusId(dto.getCampusId());
        lp.setPromoter(dto.getPromoter());
        lp.setPurchaseDate(dto.getPurchaseDate());

        lp.setIsActive(true);
        lp.setCreatedAt(LocalDateTime.now());

        repo.save(lp);

        return dto;
    }

    @Override
    public LicensePlateDTO update(Integer id, LicensePlateDTO dto) {

        LicensePlates lp = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("License plate not found"));

        lp.setOrderCode(dto.getOrderCode());
        lp.setStudentId(dto.getStudentId());
        lp.setRaceId(dto.getRaceId());
        lp.setCampusId(dto.getCampusId());
        lp.setPromoter(dto.getPromoter());
        lp.setPurchaseDate(dto.getPurchaseDate());

        lp.setUpdatedAt(LocalDateTime.now());

        repo.save(lp);

        return dto;
    }

    @Override
    public void delete(Integer id) {

        LicensePlates lp = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("License plate not found"));

        lp.setIsActive(false);
        lp.setDeletedAt(LocalDateTime.now());

        repo.save(lp);
    }

    private LicensePlateDetailDTO mapToDetailDTO(LicensePlates lp) {

        LicensePlateDetailDTO dto = new LicensePlateDetailDTO();

        dto.setLicensePlatesId(lp.getLicensePlatesId());
        dto.setOrderCode(lp.getOrderCode());
        dto.setPromoter(lp.getPromoter());
        dto.setPurchaseDate(lp.getPurchaseDate());
        dto.setStudentId(lp.getStudentId());
        dto.setRaceId(lp.getRaceId());
        dto.setCampusId(lp.getCampusId());

        return dto;
    }
}
