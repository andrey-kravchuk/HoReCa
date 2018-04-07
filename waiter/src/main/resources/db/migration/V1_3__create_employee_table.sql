CREATE TABLE employee (
  id       BIGINT PRIMARY KEY AUTO_INCREMENT,
  name     VARCHAR(255)           NOT NULL,
  enabled  BIT DEFAULT b'1'       NOT NULL,
  email    VARCHAR(255),
  password VARCHAR(255)
)