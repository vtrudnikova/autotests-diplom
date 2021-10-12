package ru.paragon.rest.clients.model;

import java.util.List;

public class CustomerProductApiResponse {
    private List<Object> activatedCustomerSerialCodes;

    public List<Object> getActivatedCustomerSerialCodes() {
        return activatedCustomerSerialCodes;
    }

    public void setActivatedCustomerSerialCodes(List<Object> activatedCustomerSerialCodes) {
        this.activatedCustomerSerialCodes = activatedCustomerSerialCodes;
    }
}
