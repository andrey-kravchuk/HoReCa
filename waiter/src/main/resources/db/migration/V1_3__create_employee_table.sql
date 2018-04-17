CREATE TABLE employee (
  id        BIGINT PRIMARY KEY AUTO_INCREMENT,
  name      VARCHAR(255)           NOT NULL,
  enabled   BIT DEFAULT b'1'       NOT NULL,
  email     VARCHAR(255),
  password  VARCHAR(255),
  cabare_id BIGINT                 NOT NULL,
  CONSTRAINT fk_employee_cabare
  FOREIGN KEY (cabare_id) REFERENCES cabare (id)
)