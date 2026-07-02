//Aquí conecto con mi SQL Server
package com.hackathon.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ProductS")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ProductId;

    private Integer categoryId;
    private Integer supplierId;

    private String ProductName;
    private String description;

    private Double unitPrice;
    private Integer stock;
    private Integer minimumStock;
    private String barcode;

    private Boolean status;

    //Auditoría
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    
}
    