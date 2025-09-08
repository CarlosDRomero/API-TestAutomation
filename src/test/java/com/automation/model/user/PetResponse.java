package com.automation.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
// Define los campos de información de las mascotas
// (realmente la API define más campos pero no los consideré necesarios para los ejercicios propuestos)
public class PetResponse {
    private long id;
    private String name;
    private String status;
}
