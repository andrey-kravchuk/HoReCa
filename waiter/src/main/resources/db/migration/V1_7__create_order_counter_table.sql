CREATE TABLE order_counter
(
  id        BIGINT PRIMARY KEY AUTO_INCREMENT,
  counter   BIGINT NOT NULL,
  cabare_id BIGINT NOT NULL,
  CONSTRAINT fk_counter_cabare
  FOREIGN KEY (cabare_id) REFERENCES cabare (id)
)