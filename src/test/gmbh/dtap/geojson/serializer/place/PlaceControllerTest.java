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

import gmbh.dtap.geojson.serializer.place.domain.Place;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for {@link PlaceController}.
 */
@ExtendWith(MockitoExtension.class)
class PlaceControllerTest {

   @InjectMocks private PlaceController placeController;
   @Mock private PlaceRepository placeRepository;
   private MockMvc mockMvc;

   @BeforeEach
   void beforeEach() {
      mockMvc = MockMvcBuilders
            .standaloneSetup(placeController)
            .build();
   }

   @Test
   void shouldReturnEmptyFeatureCollection_whenRepositoryReturnsNoPlaces() throws Exception {
      when(placeRepository.findAll()).thenReturn(emptyList());

      String expectedJson = IOUtils.toString(getClass().getResource("/empty-feature-collection.json").toURI(), UTF_8);

      mockMvc.perform(MockMvcRequestBuilders
            .get("/api/places")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(expectedJson, true));
   }

   @Test
   void shouldReturnFeatureCollectionWithPlaces_whenRepositoryReturnsPlaces() throws Exception {
      when(placeRepository.findAll()).thenReturn(asList(
            new Place(1L, "Eiffel Tower", "Champ de Mars, 5 Avenue Anatole France, 75007 Paris, France", "POINT(2.2944313287734985 48.85826523681466)"),
            new Place(2L, "Statue of Liberty", "Isla de la Libertad, New York, NY 10004, USA", "POINT(-74.04455244541168 40.68925173681689)")
      ));

      String expectedJson = IOUtils.toString(getClass().getResource("/feature-collection.json").toURI(), UTF_8);

      mockMvc.perform(MockMvcRequestBuilders
            .get("/api/places")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(expectedJson, true));
   }

   @Test
   void shouldReturn404_whenRepositoryReturnsNoPlaceById() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders
            .get("/api/place/23")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound());
   }

   @Test
   void shouldReturnFeatureWithPlace_whenRepositoryReturnsPlaceById() throws Exception {
      when(placeRepository.findById(2L)).thenReturn(
            Optional.of(new Place(2L, "Statue of Liberty", "Isla de la Libertad, New York, NY 10004, USA", "POINT(-74.04455244541168 40.68925173681689)"))
      );

      String expectedJson = IOUtils.toString(getClass().getResource("/feature.json").toURI(), UTF_8);

      mockMvc.perform(MockMvcRequestBuilders
            .get("/api/places/2")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(expectedJson, true));
   }
}
