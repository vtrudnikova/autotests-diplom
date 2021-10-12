package ru.paragon.rest.clients.model;

import java.util.List;

public class KindAndProductConstraintsApiResponse {
    private List<String> kinds;
    private List<String> productConstraints;

    public List<String> getKinds() {
        return kinds;
    }

    public void setKinds(List<String> kinds) {
        this.kinds = kinds;
    }

    public List<String> getProductConstraints() {
        return productConstraints;
    }

    public void setProductConstraints(List<String> productConstraints) {
        this.productConstraints = productConstraints;
    }
}
