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

    @Step("Ввести email")
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

    @Step("Проверить, что выводится сообщение Покупатель с таким login/пароль - не существует ")
    public void checkTitleForUnregisteredUser() {
        $(byXpath("//*[text()='Покупатель с таким login/пароль - не существует']"))
                .shouldBe(text("Покупатель с таким login/пароль - не существует"));
    }

    @Step("Проверить, что выводится сообщение Введите правильный адрес")
    public void checkTitleForLoginWithIncorrectEmail() {
        $(byXpath("//*[text()='Введите правильный адрес']"))
                .shouldBe(text("Введите правильный адрес"));
    }

    @Step("Изменить локализацию на странице")
    public void changesLocalization(String language) {
        Selenide.$x("//*[@class='glyphicon glyphicon-globe']").click();
        Selenide.$x(String.format("//a[text() = '%s']",language)).click();

    }

    @Step("Проверить что на странице текст Авторизация изменился на Sign in")
    public void checkTitleAuthorizations() {
        $(byXpath("//*[@ng-if='!ctrl.isSimpleModeInApp'][text()='Sign in']")).shouldBe(text("Sign in"));
    }

    @Step("Нажать Забыли пароль")
    public void pressGoToResetPassword() {
        $(byXpath("//a[ text() ='Забыли пароль?']")).click();
    }


    @Step("Нажать на ссылку Перейти к странице авторизации")
    public void pressEnterReturnToSignIn() {
        $(byXpath("//a[ text() ='Перейти к странице авторизации']")).click();
    }

    @Step("Проверить сообщение на странице авторизации")
    public void checkMessage() {
        $(byXpath("//*[ text() ='Используйте свой Личный Кабинет для запросов в службу " +
                "поддержки, для управления лицензиями на программные продукты, для загрузки " +
                "установочных файлов или обновлений, а также для получения специальных предложений.']"))
                .shouldBe(text("Используйте свой Личный Кабинет для запросов в службу поддержки," +
                        " для управления лицензиями на программные продукты, для загрузки установочных " +
                        "файлов или обновлений, а также для получения специальных предложений."));
    }

    @Step("Проверить ссылку Войти")
    public String checkLinkLogin() {
        String link = $(byXpath("//a[text() ='Войти']")).getAttribute("href");
        return link;

    }
}
