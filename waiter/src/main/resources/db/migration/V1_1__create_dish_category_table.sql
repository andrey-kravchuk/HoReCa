CREATE TABLE zone (
  id    BIGINT PRIMARY KEY AUTO_INCREMENT,
  name  VARCHAR(255) NOT NULL,
  UNIQUE (name)
);

CREATE TABLE dish_category (
  id    BIGINT PRIMARY KEY AUTO_INCREMENT,
  name  VARCHAR(255) NOT NULL,
  photo VARCHAR(255),
  zone_id BIGINT            NOT NULL,
  UNIQUE (name),
  CONSTRAINT fk_dishcategory_zone
  FOREIGN KEY (zone_id) REFERENCES zone (id)
);