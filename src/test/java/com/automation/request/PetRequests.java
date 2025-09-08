package com.automation.request;

import com.automation.model.user.PetRequest;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class PetRequests {
    // Petición para crear una mascota
    public static Response createPet(String name, String status){
        PetRequest petRequest =  PetRequest.builder()
                .name(name)
                .status(status)
                .build();

        return RequestBuilder.postRequest("/pet", petRequest);
    }
    // Petición para obtener todas las mascotas que tienen cierto status
    public static Response getPetsByStatus(String status) {
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("status", status);
        return RequestBuilder.getRequest("/pet/findByStatus", queryParams);
    }
    // Petición para obtener una mascota mediante su petId
    public static Response getPetById(long id) {
        return RequestBuilder.getRequest("/pet/"+id);
    }
    // Petición para eliminar una mascota mediante su petId
    public static Response deletePetById(long id) {
        return RequestBuilder.deleteRequest("/pet/"+id);
    }
}
