package com.loopme.testtask;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/myapp/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
     * application.
     * 
     * @return Grizzly HTTP server.
     */
    public static HttpServer createServer() {

        WebappContext webappContext = new WebappContext("loopme-testtask-webappcontext");

        webappContext.addContextInitParameter("contextClass", "org.springframework.web.context.support.XmlWebApplicationContext");
        webappContext.addContextInitParameter("contextConfigLocation", "classpath*:spring-context.xml");
        webappContext.addListener("org.springframework.web.context.ContextLoaderListener");

        // Create a servlet registration for the web application in order to
        // wire up Spring managed collaborators to Jersey resources.
        ServletRegistration servletRegistration = webappContext.addServlet("jersey-servlet", ServletContainer.class);

        // The logging filters for server logging.
        servletRegistration.setInitParameter("com.sun.jersey.spi.container.ContainerResponseFilters", "com.sun.jersey.api.container.filter.LoggingFilter");
        servletRegistration.setInitParameter("com.sun.jersey.spi.container.ContainerRequestFilters", "com.sun.jersey.api.container.filter.LoggingFilter");

        servletRegistration.setInitParameter("javax.ws.rs.Application", "com.loopme.testtask.MainApplication");

        servletRegistration.setInitParameter("com.sun.jersey.config.property.packages", "com.loopme.testtask");
        servletRegistration.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
        servletRegistration.addMapping("/*");

        HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), false);
        webappContext.deploy(httpServer);
        return httpServer;
    }

    /**
     * Main method.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = createServer();
        try {
            server.start();
            System.out.println(String.format("Jersey app started\nHit enter to stop it...", BASE_URI));
            System.in.read();
        } finally {
            server.shutdownNow();
        }
    }
}
