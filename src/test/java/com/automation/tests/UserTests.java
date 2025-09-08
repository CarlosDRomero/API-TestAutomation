package com.automation.tests;

import com.automation.model.user.User;
import com.automation.request.UserRequests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTests extends TestRunner {

    final int id = 1;
    final String userName = "Felipe_0987";
    final String firstName = "Felipe";
    final String lastName = "Rodríguez";
    final String email = "felipe@correo.com";
    final String password = "12345";
    final String phone = "1234567890";
    final int userStatus = 0;

    @Test(testName = "Se puede crear un usuario con los campos correctos")
    public void correctUserCreation(){
        // Creación de usuario
        Response res;
        res = UserRequests.createUser(id, userName, firstName, lastName, email, password, phone, userStatus);
        Assert.assertEquals(res.getStatusCode(), 200);
        // Validación de la creación del usuario
        res = UserRequests.getUserByUserName(userName);
        Assert.assertEquals(res.getStatusCode(), 200);
        User user = res.as(User.class);

        Assert.assertEquals(user.getId(),1);
        Assert.assertEquals(user.getUserName(),userName);
        Assert.assertEquals(user.getFirstName(),firstName);
        Assert.assertEquals(user.getLastName(),lastName);
        Assert.assertEquals(user.getEmail(), email);
        Assert.assertEquals(user.getPassword(),password);
        Assert.assertEquals(user.getPhone(),phone);
        Assert.assertEquals(user.getUserStatus(),userStatus);

    }
    @Test(testName = "El sistema no debe dejar crear un usuario con datos en un formato incorrecto")
    public void userCreationFail(){
        // Creación de usuario
        Response res;
        res = UserRequests.createUser(id, userName, firstName, lastName, "esto no es un correo", password, phone, userStatus);
        Assert.assertEquals(res.getStatusCode(), 400);
    }
    @Test(testName = "Se puede iniciar sesión con un usuario recién creado")
    public void createdUserLogin(){
        // Creación del usuario
        Response res;
        res = UserRequests.createUser(id, userName, firstName, lastName, email, password, phone, userStatus);
        Assert.assertEquals(res.getStatusCode(), 200);
        // Login del usuario
        res = UserRequests.loginUser(userName, password);

        Assert.assertEquals(res.getStatusCode(), 200);
    }
    @Test(testName = "Se puede hacer un logout")
    public void logout() {
        // En este caso el logout no pide ningun parametro entonces es tan simple como comprobar el estado 200, al menos con esta API.
        Response res = UserRequests.logoutUser();
        Assert.assertEquals(res.getStatusCode(), 200);
    }
}
