
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
