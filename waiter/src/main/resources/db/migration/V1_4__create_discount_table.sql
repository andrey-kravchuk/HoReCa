CREATE TABLE discount (
  id            BIGINT PRIMARY KEY AUTO_INCREMENT,
  card_number   VARCHAR(255) UNIQUE   NOT NULL,
  discount_size INT                   NOT NULL,
  total_paid    BIGINT                NULL,
  activated     BIT DEFAULT b'1'      NOT NULL,
  cabare_id     BIGINT                NOT NULL,
  CONSTRAINT fk_discount_cabare
  FOREIGN KEY (cabare_id) REFERENCES cabare (id)
)