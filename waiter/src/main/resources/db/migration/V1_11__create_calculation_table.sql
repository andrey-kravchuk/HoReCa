CREATE TABLE calculation (
  id                BIGINT        PRIMARY KEY          AUTO_INCREMENT,
  number            BIGINT        NOT NULL,
  date              DATE,
  cabare_id         BIGINT        NOT NULL,
  dish_id           BIGINT        NOT NULL,
  ingredient_id     BIGINT        NOT NULL,
  quantity          DOUBLE,
  is_archived       BIT           DEFAULT b'0'         NOT NULL,
  CONSTRAINT fk_calculation_cabare
  FOREIGN KEY (cabare_id) REFERENCES cabare (id),
  CONSTRAINT fk_calculation_dish
  FOREIGN KEY (dish_id) REFERENCES dish (id),
  CONSTRAINT fk_calculation_ingredient
  FOREIGN KEY (ingredient_id) REFERENCES ingredient (id)
);