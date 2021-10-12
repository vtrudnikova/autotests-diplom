package ru.paragon.tests.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.paragon.rest.clients.ApiClient;
import ru.paragon.rest.clients.model.CustomerProductApiResponse;
import ru.paragon.rest.clients.model.CustomerSessionApiResponse;
import ru.paragon.rest.clients.model.KindAndProductConstraintsApiResponse;
import ru.paragon.rest.clients.model.SerialNumberApiResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParagonApiTests {
    String email = "pegafragaka-2961@yopmail.com";
    Long serialNumber = System.currentTimeMillis();
    ApiClient apiClient = new ApiClient();

    @Test
    @DisplayName("Нельзя зарегистрировать невалидный серийный номер")
    void cannotRegisterWrongSerialNumber() {
        CustomerSessionApiResponse responseGetSessionId = apiClient.getSessionCustomer(email);
        String sessionId = responseGetSessionId.getSessionId();
        SerialNumberApiResponse response = apiClient.registerWrongSerialNumber(sessionId, serialNumber);
        String message = response.getError();
        assertThat(message).isEqualTo("SERIAL_CODE_NOT_FOUND");
    }

    @Test
    @DisplayName("У нового пользователя нет зарегистрированных продуктов")
    void newUserHasNoRegisteredProducts() {
        CustomerSessionApiResponse responseGetSessionId = apiClient.getSessionCustomer(email);
        String sessionId = responseGetSessionId.getSessionId();
        CustomerProductApiResponse response = apiClient.getListProducts(sessionId);
        List<Object> products = response.getActivatedCustomerSerialCodes();
        assertThat(products).isEmpty();
    }

    @Test
    @DisplayName("Проверить, что есть kind с типом Technical issue и productConstraints с типом Free для new request")
    void checkKindWithTechnicalIssueTypeAndProductConstraintsWithFreeType() {
        CustomerSessionApiResponse responseGetSessionId = apiClient.getSessionCustomer(email);
        String sessionId = responseGetSessionId.getSessionId();
        KindAndProductConstraintsApiResponse response = apiClient.getKindAndProductConstraints(sessionId);
        String kind = response.getKinds().get(0);
        String productConstraints = response.getProductConstraints().get(1);
        assertThat(kind).isEqualTo("Technical issue");
        assertThat(productConstraints).isEqualTo("FREE");
    }
}

