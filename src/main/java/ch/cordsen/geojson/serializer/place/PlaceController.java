package ch.cordsen.geojson.serializer.place;

import ch.cordsen.geojson.serializer.place.domain.Place;
import ch.cordsen.geojson.serializer.place.domain.Places;
import ch.cordsen.geojson.annotation.GeoJson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

/**
 * A REST controller that provides HTTP access to the {@link PlaceRepository}.
 * <p>
 * To create a response body with GeoJSON, a {@link GeoJson @GeoJson} annotated class
 * is sufficient.
 * <p>
 * This example uses two annotated classes, {@link Place} as <em>Feature</em> and
 * {@link Places} as the <em>FeatureCollection</em>.
 **/
@RestController
public class PlaceController {

   private final PlaceRepository placeRepository;

   public PlaceController(PlaceRepository placeRepository) {
      this.placeRepository = placeRepository;
   }

   /**
    * Returns the available {@link Places}. The class is annotated with {@code @GeoJson(type = GeoJsonType.FEATURE_COLLECTION)}.
    *
    * @return the {@link ResponseEntity} with places
    */
   @GetMapping("/api/places")
   public ResponseEntity<Places> findAll() {
      List<Place> list = placeRepository.findAll();
      Places places = new Places(list);
      return ok(places);
   }

   /**
    * Returns one {@link Place} by ID. The class is annotated with {@code @GeoJson(type = GeoJsonType.FEATURE)}.
    *
    * @param id the ID
    * @return the {@link ResponseEntity} with place, or HTTP status 404 if not found
    */
   @GetMapping("/api/places/{id}")
   public ResponseEntity<Place> findById(@PathVariable("id") Long id) {
      return placeRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(notFound().build());
   }
}
