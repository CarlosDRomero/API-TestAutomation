package com.automation.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// Define los campos necesarios para crear ordenes de compra de mascotas
public class OrderRequest {
    private long petId;
    private int quantity;
    private String status;
    private boolean complete;
}
