package ru.paragon.tests.ui;

import com.github.javafaker.Faker;
import io.qameta.allure.AllureId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.paragon.pages.ParagonMainPage;
import ru.paragon.tests.TestBase;

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
        startPage.changesLocalization("English");
    }

    @Test()
    @AllureId("47018")
    @DisplayName("Невозможность логина незарегистрированным пользователем")
    void impossibilityOfRegistrationUnregisteredUsers() {
        startPage.openPage();
        startPage.enterEmail("test@test.com");
        startPage.enterPassword(passWord);
        startPage.pressSubmit();
        startPage.checkTitleForUnregisteredUser();
    }

    @Test()
    @AllureId("47017")
    @DisplayName("Невозможность логина c некорректным email")
    void impossibilityOfLoginWithIncorrectEmail() {
        startPage.openPage();
        startPage.enterEmail(emailAddress + 1);
        startPage.enterPassword(passWord);
        startPage.pressSubmit();
        startPage.checkTitleForLoginWithIncorrectEmail();
    }

    @Test()
    @AllureId("47014")
    @DisplayName("Можно изменить локализацию")
    void checkLocalizationChange() {
        startPage.openPage();
        startPage.changesLocalization("Русский");
        startPage.checkTitleAuthorizations();
    }

    @Test()
    @AllureId("47016")
    @DisplayName("Можно вернуться к форме авторизации со страницы восстановления пароля")
    void returnToAuthorizationPageFromTheResetPasswordPage() {
        startPage.openPage();
        startPage.pressGoToResetPassword();
        startPage.pressEnterReturnToSignIn();
        startPage.checkMessage();
    }

    @Test()
    @AllureId("47015")
    @DisplayName("На странице логина есть ссылка Sign in")
    void theLoginPageContainsTheLoginLink() {
        startPage.openPage();
        String link = startPage.checkLinkLogin();
        assertThat(link).isEqualTo("https://my.paragon-software.com/#/login");

    }
}