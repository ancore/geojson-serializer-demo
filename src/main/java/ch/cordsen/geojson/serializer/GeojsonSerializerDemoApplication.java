package ch.cordsen.geojson.serializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Spring Boot Application demonstrating the <em>geojson-serializer</em> library.
 *
 * @see <a href="https://github.com/ancore/geojson-serializer">geojson-serializer</a>
 */
@EnableJpaRepositories
@SpringBootApplication
public class GeojsonSerializerDemoApplication {

   public static void main(String[] args) {
      SpringApplication.run(GeojsonSerializerDemoApplication.class, args);
   }
}
