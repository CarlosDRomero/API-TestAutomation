package com.automation.tests;

import com.automation.model.user.OrderRequest;
import com.automation.model.user.OrderResponse;
import com.automation.model.user.PetRequest;
import com.automation.model.user.PetResponse;
import com.automation.request.RequestBuilder;
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
        PetRequest petRequest =  new PetRequest("TestPet", "pending");

        PetResponse petResponse = RequestBuilder.postRequest(getBaseUrl(), "/store/order", petRequest).as(PetResponse.class);
        petId = petResponse.getId();
    }

    @Test(testName = "Crear una orden de compra para una mascota")
    public void petOrder(){
        OrderRequest orderRequest = OrderRequest.builder()
                .petId(petId)
                .quantity(quantity)
                .status(status)
                .complete(complete)
                .build();
        Response res = RequestBuilder.postRequest(getBaseUrl(), "/store/order", orderRequest);
        Assert.assertEquals(res.statusCode(), 200);
        OrderResponse orderResponse = res.as(OrderResponse.class);

        Assert.assertEquals(orderResponse.getPetId(), petId);
        Assert.assertEquals(orderResponse.getQuantity(), quantity);
        Assert.assertEquals(orderResponse.getStatus(), status);
        Assert.assertEquals(orderResponse.isComplete(), complete);
    }
    @Test(testName = "No se debería poder generar una orden con cantidad de 0")
    public void petNoOrder(){
        OrderRequest orderRequest = OrderRequest.builder()
                .petId(petId)
                .quantity(0)
                .status(status)
                .complete(complete)
                .build();
        Response res = RequestBuilder.postRequest(getBaseUrl(), "/order", orderRequest);
        Assert.assertEquals(res.statusCode(), 400);
    }
    @AfterClass
    public void deletePet(){
        // Tras acabar todos los tests, considero buena idea eliminar la mascota que se creo paa las pruebas
        RequestBuilder.deleteRequest(getBaseUrl(), "/pet/"+petId);
    }

}
