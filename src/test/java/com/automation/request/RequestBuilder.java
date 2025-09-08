package com.automation.request;

import com.automation.config.Config;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;

import java.util.Map;

public class RequestBuilder {
    public static final String baseUrl = Config.getProperty("baseUrl");

    // Permite hacer una petición GET a la ruta especificada, partiendo de la baseUrl
    public static Response getRequest(String path) {
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri(baseUrl)
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());

        return requestSpecification.get(path);
    }
    // Permite hacer una petición GET a la ruta especificada y agrega queryParams, partiendo de la baseUrl
    public static Response getRequest(String path, Map<String, ?> queryParams) {
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri(baseUrl)
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .queryParams(queryParams);

        return  requestSpecification.get(path);
    }
    // Permite hacer una petición POST a la ruta especificada, partiendo de la baseUrl
    public static Response postRequest(String path, Object body) {
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri(baseUrl)
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(body);
        // Agrega cómo header la api key ya que los posts de esta api, aunque no estén 100% protegidos, sin la key no siempre funcionan
        requestSpecification.header("x-api-key", "special-key");
        return  requestSpecification.post(path);
    }

    // Permite hacer una petición DELETE a la ruta especificada, partiendo de la baseUrl
    public static Response deleteRequest(String path) {
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri(baseUrl)
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());

        return  requestSpecification.delete(path);
    }
}
