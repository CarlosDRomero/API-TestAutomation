package com.automation.request;

import com.automation.model.user.User;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class UserRequests {
    // Petición para crear un usuario
    public static Response createUser(
            int id, String userName, String firstName, String lastName,
            String email, String password, String phone, int userStatus
    ) {
        User createUserRequest = User.builder()
                .id(id)
                .userName(userName)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .phone(phone)
                .userStatus(userStatus)
                .build();
        Response res;
        return RequestBuilder.postRequest("/user", createUserRequest);
    }

    // Petición para buscar y obtener un usuario por medio del userName
    public static Response getUserByUserName(String userName) {
        return RequestBuilder.getRequest("/user/"+userName);
    }

    public static Response loginUser(String userName, String password) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("username", userName);
        queryParams.put("password", password);

        return RequestBuilder.getRequest("/user/login", queryParams);
    }
    public static Response logoutUser() {
        return RequestBuilder.getRequest("/user/logout");
    }

}
