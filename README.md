[![Build Status](https://github.com/dtap-gmbh/geojson-serializer-demo/actions/workflows/maven.yml/badge.svg)](https://github.com/dtap-gmbh/geojson-serializer-demo) 

# geojson-serializer-demo

An example Spring Boot project that demonstrates the usage of [geojson-serializer](https://github.com/dtap-gmbh/geojson-serializer) to create [GeoJSON](https://tools.ietf.org/html/rfc7946) with a RestController.

## REST Calls

http://localhost:8080/api/places

```json
{
	"type": "FeatureCollection",
	"features": [{
		"type": "Feature",
		"id": 1,
		"geometry": {
			"type": "Point",
			"coordinates": [2.2944313287734985, 48.85826523681466]
		},
		"properties": {
			"name": "Eiffel Tower",
			"description": "Champ de Mars, 5 Avenue Anatole France, 75007 Paris, France"
		}
	}, {
		"type": "Feature",
		"id": 2,
		"geometry": {
			"type": "Point",
			"coordinates": [-74.04455244541168, 40.68925173681689]
		},
		"properties": {
			"name": "Statue of Liberty",
			"description": "Isla de la Libertad, New York, NY 10004, USA"
		}
	}]
}
```

http://localhost:8080/api/places/1

```json
{
    "type": "Feature",
    "id": 1,
    "geometry": {
        "type": "Point",
        "coordinates": [2.2944313287734985, 48.85826523681466]
    },
    "properties": {
        "name": "Eiffel Tower",
        "description": "Champ de Mars, 5 Avenue Anatole France, 75007 Paris, France"
    }
}
```

http://localhost:8080/api/places/2

```json
{
	"type": "Feature",
	"id": 2,
	"geometry": {
		"type": "Point",
		"coordinates": [-74.04455244541168, 40.68925173681689]
	},
	"properties": {
		"name": "Statue of Liberty",
		"description": "Isla de la Libertad, New York, NY 10004, USA"
	}
}
```

## Credits

Copyright (c) 2019 DTAP GmbH

Please refer to the LICENSE file for details.
