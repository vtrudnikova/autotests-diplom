package ru.paragon.rest.clients.model;

public class CustomerSessionApiResponse {
    private String sessionId;
    private String canChangePassword;
    private String firstTimeSocialLogin;
    private String hasProducts;
    private String agreeWithPolicy;

    public String getCanChangePassword() {
        return canChangePassword;
    }

    public void setCanChangePassword(String canChangePassword) {
        this.canChangePassword = canChangePassword;
    }

    public String getFirstTimeSocialLogin() {
        return firstTimeSocialLogin;
    }

    public void setFirstTimeSocialLogin(String firstTimeSocialLogin) {
        this.firstTimeSocialLogin = firstTimeSocialLogin;
    }

    public String getHasProducts() {
        return hasProducts;
    }

    public void setHasProducts(String hasProducts) {
        this.hasProducts = hasProducts;
    }

    public String getAgreeWithPolicy() {
        return agreeWithPolicy;
    }

    public void setAgreeWithPolicy(String agreeWithPolicy) {
        this.agreeWithPolicy = agreeWithPolicy;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
