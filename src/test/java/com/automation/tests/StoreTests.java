package com.automation.tests;

import com.automation.model.user.OrderResponse;
import com.automation.model.user.PetResponse;
import com.automation.request.PetRequests;
import com.automation.request.StoreRequests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StoreTests extends TestRunner{
    private long petId;

    private final int quantity = 2;
    private final String status = "placed";
    private final boolean complete = false;

    @BeforeClass
    public void beforeClass(){
        // Como precondición, para evitar problemas con la creación de la orden de compra, hacer la creación de una mascota para tener un ID mas seguro
        PetResponse petResponse = PetRequests.createPet("TestPet", "pending")
                .as(PetResponse.class);
        petId = petResponse.getId();
    }

    @Test(testName = "Crear una orden de compra para una mascota")
    public void petOrder(){
        // Se crea una orden para comprar la mascota que fue creada en el BeforeMethod
        Response res = StoreRequests.createPetOrder(petId, quantity, status, complete);
        Assert.assertEquals(res.statusCode(), 200);
        OrderResponse orderResponse = res.as(OrderResponse.class);

        Assert.assertEquals(orderResponse.getPetId(), petId);
        Assert.assertEquals(orderResponse.getQuantity(), quantity);
        Assert.assertEquals(orderResponse.getStatus(), status);
        Assert.assertEquals(orderResponse.isComplete(), complete);
    }
    @Test(testName = "No se debería poder generar una orden con cantidad de 0")
    public void petNoOrder(){
        // Se intenta generar una orden de compra colocando una cantidad de 0, esperando que el sistema no lo admita
        Response res = StoreRequests.createPetOrder(petId, 0, status, complete);
        Assert.assertEquals(res.statusCode(), 400);
    }
    @AfterClass
    public void deletePet(){
        // Tras acabar todos los tests, considero buena idea eliminar la mascota que se creo paa las pruebas
        PetRequests.deletePetById(petId);
    }

}
