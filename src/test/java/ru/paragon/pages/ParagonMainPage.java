package ru.paragon.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactOwnText;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ParagonMainPage {
    @Step("Открыть главную страницу сайта")
    public void openPage() {
        open("https://my.paragon-software.com/");
    }

    @Step("Ввести некорректный email")
    public void enterEmail(String email) {
        $(byId("login-form-email")).setValue(email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        $(byId("login-form-password")).setValue(password);
    }

    @Step("Нажать кнопку Войти")
    public void pressSubmit() {
        $x("//*[@class='btn btn-primary ng-binding'][text()='Sign in']").click();
    }

    @Step("Проверить, что выводится сообщение The email or password you entered is incorrect. Please try again. ")
    public void checkTitleForUnregisteredUser() {
        $x("//div[@ng-bind-html='$alert.text']")
                .shouldHave(exactOwnText("The email or password you entered is incorrect. Please try again."));
    }

    @Step("Проверить, что выводится сообщение Please enter a valid email address.")
    public void checkTitleForLoginWithIncorrectEmail() {
        $x("//div[@ng-bind-html='$alert.text']")
                .shouldHave(exactOwnText("Please enter a valid email address."));
    }

    @Step("Изменить локализацию на странице")
    public void changesLocalization(String language) {
        $x("//*[@class='glyphicon glyphicon-globe']").click();
        $(byText(language)).click();
    }

    @Step("Проверить что на странице текст поменялся с Sign in на Авторизация")
    public String checkTitleAuthorizations() {
        SelenideElement language = $x("//span[@ng-bind='locale.selectedLanguage.name']");
        language.shouldBe(Condition.matchText("Русский"), Duration.ofMillis(3000));
        return language.getText();
    }

    @Step("Нажать Forgot password?")
    public void pressGoToResetPassword() {
        $(byText("Forgot password?")).click();
    }

    @Step("Нажать на ссылку Return to Sign in")
    public void pressEnterReturnToSignIn() {
        $(byText("Return to Sign in")).click();
    }

    @Step("Проверить сообщение на странице авторизации")
    public String checkMessage() {
        SelenideElement message = $x("//*[@ng-if='!(ctrl.isSimpleModeInApp || ctrl.isFreeLicenseState)']");
        return message.getText();
    }

    @Step("Проверить ссылку Sign in")
    public String checkLinkLogin() {
        return $(byText("Sign in")).getAttribute("href");
    }
}
