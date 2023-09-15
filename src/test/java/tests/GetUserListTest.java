package tests;

import dto.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static tests.BaseTest.getRequest;

public class GetUserListTest {

    @Test
    public void getUserList(){
       List<User> users = getRequest("/user", 200).body().jsonPath().getList("data", User.class);
        System.out.println(users.get(0).getFirstName());
        //check that first user in list has a first name
       assertFalse(users.get(0).getFirstName().isEmpty());
        //all users have names
        for (User user: users) {
            assertFalse(user.getFirstName().isEmpty());
        }


    }

}
