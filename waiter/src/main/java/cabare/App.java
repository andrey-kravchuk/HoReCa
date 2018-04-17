package cabare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
public class App {

  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(App.class, args);
  }

  @Bean
  public MethodValidationPostProcessor get() {
    return new MethodValidationPostProcessor();
  }
}
