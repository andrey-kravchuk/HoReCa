CREATE TABLE cabare (
  id   BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  UNIQUE (name)
);

CREATE TABLE zone (
  id        BIGINT PRIMARY KEY AUTO_INCREMENT,
  name      VARCHAR(255) NOT NULL,
  cabare_id BIGINT       NOT NULL,
  CONSTRAINT fk_zone_cabare
  FOREIGN KEY (cabare_id) REFERENCES cabare (id),
  UNIQUE (name)
);

CREATE TABLE dish_category (
  id        BIGINT PRIMARY KEY AUTO_INCREMENT,
  name      VARCHAR(255) NOT NULL,
  photo     VARCHAR(255),
  zone_id   BIGINT       NOT NULL,
  cabare_id BIGINT       NOT NULL,
  CONSTRAINT fk_dishcategory_cabare
  FOREIGN KEY (cabare_id) REFERENCES cabare (id),
  UNIQUE (name),
  CONSTRAINT fk_dishcategory_zone
  FOREIGN KEY (zone_id) REFERENCES zone (id)
);

CREATE TABLE measure (
  id            BIGINT PRIMARY KEY AUTO_INCREMENT,
  name          VARCHAR (255) NOT NULL,
  abbreviation  VARCHAR (255) NOT NULL,
  UNIQUE (name)
);

CREATE TABLE ingredient (
  id          BIGINT PRIMARY KEY AUTO_INCREMENT,
  name        VARCHAR(255) NOT NULL,
  measure_id  BIGINT       NOT NULL,
  cabare_id   BIGINT       NOT NULL,
  CONSTRAINT  fk_ingredient_measure
  FOREIGN KEY (measure_id) REFERENCES measure (id),
  CONSTRAINT  fk_ingredient_cabare
  FOREIGN KEY (cabare_id) REFERENCES cabare (id),
  UNIQUE (name)
);

