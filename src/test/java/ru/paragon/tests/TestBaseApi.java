package ru.paragon.tests;

import ru.paragon.rest.clients.ApiClient;
import ru.paragon.rest.clients.model.CustomerSessionApiResponse;

public class TestBaseApi {
    ApiClient apiClient = new ApiClient();

    public String getSessionId() {
        CustomerSessionApiResponse responseGetSessionId = apiClient.getSessionCustomer();
        String sessionId = responseGetSessionId.getSessionId();
        return sessionId;
    }
}
