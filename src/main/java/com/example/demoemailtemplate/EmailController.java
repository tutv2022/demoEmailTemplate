package com.example.demoemailtemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @GetMapping("/email")
    public String testEmail() {

        emailService.sendEmail("tutv1988@gmail.com","Hello there");
        return "Test";

    }
}
