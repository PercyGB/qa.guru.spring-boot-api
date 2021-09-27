package guru.qa.restbackend.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/api/api.properties"
})
public interface ApiConfig extends Config {
    @Key("baseUrl")
    String baseUrl();
}