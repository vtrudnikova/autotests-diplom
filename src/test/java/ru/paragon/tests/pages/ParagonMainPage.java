package ru.paragon.tests.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParagonMainPage {
    @Step("Открыть главную страницу сайта")
    public void openPage() {
        open("https://my.paragon-software.com/");
    }

    @Step("Ввести некорректный email")
    public void enterEmail(String email) {
        $(byXpath("//*[@type='email']")).setValue(email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        $(byXpath("//*[@type='password']")).setValue(password);
    }

    @Step("Нажать кнопку Войти")
    public void pressSubmit() {
        $(byXpath("//*[@type='submit']")).click();
    }

    @Step("Проверить, что выводится сообщение The email or password you entered is incorrect. Please try again. ")
    public void checkTitleForUnregisteredUser() {
        $(byXpath("//*[text()='The email or password you entered is incorrect. Please try again.']"))
                .shouldBe(text("The email or password you entered is incorrect. Please try again."));
    }

    @Step("Проверить, что выводится сообщение Please enter a valid email address.")
    public void checkTitleForLoginWithIncorrectEmail() {
        $(byXpath("//*[text()='Please enter a valid email address.']"))
                .shouldBe(text("Please enter a valid email address."));
    }

    @Step("Изменить локализацию на странице")
    public void changesLocalization(String language) {
        Selenide.$x("//*[@class='glyphicon glyphicon-globe']").click();
        Selenide.$x(String.format("//a[text() = '%s']",language)).click();

    }

    @Step("Проверить что на странице текст с Sign in на Авторизация")
    public void checkTitleAuthorizations() {
        $(byXpath("//*[@ng-if='!ctrl.isSimpleModeInApp'][text()='Авторизация']")).shouldBe(text("Авторизация"));
    }

    @Step("Нажать Forgot password?")
    public void pressGoToResetPassword() {
        $(byXpath("//a[ text() ='Forgot password?']")).click();
    }


    @Step("Нажать на ссылку Return to Sign in")
    public void pressEnterReturnToSignIn() {
        $(byXpath("//a[ text() ='Return to Sign in']")).click();
    }

    @Step("Проверить сообщение на странице авторизации")
    public void checkMessage() {
        $(byXpath("//*[ text() ='You can use your personal customer account to submit " +
                "a support request to the technical support and track the status of your" +
                " requests, manage your software licenses, download your software or the " +
                "latest update, or obtain an upgrade at a discount.']"))
                .shouldBe(text("You can use your personal customer account to submit a " +
                        "support request to the technical support and track the status " +
                        "of your requests, manage your software licenses, download your " +
                        "software or the latest update, or obtain an upgrade at a discount."));
    }

    @Step("Проверить ссылку Sign in")
    public String checkLinkLogin() {
        String link = $(byXpath("//a[text() ='Sign in']")).getAttribute("href");
        return link;

    }
}
