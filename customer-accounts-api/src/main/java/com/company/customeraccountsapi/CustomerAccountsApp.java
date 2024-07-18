package com.company.customeraccountsapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients()
public class CustomerAccountsApp {
    public static void main(String[] args) {

        SpringApplication.run(CustomerAccountsApp.class, args);
    }
}