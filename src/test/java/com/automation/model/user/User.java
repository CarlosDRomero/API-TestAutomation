package com.automation.model.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// Esta clase permite definir la información tanto para crear como para obtener usuarios
// Ya que la API acepta exactamente los mismos campos en ambos sentidos
public class User {
    private int  id;
    @JsonProperty(value = "username")
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
}
