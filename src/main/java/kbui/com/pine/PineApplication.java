package kbui.com.pine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PineApplication {

  public static void main(String[] args) {
    SpringApplication.run(PineApplication.class, args);
  }
}
