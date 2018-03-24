CREATE TABLE discount (
  id            BIGINT PRIMARY KEY AUTO_INCREMENT,
  card_number   VARCHAR(255) UNIQUE   NOT NULL,
  discount_size INT                   NOT NULL,
  total_paid    BIGINT                NULL,
  activated     BIT DEFAULT b'1'      NOT NULL
)