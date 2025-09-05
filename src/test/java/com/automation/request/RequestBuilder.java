package com.automation.request;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;

import java.util.Map;

public class RequestBuilder {

    public static Response getRequest(String baseUrl, String path) {
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri(baseUrl)
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());

        return requestSpecification.get(path);
    }
    public static Response getRequest(String baseUrl, String path, Map<String, ?> queryParams) {
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri(baseUrl)
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .queryParams(queryParams);

        return  requestSpecification.get(path);
    }

    public static Response postRequest(String baseUrl, String path, Object body) {
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri(baseUrl)
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(body);
        requestSpecification.header("x-api-key", "special-key");
        return  requestSpecification.post(path);
    }

    public static Response deleteRequest(String baseUrl, String path, Integer id) {
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .pathParams("id", id);

        return  requestSpecification.delete("/{id}");
    }
}
