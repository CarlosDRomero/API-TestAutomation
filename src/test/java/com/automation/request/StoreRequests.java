package com.automation.request;

import com.automation.model.user.OrderRequest;
import io.restassured.response.Response;

public class StoreRequests {
    public static Response createPetOrder(long petId, int quantity, String status, boolean complete) {
        OrderRequest orderRequest = OrderRequest.builder()
                .petId(petId)
                .quantity(quantity)
                .status(status)
                .complete(complete)
                .build();
        return RequestBuilder.postRequest("/store/order", orderRequest);
    }
}
