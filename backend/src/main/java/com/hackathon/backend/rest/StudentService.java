package com.hackathon.backend.rest;

import com.hackathon.backend.dto.StudentDTO;
import com.hackathon.backend.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin("*")
public class StudentRest {

    private final StudentService service;

    public StudentRest(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudentDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public StudentDTO getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public StudentDTO create(@RequestBody StudentDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public StudentDTO update(@PathVariable Integer id, @RequestBody StudentDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
