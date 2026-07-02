
//Esto es lo que usará Swagger
package com.hackathon.backend.dto;

import lombok.Data;

@Data
public class StudentDTO {

    private Integer studentId;
    private String name;
    private String lastName;
    private String email;
    private String address;
    private Integer age;
    private String dni;
    private Boolean isActive;
}




package com.hackathon.backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LicensePlateDTO {

    private Integer licensePlatesId;
    private String orderCode;
    private Integer studentId;
    private Integer raceId;
    private Integer campusId;
    private String promoter;
    private LocalDate purchaseDate;
    private Boolean isActive;
}



package com.hackathon.backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LicensePlateDetailDTO {

    private Integer licensePlatesId;
    private String orderCode;

    private String studentName;
    private String raceName;
    private String campusName;

    private String promoter;
    private LocalDate purchaseDate;
}
