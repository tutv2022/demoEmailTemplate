package com.example.demoemailtemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.Properties;

@Configuration
public class EmailConfiguration {

    @Bean
    public ITemplateResolver dynamicTemplateResolver() {
        StringTemplateResolver resolver = new StringTemplateResolver();
        resolver.setCacheable(false); // Disable caching for dynamic templates
        return resolver;
    }


//    @Bean
//    public TemplateEngine templateEngine() {
//        TemplateEngine templateEngine = new TemplateEngine();
//        templateEngine.setTemplateResolver(dynamicTemplateResolver());
//        return templateEngine;
//    }
}
