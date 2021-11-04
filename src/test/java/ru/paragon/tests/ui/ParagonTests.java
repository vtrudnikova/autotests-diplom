package ru.paragon.tests.ui;

import com.github.javafaker.Faker;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import ru.paragon.allure.JiraIssue;
import ru.paragon.allure.JiraIssues;
import ru.paragon.allure.Layer;
import ru.paragon.pages.ParagonMainPage;
import ru.paragon.tests.TestBase;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("UI tests")
@Story("Тесты на главную страницу личного кабинета")
@Layer("web")
@Owner("vtrudnikova")
public class ParagonTests extends TestBase {
    ParagonMainPage startPage = new ParagonMainPage();
    Faker faker = new Faker();
    private String emailAddress = faker.internet().emailAddress();
    private String passWord = "Qwerty111";
    private String infoMessage = "You can use your personal customer account to submit a support" +
            " request to the technical support and track the status of your requests, manage your software licenses," +
            " download your software or the latest update, or obtain an upgrade at a discount.";

    @BeforeEach
    void configureBaseUrl() {
        startPage.openPage();
        startPage.changesLocalization("English");
    }

    @Test()
    @AllureId("4408")
    @JiraIssues({@JiraIssue("HOMEWORK-255")})
    @Tags({@Tag("ui"), @Tag("regress")})
    @DisplayName("Невозможность логина незарегистрированным пользователем")
    void impossibilityOfRegistrationUnregisteredUsers() {
        startPage.openPage();
        startPage.enterEmail("test@test.com");
        startPage.enterPassword(passWord);
        startPage.pressSubmit();
        startPage.checkTitleForUnregisteredUser();
    }

    @Test()
    @AllureId("4409")
    @JiraIssues({@JiraIssue("HOMEWORK-255")})
    @Tags({@Tag("ui"), @Tag("regress")})
    @DisplayName("Невозможность логина c некорректным email")
    void impossibilityOfLoginWithIncorrectEmail() {
        startPage.openPage();
        startPage.enterEmail(emailAddress + 1);
        startPage.enterPassword(passWord);
        startPage.pressSubmit();
        startPage.checkTitleForLoginWithIncorrectEmail();
    }

    @Test()
    @AllureId("4411")
    @JiraIssues({@JiraIssue("HOMEWORK-255")})
    @Tags({@Tag("ui"), @Tag("regress")})
    @DisplayName("Можно изменить локализацию")
    void checkLocalizationChange() {
        startPage.openPage();
        startPage.changesLocalization("Русский");
        startPage.checkTitleAuthorizations();
        String language = startPage.checkTitleAuthorizations();
        assertThat("Русский").isEqualTo(language);
    }

    @Test()
    @AllureId("4407")
    @JiraIssues({@JiraIssue("HOMEWORK-255")})
    @Tags({@Tag("ui"), @Tag("regress")})
    @DisplayName("Можно вернуться к форме авторизации со страницы восстановления пароля")
    void returnToAuthorizationPageFromTheResetPasswordPage() {
        startPage.openPage();
        startPage.pressGoToResetPassword();
        startPage.pressEnterReturnToSignIn();
        startPage.checkMessage();
        String message = startPage.checkMessage();
        assertThat(infoMessage).isEqualTo(message);
    }

    @Test()
    @AllureId("4406")
    @JiraIssues({@JiraIssue("HOMEWORK-255")})
    @Tags({@Tag("ui"), @Tag("regress")})
    @DisplayName("На странице логина есть ссылка Sign in")
    void theLoginPageContainsTheLoginLink() {
        startPage.openPage();
        String link = startPage.checkLinkLogin();
        assertThat("https://my.paragon-software.com/#/login").isEqualTo(link);
    }
}