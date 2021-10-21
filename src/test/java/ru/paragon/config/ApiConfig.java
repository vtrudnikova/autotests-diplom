package ru.paragon.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties",
        "classpath:config/api.properties",
        "classpath:config/remote.properties"
})
public interface ApiConfig extends Config {
    @Config.Key("url")
    String url();

    @Config.Key("clientId")
    String clientId();

    @Config.Key("pwdHash")
    String pwdHash();

    @Config.Key("urlPrefix")
    String urlPrefix();

    @Config.Key("email")
    String email();

}


