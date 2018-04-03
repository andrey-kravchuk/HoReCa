CREATE TABLE bill (
  id                BIGINT PRIMARY KEY AUTO_INCREMENT,
  served_table      BIGINT,
  open_bill_time    DATETIME         NOT NULL,
  close_bill_time   DATETIME         NULL,
  employee_id       BIGINT           NOT NULL,
  table_number      INT              NULL,
  number_of_persons INT              NULL,
  discount_id       BIGINT           NULL,
  discounted_sum    BIGINT           NULL,
  sale_type_id      INT              NOT NULL,
  pay_type_id       INT              NULL,
  pay_status_id     INT              NOT NULL,
  money_paid        BIGINT           NULL,
  is_opened         BIT DEFAULT b'1' NOT NULL,
  is_active_shift   BIT DEFAULT b'1' NOT NULL,
  CONSTRAINT fk_bill_discount
  FOREIGN KEY (discount_id) REFERENCES discount (id),
  CONSTRAINT fk_bill_employee
  FOREIGN KEY (employee_id) REFERENCES employee (id)
)