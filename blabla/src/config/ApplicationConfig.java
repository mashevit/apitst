package config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationConfig extends ResourceConfig{
    public ApplicationConfig() {
    	register(new ApplicationBinder());
    	register(JacksonFeature.class);
        packages(true, "api");
    }
}
