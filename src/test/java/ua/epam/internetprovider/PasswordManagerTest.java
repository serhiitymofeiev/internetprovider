package ua.epam.internetprovider;

import org.junit.Test;
import ua.epam.internetprovider.db.entity.User;
import ua.epam.internetprovider.db.services.IUserService;
import ua.epam.internetprovider.db.services.UserServiceImpl;

import static org.junit.Assert.*;

public class PasswordManagerTest {
    @Test
    public void passwordManagerTest(){
        IUserService service = new UserServiceImpl();

        String login = "admin1";
        User user = service.findByLogin(login);
        String wrongPass = "wrongness";
        String pass = user.getPassword();
        String correctPass = "admin1";

        assertEquals(pass, correctPass);
        assertNotEquals(wrongPass, correctPass);
    }

}


