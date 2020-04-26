package Unit;

import Domain.User;
import Domain.UserFactory;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.*;

public class HasPageAuthorizationTest {

    @Test
    public void uploadToPage() {
        Object[] player = UserFactory.getNewPlayer("Leonardo","Messi","hasPageTest@gmail.com",new Date(),"",250);
        assertNotNull(player);
        HasPageAuthorization authorization = (HasPageAuthorization)((User)player[0]).getRoles().get(0);
        String data = "This is Leonardo Messi's Personal Page! ";
        assertEquals(data, authorization.getPage().getData());
        authorization.uploadToPage("Something Else about Messi");
        assertEquals("Something Else about Messi", authorization.getPage().getData());

    }
}