package org.softkiss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@SpringBootApplication
public class Application extends RepositoryRestConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public PersonValidator beforeCreateWebsiteUserValidator() {
//        return new PersonValidator();
//    }

//    @Override
//    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener v) {
//        v.addValidator("beforeCreate", new PersonValidator());
//    }

}
