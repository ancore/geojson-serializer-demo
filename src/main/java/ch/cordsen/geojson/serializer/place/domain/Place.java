package ch.cordsen.geojson.serializer.place.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ch.cordsen.geojson.annotation.GeoJson;
import ch.cordsen.geojson.annotation.GeoJsonGeometry;
import ch.cordsen.geojson.annotation.GeoJsonId;
import ch.cordsen.geojson.annotation.GeoJsonProperty;
import ch.cordsen.geojson.serializer.GeoJsonSerializer;
import ch.cordsen.geojson.serializer.GeoJsonType;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents the JPA entity for one place.
 * <p>
 * The location field was chosen to contain WKT only to keep the in-memory database simple and without GIS extension.
 * In a normal application, the {@link Point} data type may be used directly, e.g. with hibernate-spatial.
 * <p>
 * Complementary to the {@link GeoJson} annotation, the <em>id</em>, some <em>properties</em>
 * and the <em>geometry</em> are annotated.
 * <p>
 * For a separation of JPA entity and a DTO, all GeoJson annotations would go to the DTO.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Well-known_text_representation_of_geometry" target="_blank">Well-known text representation of geometry</a>
 * @since 0.1.0
 */
@Entity
@GeoJson(type = GeoJsonType.FEATURE)
@JsonSerialize(using = GeoJsonSerializer.class)
public class Place {

   private static final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
   private static final WKTReader wktReader = new WKTReader(geometryFactory);

   @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @GeoJsonId private Long id;
   @GeoJsonProperty private String name;
   @GeoJsonProperty private String description;
   private String location;

   public Place() {
      // JPA
   }

   public Place(Long id, String name, String description, String location) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.location = location;
   }

   /**
    * Parses the WKT content and return the Geometry object.
    * <p>
    * The method is a <strong>getter</strong> to work with the {@link GeoJsonGeometry} annotation.
    *
    * @return a Geometry representation of the WKT content
    * @throws ParseException in case of malformed WKT
    * @since 0.1.0
    */
   @GeoJsonGeometry
   public Geometry getGeometry() throws ParseException {
      return wktReader.read(location);
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getLocation() {
      return location;
   }

   public void setLocation(String location) {
      this.location = location;
   }

   @Override public String toString() {
      return "Place{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", location=" + location +
            '}';
   }
}
