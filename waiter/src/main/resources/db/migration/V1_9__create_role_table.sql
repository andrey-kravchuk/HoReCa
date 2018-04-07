CREATE TABLE role (
  id   BIGINT PRIMARY KEY AUTO_INCREMENT,
  role VARCHAR(255)
);

CREATE TABLE user_role (
  id      BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  CONSTRAINT fk_to_user
  FOREIGN KEY (user_id) REFERENCES employee (id),
  CONSTRAINT fk_to_role
  FOREIGN KEY (role_id) REFERENCES role (id)
)