package com.hackathon.backend.rest;

import com.hackathon.backend.dto.ProductDTO;
import com.hackathon.backend.model.Users;
import com.hackathon.backend.service.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/Products")
@CrossOrigin("*")
public class ProductRest {

    private final ProductService service;

    public ProductRest(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable Integer id, @RequestBody ProductDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PostMapping("/import/csv")
    public String importCSV(@RequestParam("file") MultipartFile file) {
        service.importCSV(file);
        return "CSV imported successfully";
    }
    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> exportExcel() throws IOException {

        ByteArrayInputStream file = service.exportExcel();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=Products.xlsx")
                .body(file.readAllBytes());
    }
    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> exportPDF() {

        ByteArrayInputStream file = service.exportPDF();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=Products.pdf")
                .body(file.readAllBytes());
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody(required = false) Users Users) {

        if (Users == null || Users.getUsemame() == null || Users.getPassword() == null
                || Users.getUsemame().isBlank() || Users.getPassword().isBlank()) {
            return ResponseEntity.badRequest().body("Debe enviar usemame y password");
        }

        Users result = service.login(Users.getUsemame().trim(), Users.getPassword());

        if (result != null) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.status(401).body("Credenciales inválidas");
    }
    
}