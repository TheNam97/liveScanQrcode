package com.crud_restfulapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.crud_restfulapi.*" })
public class CrudRestfulApiApplication {

    public static void main(String[] args) {
        //Integer i =10;
        SpringApplication.run(CrudRestfulApiApplication.class, args);
    }

}
