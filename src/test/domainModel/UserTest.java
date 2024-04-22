package test.domainModel;

import main.DomainModel.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void user_test() throws Exception{
        User u = new User("user", "username");
        Assertions.assertEquals("user", u.getName());
        Assertions.assertEquals("username", u.getUsername());
    }
}
