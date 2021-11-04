package ru.paragon.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/api.properties",
})
public interface ApiConfig extends Config {
    @Config.Key("url")
    String url();

    @Config.Key("urlPrefix")
    String urlPrefix();

}


