CREATE TABLE place (
  id          BIGINT PRIMARY KEY,
  NAME        VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  location    VARCHAR(255) NOT NULL
);

INSERT INTO place (id, name, description, location) VALUES
  (1, 'Eiffel Tower', 'Champ de Mars, 5 Avenue Anatole France, 75007 Paris, France', 'POINT(2.2944313287734985 48.85826523681466)'),
  (2, 'Statue of Liberty', 'Isla de la Libertad, New York, NY 10004, USA', 'POINT(-74.04455244541168 40.68925173681689)');
