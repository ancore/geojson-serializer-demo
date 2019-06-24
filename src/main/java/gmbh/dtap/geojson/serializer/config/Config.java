package gmbh.dtap.geojson.serializer.config;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Registers the jts module that is necessary to serialize {@link Geometry} objects.
 *
 * @since 0.1.0
 */
@Configuration
public class Config {

   @Bean
   public ObjectMapper objectMapper() {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.registerModule(jtsModule());
      return objectMapper;
   }

   @Bean
   public JtsModule jtsModule() {
      return new JtsModule(geometryFactory());
   }

   @Bean
   public GeometryFactory geometryFactory() {
      return new GeometryFactory(precisionModel(), 4326);
   }

   @Bean
   public PrecisionModel precisionModel() {
      return new PrecisionModel();
   }
}
