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

    @Step("Получить sessionId пользователя")
    public CustomerSessionApiResponse getSessionCustomer() {
        Response response = given()
                .baseUri(config.url())
                .basePath("/rest/rs/customer/session?")
                .queryParam("urlPrefix", config.urlPrefix())
                .contentType(ContentType.TEXT)
                .header("loginOrEmail", config.email())
                .header("pwd-hash", config.pwdHash())
                .header("client-id", config.clientId())
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
                .header("client-id", config.clientId())
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
                .header("client-id", config.clientId())
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
                .header("client-id", config.clientId())
                .header("session-id", sessionId)
                .get();
        response.then().statusCode(200);
        return response.as(KindAndProductConstraintsApiResponse.class);
    }
}
