package com.example.demoemailtemplate;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import freemarker.cache.StringTemplateLoader;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class DynamicEmailTemplate {

    public static void main(String[] args) {
        String toEmail = "recipient@example.com";
        String subject = "Dynamic Email Template";

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("name", "John Doe");
        templateData.put("message", "This is a dynamically generated email template.");

        try {
            String emailContent = generateEmailContent(templateData);

            // In a real-world scenario, you would use JavaMail to send the email with the generated content
            System.out.println("Email content:\n" + emailContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateEmailContent(Map<String, Object> templateData) throws IOException {
        try {
            // Configure FreeMarker
            Configuration configuration = new Configuration(new Version("2.3.31"));
            configuration.setTemplateLoader(new StringTemplateLoader());
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            // Create a StringWriter to store the template content
            StringWriter stringWriter = new StringWriter();

            // Build the dynamic template content
            stringWriter.write("<html><body>");
            stringWriter.write("<h1>Hello ${name},</h1>");
            stringWriter.write("<p>${message}</p>");
            stringWriter.write("</body></html>");

            // Load the dynamic template content into FreeMarker using StringTemplateLoader
            StringTemplateLoader stringTemplateLoader = (StringTemplateLoader) configuration.getTemplateLoader();
            stringTemplateLoader.putTemplate("tutv_template", stringWriter.toString());

            // Get the template
            Template template = configuration.getTemplate("tutv_template");

            // Process FreeMarker template
            return processTemplate(template, templateData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String processTemplate(Template template, Map<String, Object> data) throws freemarker.template.TemplateException, IOException {
        StringWriter writer = new StringWriter();
        template.process(data, writer);
        return writer.toString();
    }
}
