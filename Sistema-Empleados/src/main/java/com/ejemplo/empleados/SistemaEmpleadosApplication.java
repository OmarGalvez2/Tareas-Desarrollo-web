package com.ejemplo.empleados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SistemaEmpleadosApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SistemaEmpleadosApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SistemaEmpleadosApplication.class);
    }
}
