package tests;

import dto.UserFull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static tests.BaseTest.getRequest;

public class GetUserByIdTest {

    String requestUserId = "60d0fe4f5311236168a109cd";
    @Test
    public void getUserById(){
       UserFull userFull = getRequest("/user/" + requestUserId, 200)
               .body().jsonPath().getObject("",UserFull.class);

       //check id is not empty
       assertFalse(userFull.getId().isEmpty());
        // check that id from response equals to requestUserId
        assertEquals(requestUserId, userFull.getId());
        //check that picture value ends with ".jpg"
        assertTrue(userFull.getPicture().endsWith(".jpg"), "actual picture format is not jpg");
        // check that email ends with @example.com
        assertTrue(userFull.getEmail().endsWith("@example.com"));
        // 1945 is dateOfBirth
        assertTrue(userFull.getDateOfBirth().startsWith("1945"), "dateOfBirth is not 1945" + userFull.getDateOfBirth());

    }
}
