package com.example.demoemailtemplate;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EmailSender {

    public static void main(String[] args) {
        String toEmail = "recipient@example.com";
        String subject = "Sample Email";
        String templatePath = "email_template.ftl";

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("name", "John Doe");

        sendEmail(toEmail, subject, templatePath, templateData);
    }

    public static void sendEmail(String toEmail, String subject, String templatePath, Map<String, Object> templateData) {
        try {
            // Configure FreeMarker
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
            configuration.setClassForTemplateLoading(EmailSender.class, "/");

            // Load FreeMarker template
            Template template = configuration.getTemplate(templatePath);

            // Process FreeMarker template
            String emailContent = processTemplate(template, templateData);

            // Set up JavaMail properties
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "your.smtp.host"); // Set your SMTP server
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            // Set up JavaMail session
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("your.email@example.com", "your_email_password");
                }
            });

            // Create and configure the message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("your.email@example.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setContent(emailContent, "text/html");

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String processTemplate(Template template, Map<String, Object> data) throws IOException, TemplateException {
        StringWriter writer = new StringWriter();
        template.process(data, writer);
        return writer.toString();
    }
}