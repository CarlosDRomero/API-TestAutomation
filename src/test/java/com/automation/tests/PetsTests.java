package com.automation.tests;

import com.automation.model.user.PetRequest;
import com.automation.model.user.PetResponse;
import com.automation.request.RequestBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetsTests extends TestRunner {
    private long petId;

    @BeforeClass
    public void createPet(){
        // Como precondición, para evitar problemas con los GET he decidido hacer la creación de una mascota para tener un ID mas seguro
        PetRequest petRequest =  new PetRequest("TestPet", "pending");

        PetResponse petResponse = RequestBuilder.postRequest(getBaseUrl(), "/pet", petRequest).as(PetResponse.class);
        petId = petResponse.getId();
    }

    @Test(testName = "Obtener todas las mascotas en estado 'Disponible'")
    public void availablePets() {
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("status", "available");
        Response res = RequestBuilder.getRequest(getBaseUrl(), "/pet/findByStatus", queryParams);

        Assert.assertEquals(res.getStatusCode(), 200);
        List<PetResponse> petResponses = res.as(new TypeRef<ArrayList<PetResponse>>() {});
        // Para verificar decidì usar aleatoriedad, tomando X cantidad de mascotas de la respuesta, y verificar que su estado es efectivamente "available"
        // Lo ví más viable que recorrer toda la respuesta
        int X = Math.min(10, petResponses.size());
        for (int i = 0; i < X; i++) {
            int random_index = (int) (Math.random() * petResponses.size());
            PetResponse petResponse = petResponses.get(random_index);
            petResponses.remove(random_index);
            Assert.assertEquals(petResponse.getStatus(), "available");
        }

    }
    @Test(testName = "Consultar los datos de una mascota en específico")
    public void getPetById(){
        Response res = RequestBuilder.getRequest(getBaseUrl(), "/pet/"+petId);
        Assert.assertEquals(res.getStatusCode(), 200);
        PetResponse petResponse = res.as(PetResponse.class);
        Assert.assertNotNull(petResponse.getName());
        Assert.assertNotNull(petResponse.getStatus());
    }
    @Test(testName = "Consultar los datos de una mascota con un id inexistente retorna un 404")
    public void getPetByNonExistentId(){
        Response res = RequestBuilder.getRequest(getBaseUrl(), "/pet/342190");
        Assert.assertEquals(res.getStatusCode(), 404);
    }
    @AfterClass
    public void deletePet(){
        // Tras acabar todos los tests, considero buena idea eliminar la mascota que se creo paa las pruebas
        RequestBuilder.deleteRequest(getBaseUrl(), "/pet/"+petId);
    }
}
