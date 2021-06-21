package ch.cordsen.geojson.serializer.place;

import ch.cordsen.geojson.serializer.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The JPA repository for entity {@link Place}.
 */
@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
