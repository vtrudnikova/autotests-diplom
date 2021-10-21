package ru.paragon.tests.api;

import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.paragon.allure.JiraIssue;
import ru.paragon.allure.JiraIssues;
import ru.paragon.allure.Layer;
import ru.paragon.rest.clients.ApiClient;
import ru.paragon.rest.clients.model.CustomerProductApiResponse;
import ru.paragon.rest.clients.model.KindAndProductConstraintsApiResponse;
import ru.paragon.rest.clients.model.SerialNumberApiResponse;
import ru.paragon.tests.TestBaseApi;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("API tests")
@Story("Тесты на личный кабинет пользователя")
@Layer("rest")
@Owner("vtrudnikova")
public class ParagonApiTests {
    Long serialNumber = System.currentTimeMillis();
    ApiClient apiClient = new ApiClient();
    TestBaseApi testBaseApi = new TestBaseApi();


    @Test
    @AllureId("5270")
    @JiraIssues({@JiraIssue("HOMEWORK-255")})
    @Tags({@Tag("api"), @Tag("regress")})
    @DisplayName("Нельзя зарегистрировать невалидный серийный номер")
    void cannotRegisterWrongSerialNumber() {
        String sessionId = testBaseApi.getSessionId();
        SerialNumberApiResponse response = apiClient.registerWrongSerialNumber(sessionId, serialNumber);
        String message = response.getError();
        step("Проверить, что вернулось сообщение с текстом message", () -> {
            assertThat(message).isEqualTo("SERIAL_CODE_NOT_FOUND");
        });
    }

    @Test
    @AllureId("5268")
    @JiraIssues({@JiraIssue("HOMEWORK-255")})
    @Tags({@Tag("api"), @Tag("regress")})
    @DisplayName("У нового пользователя нет зарегистрированных продуктов")
    void newUserHasNoRegisteredProducts() {
        String sessionId = testBaseApi.getSessionId();
        CustomerProductApiResponse response = apiClient.getListProducts(sessionId);
        List<Object> products = response.getActivatedCustomerSerialCodes();
        step("Проверить, что вернулся пустой массив", () -> {
            assertThat(products).isEmpty();
        });
    }

    @Test
    @AllureId("5269")
    @JiraIssues({@JiraIssue("HOMEWORK-255")})
    @Tags({@Tag("api"), @Tag("regress")})
    @DisplayName("Проверить, что есть kind с типом Technical issue и productConstraints с типом Free для new request")
    void checkKindWithTechnicalIssueTypeAndProductConstraintsWithFreeType() {
        String sessionId = testBaseApi.getSessionId();
        KindAndProductConstraintsApiResponse response = apiClient.getKindAndProductConstraints(sessionId);
        String kind = response.getKinds().get(0);
        String productConstraints = response.getProductConstraints().get(1);
        step("Проверить, что тип kind = Technical issue", () -> {
            assertThat(kind).isEqualTo("Technical issue");
        });
        step("Проверить, что тип productConstraints = FREE", () -> {
            assertThat(productConstraints).isEqualTo("FREE");
        });
    }
}
