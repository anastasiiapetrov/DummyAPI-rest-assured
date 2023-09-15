package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class BaseTest {
    final static String BASE_URI = "https://dummyapi.io/data/v1";

    final static String APP_ID_VALUE = "63ed1c84110e33e9f5c0f4f2";

    static RequestSpecification specification = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .addHeader("app-id", APP_ID_VALUE)
            .build();

    public static Response getRequest(String endPoint, Integer responseCode){
       Response response = given()
               .spec(specification)
               .when()
               .log().all()
               .get(endPoint)
               .then().log().all()
               .statusCode(responseCode)
               .extract().response();
       return response;
    }

    public static Response postRequest(String endPoint, Integer responseCode, Object body){
        Response response = given()
                .spec(specification)
                .body(body)
                .when()
                .log().all()
                .post(endPoint)
                .then().log().all()
                .statusCode(responseCode)
                .extract().response();
        return response;
    }

    public static Response deleteRequest(String endPoint, Integer responseCode){
        Response response = given()
                .spec(specification)
                .when()
                .log().all()
                .delete(endPoint)
                .then().log().all()
                .statusCode(responseCode)
                .extract().response();
        return response;
    }

    public static String getRandomEmail() {
        String SALTCHARS = "abcdefghijklmopqrstuwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr + "@gmail.com";

    }
}
