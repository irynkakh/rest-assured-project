import dto.UserDetails;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.OperateUsersApiSteps;

import java.util.Arrays;
import java.util.Collection;

@RunWith(SerenityParameterizedRunner.class)
@Concurrent
public class OperateUserCheck {
    private final String comment;
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final int userStatus;
    private final String phone;
    private final String email;

    @Steps
    private OperateUsersApiSteps operateUsersApiSteps;

    public OperateUserCheck(String comment, String firstName, String lastName, String username, String password, int userStatus, String phone, String email) {
        this.comment = comment;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.userStatus = userStatus;
        this.phone = phone;
        this.email = email;
    }

    @TestData(columnNames = "test description, first name, last name, username, password, user status, phone, email")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"query with valid credentials", "FirstNameTest1", "LastNameTest1", "newUser1", "AaTest1", 1, "+20123234545", "test1@yandex.ua"},
                {"query with valid credentials", "FirstNameTest2", "LastNameTest2", "newUser2", "AaTest2", 2, "+48123234545", "test2@e-mail.ua"},
        });
    }

    @Test
    public void logUserIntoTheSystem() {
        operateUsersApiSteps.checkIsUserCreated(firstName, lastName, username, password, userStatus, phone, email);
        UserDetails userNameActual = operateUsersApiSteps.getInfoAboutUser(username);
        operateUsersApiSteps.checkUserName(userNameActual, username);
        operateUsersApiSteps.checkIsUserLogged(username, password);
        operateUsersApiSteps.checkIsUserLoggedOut();
        operateUsersApiSteps.checkIsUserDeleted(username);
    }
}
