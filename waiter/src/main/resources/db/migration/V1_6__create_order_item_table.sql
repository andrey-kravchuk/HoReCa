CREATE TABLE order_item (
  id           BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_number BIGINT       NOT NULL,
  dish_id      BIGINT       NOT NULL,
  quantity     INT          NOT NULL,
  total_price  BIGINT       NOT NULL,
  comments     VARCHAR(255) NULL,
  bill_id      BIGINT       NOT NULL,
  CONSTRAINT fk_orderitem_bill
  FOREIGN KEY (bill_id) REFERENCES bill (id),
  CONSTRAINT fk_orderitem_dish
  FOREIGN KEY (dish_id) REFERENCES dish (id)
)