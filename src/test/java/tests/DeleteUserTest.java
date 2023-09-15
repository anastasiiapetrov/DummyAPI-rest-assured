package tests;

import dto.CreateUserRequest;
import dto.DeleteUser;
import dto.UnsuccessDeleteUser;
import dto.UserFull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.BaseTest.*;


public class DeleteUserTest {

    @Test
    public void deleteExistingUser(){
        CreateUserRequest requestBody = CreateUserRequest.builder()
                .firstName("John")
                .lastName("Johnson")
                .email(getRandomEmail())
                .build();
        UserFull userFullResponse =
                postRequest("/user/create", 200, requestBody)
                        .body().jsonPath().getObject("", UserFull.class);

     DeleteUser delete = deleteRequest("/user/" + userFullResponse.getId(), 200)
             .body().jsonPath().getObject("", DeleteUser.class);

     assertEquals(userFullResponse.getId(), delete.getId());

}

@Test
        public void deleteDeletedUser(){
            CreateUserRequest requestBody = CreateUserRequest.builder()
                    .firstName("John")
                    .lastName("Johnson")
                    .email(getRandomEmail())
                    .build();
            UserFull userFullResponse =
                    postRequest("/user/create", 200, requestBody)
                            .body().jsonPath().getObject("", UserFull.class);

            DeleteUser delete = deleteRequest("/user/" + userFullResponse.getId(), 200)
                    .body().jsonPath().getObject("", DeleteUser.class);

            UnsuccessDeleteUser responseError = deleteRequest("/user/" + userFullResponse.getId(), 404)
                    .body().jsonPath().getObject("", UnsuccessDeleteUser.class);
            assertEquals("RESOURCE_NOT_FOUND", responseError.getError());
        }

        @Test
        public void deleteNotExistingUser(){

            UnsuccessDeleteUser responseError = deleteRequest("/user/sdjkfhif48", 400)
                    .body().jsonPath().getObject("", UnsuccessDeleteUser.class);
            assertEquals("PARAMS_NOT_VALID", responseError.getError());
        }
}