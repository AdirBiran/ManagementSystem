
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.api.FxAssert;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

import java.security.Key;

import static junit.framework.TestCase.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GuiTestClass extends UITests.InitiationClass {

    private final String testMail = "autoTest@mail.com";
    private final String testPass = "Aa123456";


    @Test
    public void A_registerTest()
    {
        clickOn("#register");
        sleep(1000);
        clickOn("#mail").write(testMail);
        clickOn("#pass").write(testPass);
        clickOn("#passVerify").write(testPass);
        clickOn("#firstName").write("First Name");
        clickOn("#lastName").write("Last Name");
        clickOn("#phone").write("0577777777");
        clickOn("#address").write("Beer Sheba Sadly street");
        sleep(1000);
        clickOn("#registerButton");
        sleep(2000);
        press(KeyCode.ENTER);
        sleep(1000);

        Scene currScene = stage.getScene();
        Node logout = currScene.lookup("#logout");
        assertNotNull(logout);

    }

    @Test
    public void B_logoutTest()
    {
        Scene currScene = stage.getScene();
        Node logout = currScene.lookup("#logout");
        assertNotNull(logout);

        clickOn("#logout");
        sleep(2000);

        currScene = stage.getScene();
        Node login = currScene.lookup("#login");

        assertNotNull(login);

    }

    @Test
    public void C_viewInformations()
    {
        clickOn("#viewInformation");
        clickOn("#selectSubjects");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        sleep(2000);

        clickOn("#selectLabel");
        clickOn("#selectSubjects");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        sleep(2000);

        clickOn("#selectSubjects");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        sleep(2000);

        clickOn("#selectSubjects");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        sleep(2000);

        clickOn("#selectSubjects");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        sleep(2000);

        clickOn("#selectSubjects");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        sleep(2000);
    }

    @Test
    public void D_loginFan()
    {
        clickOn("#tf_email").write(testMail);
        clickOn("#tf_password").write(testPass);

        clickOn("#login");
        sleep(2000);

        Scene currScene = stage.getScene();
        Node logout = currScene.lookup("#logout");
        assertNotNull(logout);
    }


}
