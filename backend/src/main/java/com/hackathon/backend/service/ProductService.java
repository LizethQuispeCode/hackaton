//interfaz
package com.hackathon.backend.service;

import com.hackathon.backend.dto.ProductDTO;
import com.hackathon.backend.model.Users;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

    List<ProductDTO> findAll();

    ProductDTO findById(Integer id);

    ProductDTO create(ProductDTO dto);

    ProductDTO update(Integer id, ProductDTO dto);

    void delete(Integer id);

    void importCSV(MultipartFile file);

    ByteArrayInputStream exportExcel();

    ByteArrayInputStream exportPDF();

    Users login(String username, String password);

}