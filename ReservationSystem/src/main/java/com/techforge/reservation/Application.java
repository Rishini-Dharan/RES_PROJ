package com.techforge.reservation;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Application {

    public static void main(String[] args) throws IOException, LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        File file = Files.createTempDirectory("server").toFile();
        tomcat.setBaseDir(file.getAbsolutePath());
        Context context = tomcat.addContext("/", file.getAbsolutePath());

        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.scan("com.techforge.reservation");
        webApplicationContext.register(ConfigurationPropertiesAutoConfiguration.class);
        webApplicationContext.setServletContext(context.getServletContext());
        webApplicationContext.refresh();

        DispatcherServlet servlet = new DispatcherServlet(webApplicationContext);
        Tomcat.addServlet(context, "dispatcherServlet", servlet).setLoadOnStartup(1);
        context.addServletMappingDecoded("/*", "dispatcherServlet");

        tomcat.getConnector();
        tomcat.start();
    }

}
