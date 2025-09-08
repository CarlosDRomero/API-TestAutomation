package com.automation.tests;

import com.automation.model.user.PetResponse;
import com.automation.request.PetRequests;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class PetsTests extends TestRunner {
    private long petId;

    @BeforeClass
    public void createPet(){
        // Como precondición, para evitar problemas con los GET he decidido hacer la creación de una mascota para tener un ID mas seguro
        PetResponse petResponse = PetRequests.createPet("TestPet", "pending")
                .as(PetResponse.class);
        petId = petResponse.getId();
    }

    @Test(testName = "Obtener todas las mascotas en estado 'Disponible'")
    public void availablePets() {
        Response res = PetRequests.getPetsByStatus("available");
        Assert.assertEquals(res.getStatusCode(), 200);

        List<PetResponse> petResponses = res.as(new TypeRef<ArrayList<PetResponse>>() {});
        // Para verificar decidí usar aleatoriedad, tomando X cantidad de mascotas de la respuesta, y verificar que su estado es efectivamente "available"
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
        // Se obtiene una mascota mediante su Id para luego comprobar que contiene los campos esperados
        Response res = PetRequests.getPetById(petId);
        Assert.assertEquals(res.getStatusCode(), 200);
        PetResponse petResponse = res.as(PetResponse.class);

        Assert.assertNotNull(petResponse.getName());
        Assert.assertNotNull(petResponse.getStatus());
    }
    @Test(testName = "Consultar los datos de una mascota con un id inexistente retorna un 404")
    public void getPetByNonExistentId(){
        // Se intenta buscar una mascota con un ID que seguramente no existe, esperando un 404 como respuesta
        Response res = PetRequests.getPetById(342190);
        Assert.assertEquals(res.getStatusCode(), 404);
    }
    @AfterClass
    public void deletePet(){
        // Tras acabar todos los tests, considero buena idea eliminar la mascota que se creo paa las pruebas
        PetRequests.deletePetById(petId);
    }
}
