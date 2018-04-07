INSERT INTO role (id, role) VALUES (1, "WAITER_ROLE");
INSERT INTO role (id, role) VALUES (2, "ADMIN_ROLE");


INSERT INTO user_role (id, user_id, role_id) VALUES (1, 1, 1);
INSERT INTO user_role (id, user_id, role_id) VALUES (2, 2, 1);
INSERT INTO user_role (id, user_id, role_id) VALUES (3, 3, 1);