package ru.paragon.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.paragon.tests.pages.ParagonMainPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ParagonTests extends TestBase {
    ParagonMainPage startPage = new ParagonMainPage();
    static Faker faker = new Faker();
    public String emailAddress = faker.internet().emailAddress();
    public String passWord = "Qwerty111";

    @BeforeAll
    static void configureBaseUrl() {
        ParagonMainPage startPage = new ParagonMainPage();
        startPage.openPage();
        startPage.changesLocalization("Русский");
    }

    @Test()
    @DisplayName("Невозможность логина незарегистрированным пользователем")
    void impossibilityOfRegistrationUnregisteredUsers() {
        startPage.openPage();
        startPage.enterEmail("test@test.com");
        startPage.enterPassword(passWord);
        startPage.pressSubmit();
        startPage.checkTitleForUnregisteredUser();
    }

    @Test()
    @DisplayName("Невозможность логина c некорректным email")
    void impossibilityOfLoginWithIncorrectEmail() {
        startPage.openPage();
        startPage.enterEmail(emailAddress + 1);
        startPage.enterPassword(passWord);
        startPage.pressSubmit();
        startPage.checkTitleForLoginWithIncorrectEmail();
    }

    @Test()
    @DisplayName("Можно изменить локализацию")
    void checkLocalizationChange() {
        startPage.openPage();
        startPage.changesLocalization("English");
        startPage.checkTitleAuthorizations();
    }

    @Test()
    @DisplayName("Можно вернуться к форме авторизации со страницы восстановления пароля")
    void returnToAuthorizationPageFromTheResetPasswordPage() {
        startPage.openPage();
        startPage.pressGoToResetPassword();
        startPage.pressEnterReturnToSignIn();
        startPage.checkMessage();
    }

    @Test()
    @DisplayName("На странице логина есть ссылка Войти")
    void theLoginPageContainsTheLoginLink() {
        startPage.openPage();
        String link = startPage.checkLinkLogin();
        assertThat(link).isEqualTo("https://my.paragon-software.com/#/login");

    }
}