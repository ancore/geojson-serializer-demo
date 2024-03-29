package ch.cordsen.geojson.serializer.place.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ch.cordsen.geojson.annotation.GeoJson;
import ch.cordsen.geojson.annotation.GeoJsonFeatures;
import ch.cordsen.geojson.serializer.GeoJsonSerializer;
import ch.cordsen.geojson.serializer.GeoJsonType;

import java.util.List;

/**
 * Represents the JPA entity for multiple place.
 * <p>
 * Complementary to the {@link GeoJson} annotation, the annotation for <em>features</em> is present.
 *
 * @since 0.1.0
 */

@GeoJson(type = GeoJsonType.FEATURE_COLLECTION)
@JsonSerialize(using = GeoJsonSerializer.class)
public class Places {

   @GeoJsonFeatures
   private List<Place> places;

   public Places(List<Place> places) {
      this.places = places;
   }

   public List<Place> getPlaces() {
      return places;
   }

   public void setPlaces(List<Place> places) {
      this.places = places;
   }

   @Override public String toString() {
      return "Places{" +
            "places=" + places +
            '}';
   }
}
