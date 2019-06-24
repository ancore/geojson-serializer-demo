package gmbh.dtap.geojson.serializer.place;

import gmbh.dtap.geojson.serializer.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The JPA repository for entity {@link Place}.
 *
 * @since 0.1.0
 */
@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
