CREATE TABLE dish
(
  id               BIGINT PRIMARY KEY AUTO_INCREMENT,
  name             VARCHAR(255)      NOT NULL,
  photo            VARCHAR(255),
  dish_out         INT               NULL,
  start_day        INT DEFAULT '0'   NOT NULL,
  end_day          INT DEFAULT '366' NOT NULL,
  price            BIGINT            NULL,
  is_archived      BIT DEFAULT b'0'  NOT NULL,
  dish_category_id BIGINT            NOT NULL,
  quantity         INT DEFAULT '-1'  NOT NULL,
  cabare_id        BIGINT            NOT NULL,
  CONSTRAINT fk_dish_cabare
  FOREIGN KEY (cabare_id) REFERENCES cabare (id),
  CONSTRAINT fk_dish_dishcategory
  FOREIGN KEY (dish_category_id) REFERENCES dish_category (id)
)