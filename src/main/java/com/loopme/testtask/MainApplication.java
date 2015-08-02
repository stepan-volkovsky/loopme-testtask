package com.loopme.testtask;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class MainApplication extends ResourceConfig {
    public MainApplication() {
        register(RequestContextFilter.class);
        register(JacksonFeature.class);
        register(CreativesResource.class);
    }
}
