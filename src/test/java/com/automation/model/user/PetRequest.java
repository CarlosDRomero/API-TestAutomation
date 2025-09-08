package com.automation.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// Define los campos necesarios para crear mascotas
public class PetRequest {
    private String name;
    private String status;
}
