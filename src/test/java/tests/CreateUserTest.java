package tests;

import dto.CreateUserRequest;
import dto.UnsuccessCreateUserResponse;
import dto.UserFull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static tests.BaseTest.getRandomEmail;
import static tests.BaseTest.postRequest;

public class CreateUserTest {

    @Test
    public void successCreateUser(){
        //CreateUserRequest requestBody = new CreateUserRequest("Brick", "John", getRandomEmail());
       CreateUserRequest requestBody = CreateUserRequest.builder()
                       .firstName("John")
                               .lastName("Johnson")
                                       .email(getRandomEmail())
                                               .build();
        UserFull userFullResponse =
        postRequest("/user/create", 200, requestBody)
                .body().jsonPath().getObject("", UserFull.class);
        // firstName and lastName are not empty
        assertFalse(userFullResponse.getFirstName().isEmpty(), "firstName is empty");
        assertFalse(userFullResponse.getLastName().isEmpty(), "lastName is empty");
        // firstName response equals to firstName request
        assertEquals(requestBody.getFirstName(), userFullResponse.getFirstName());
        //email response equals to email request
        assertEquals(requestBody.getEmail(), userFullResponse.getEmail());
        assertEquals(requestBody.getEmail().toLowerCase(), userFullResponse.getEmail().toLowerCase());
        //RegisterDate is equals to UpdatedDate
        assertEquals(userFullResponse.getRegisterDate(), userFullResponse.getUpdatedDate());
    }

    @Test
    public void createUserWithoutEmail (){
        CreateUserRequest requestBody = CreateUserRequest.builder()
                .firstName("John")
                .lastName("Johnson")
                .build();
        UnsuccessCreateUserResponse response =
                postRequest("/user/create", 400, requestBody)
                        .body().jsonPath().getObject("data", UnsuccessCreateUserResponse.class);


        assertEquals("Path `email` is required.", response.getEmail());
    }

    @Test
    public void createUserWithoutFirstName (){
        CreateUserRequest requestBody = CreateUserRequest.builder()
                .lastName("Johnson")
                .email(getRandomEmail())
                .build();
        UnsuccessCreateUserResponse response =
                postRequest("/user/create", 400, requestBody)
                        .body().jsonPath().getObject("data", UnsuccessCreateUserResponse.class);


        assertEquals("Path `firstName` is required.", response.getFirstName());
    }
    @Test
    public void createUserWithoutLastName (){
        CreateUserRequest requestBody = CreateUserRequest.builder()
                .firstName("John")
                .email(getRandomEmail())
                .build();
        UnsuccessCreateUserResponse response =
                postRequest("/user/create", 400, requestBody)
                        .body().jsonPath().getObject("data", UnsuccessCreateUserResponse.class);


        assertEquals("Path `lastName` is required.", response.getLastName());
    }

    @Test
    public void createUserWithoutBody(){
      CreateUserRequest requestBody = CreateUserRequest.builder()
                .build();
        UnsuccessCreateUserResponse response =
                postRequest("/user/create", 400, requestBody)
                        .body().jsonPath().getObject("data", UnsuccessCreateUserResponse.class);

        assertEquals("Path `firstName` is required.", response.getFirstName());
        assertEquals("Path `lastName` is required.", response.getLastName());
        assertEquals("Path `email` is required.", response.getEmail());

    }


}
