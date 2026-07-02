//Logica
package com.hackathon.backend.service.impl;

import com.hackathon.backend.dto.ProductDTO;
import com.hackathon.backend.model.Product;
import com.hackathon.backend.model.Users;
import com.hackathon.backend.repository.ProductRepository;
import com.hackathon.backend.repository.UsersRepository;
import com.hackathon.backend.service.ProductService;
import com.opencsv.CSVReader;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;
    private final UsersRepository usersRepo;

    public ProductServiceImpl(ProductRepository repo, UsersRepository usersRepo) {
        this.repo = repo;
        this.usersRepo = usersRepo;
    }

    @Override
    public List<ProductDTO> findAll() {
        return repo.findByStatusTrue()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public ProductDTO findById(Integer id) {
        return repo.findById(id).map(this::mapToDTO).orElse(null);
    }

    @Override
    public ProductDTO create(ProductDTO dto) {

        Product p = new Product();
        p.setProductName(dto.getProductName());
        p.setUnitPrice(dto.getUnitPrice());
        p.setStock(dto.getStock());

        //Auditoría
        p.setCreatedAt(LocalDateTime.now());
        p.setCreatedBy("SYSTEM");

        repo.save(p);

        return mapToDTO(p);
    }

    @Override
    public ProductDTO update(Integer id, ProductDTO dto) {

        Product p = repo.findById(id).orElseThrow();

        p.setProductName(dto.getProductName());
        p.setUnitPrice(dto.getUnitPrice());
        p.setStock(dto.getStock());

        //Auditoría
        p.setUpdatedAt(LocalDateTime.now());
        p.setUpdatedBy("SYSTEM");

        repo.save(p);

        return mapToDTO(p);
    }

    @Override
    public void delete(Integer id) {

        Product p = repo.findById(id).orElseThrow();

        p.setStatus(false); //eliminación lógica

        repo.save(p);
    }
    private ProductDTO mapToDTO(Product p) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(p.getProductId());
        dto.setProductName(p.getProductName());
        dto.setUnitPrice(p.getUnitPrice());
        dto.setStock(p.getStock());
        return dto;
    }
    @Override
    public void importCSV(MultipartFile file) {

        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVReader csvReader = new CSVReader(reader)) {
            List<String[]> rows = csvReader.readAll();

            for (int i = 1; i < rows.size(); i++) { // skip header

                String[] row = rows.get(i);

                Product p = new Product();
                p.setProductName(row[0]);
                p.setUnitPrice(Double.parseDouble(row[1]));
                p.setStock(Integer.parseInt(row[2]));
                p.setStatus(true);
                p.setCreatedAt(LocalDateTime.now());

                repo.save(p);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error importing CSV");
        }
    }
    @Override
    public ByteArrayInputStream exportExcel() {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Products");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Name");
            header.createCell(2).setCellValue("Price");
            header.createCell(3).setCellValue("Stock");

            List<Product> Products = repo.findAll();

            int rowIdx = 1;
            for (Product p : Products) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(p.getProductId());
                row.createCell(1).setCellValue(p.getProductName());
                row.createCell(2).setCellValue(p.getUnitPrice());
                row.createCell(3).setCellValue(p.getStock());
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);

            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            throw new RuntimeException("Error generating Excel");
        }
    }
    @Override
    public ByteArrayInputStream exportPDF() {

        try {

            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, out);

            document.open();

            document.add(new Paragraph("ProductS REPORT"));

            PdfPTable table = new PdfPTable(3);
            table.addCell("ID");
            table.addCell("Name");
            table.addCell("Price");

            List<Product> Products = repo.findAll();

            for (Product p : Products) {
                table.addCell(String.valueOf(p.getProductId()));
                table.addCell(p.getProductName());
                table.addCell(String.valueOf(p.getUnitPrice()));
            }

            document.add(table);
            document.close();

            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF");
        }
    }

    @Override
    public Users login(String username, String password) {
        return usersRepo.findByUsernameAndPassword(username, password);
    }
}