CREATE TABLE dish_category (
  id    BIGINT PRIMARY KEY AUTO_INCREMENT,
  name  VARCHAR(255) NOT NULL,
  photo VARCHAR(255),
  UNIQUE (name)
)