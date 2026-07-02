
//Esto es lo que usará Swagger
package com.hackathon.backend.dto;

import lombok.Data;

@Data
public class ProductDTO {

    public Integer ProductId;
    public String ProductName;
    public Double unitPrice;
    public Integer stock;
}