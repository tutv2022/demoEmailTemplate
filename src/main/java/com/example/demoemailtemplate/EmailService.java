package com.example.demoemailtemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
class EmailService {

    private final TemplateEngine templateEngine;

    @Autowired
    public EmailService( TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public void sendEmail(String to, String subject) {
        try {
            // Create a Thymeleaf context and add variables to be used in the template
            Context context = new Context();
            context.setVariable("name", "John Doe");

            // Process Thymeleaf template
            String emailContent = templateEngine.process(createEmailTemplate(), context);

            // Create a MimeMessage


            System.out.println("Email sent successfully! : " + emailContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String createEmailTemplate() {
        // Create the Thymeleaf template content programmatically
        return "<html><body><h1>Hello, [[${name}]]!</h1></body></html>";
    }
}
