package ru.paragon.rest.clients;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import ru.paragon.config.ApiConfig;
import ru.paragon.rest.clients.model.CustomerProductApiResponse;
import ru.paragon.rest.clients.model.CustomerSessionApiResponse;
import ru.paragon.rest.clients.model.KindAndProductConstraintsApiResponse;
import ru.paragon.rest.clients.model.SerialNumberApiResponse;

import static io.restassured.RestAssured.given;

public class ApiClient {
    static ApiConfig config = ConfigFactory.create(ApiConfig.class);
    String clientId = "2afa3e45-496a-49fe-9d64-fa09e8c4b828";
    String pwdHash = "345990f25d98835092dd48738db17b2223b100ccf9794de9a28c062c193b1a80b62377c07995b0ff02df92e8403530cc1ace2e51a26bb01db1526a33149ee99e";
    String urlPrefix = config.urlPrefix() + "#/password-reset?email=pegafragaka-2961@yopmail.com&code=";
    String email = "pegafragaka-2961@yopmail.com";

    @Step("Получить sessionId пользователя")
    public CustomerSessionApiResponse getSessionCustomer() {
        Response response = given()
                .baseUri(config.url())
                .basePath("/rest/rs/customer/session?")
                .queryParam("urlPrefix", urlPrefix)
                .contentType(ContentType.TEXT)
                .header("loginOrEmail", email)
                .header("pwd-hash", pwdHash)
                .header("client-id", clientId)
                .get();
        response.then().statusCode(200);
        return response.as(CustomerSessionApiResponse.class);
    }

    @Step("Зарегистировать невалидный серийный номер")
    public SerialNumberApiResponse registerWrongSerialNumber(String sessionId, Long serialNumber) {
        Response response = given()
                .baseUri(config.url())
                .basePath(String.format("rest/rs/customer/serial/register/", serialNumber))
                .contentType(ContentType.TEXT)
                .header("client-id", clientId)
                .header("session-id", sessionId)
                .put();
        response.then().statusCode(404);
        return response.as(SerialNumberApiResponse.class);
    }

    @Step("Получить список продуктов пользователя")
    public CustomerProductApiResponse getListProducts(String sessionId) {
        Response response = given()
                .baseUri(config.url())
                .basePath("/rest/rs/customer/serial")
                .contentType(ContentType.JSON)
                .contentType(ContentType.TEXT)
                .header("client-id", clientId)
                .header("session-id", sessionId)
                .get();
        response.then().statusCode(200);
        return response.as(CustomerProductApiResponse.class);
    }

    @Step("Получить список типов Kind, ProductConstraints для new request")
    public KindAndProductConstraintsApiResponse getKindAndProductConstraints(String sessionId) {
        Response response = given()
                .baseUri(config.url())
                .basePath("/rest/rs/customer/support/request/kind")
                .contentType(ContentType.JSON)
                .contentType(ContentType.TEXT)
                .header("client-id", clientId)
                .header("session-id", sessionId)
                .get();
        response.then().statusCode(200);
        return response.as(KindAndProductConstraintsApiResponse.class);
    }
}
