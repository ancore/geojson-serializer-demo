package gmbh.dtap.geojson.serializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Spring Boot Application
 *
 * @since 0.1.0
 */
@EnableJpaRepositories
@SpringBootApplication
public class GeojsonSerializerDemoApplication {

   public static void main(String[] args) {
      SpringApplication.run(GeojsonSerializerDemoApplication.class, args);
   }
}
