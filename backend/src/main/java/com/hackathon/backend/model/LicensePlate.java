package com.hackathon.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "license_plates")
@Data
public class LicensePlate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer licensePlatesId;

    private String orderCode;

    private Boolean isActive;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private LocalDateTime restoredAt;

    private Integer studentId;

    private LocalDate purchaseDate;

    private String promoter;

    private Integer raceId;

    private Integer campusId;

}
