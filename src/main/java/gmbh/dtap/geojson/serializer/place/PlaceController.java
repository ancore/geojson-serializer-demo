/*
 * Copyright 2019 DTAP GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gmbh.dtap.geojson.serializer.place;

import gmbh.dtap.geojson.annotation.GeoJson;
import gmbh.dtap.geojson.serializer.place.domain.Place;
import gmbh.dtap.geojson.serializer.place.domain.Places;
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
