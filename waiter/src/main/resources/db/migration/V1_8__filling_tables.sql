# dish categories
INSERT INTO dish_category (id, name, photo) VALUES ('1', 'Закуски', 'snacks.jpg');
INSERT INTO dish_category (id, name, photo) VALUES ('2', 'Основные блюда', 'main.jpg');
INSERT INTO dish_category (id, name, photo) VALUES ('3', 'Салаты', 'salads.jpg');
INSERT INTO dish_category (id, name, photo) VALUES ('4', 'Напитки', 'beverages.jpg');
INSERT INTO dish_category (id, name, photo) VALUES ('5', 'Десерты', 'deserts.jpg');
# dishes
INSERT INTO dish (id, name, photo, dish_out, price, dish_category_id)
VALUES ('1', 'Салат Весна', 'spring-salad.jpg', '5', '5000', '3');

INSERT INTO dish (id, name, photo, dish_out, price, dish_category_id)
VALUES ('2', 'Салат Греческий', 'greek-salad.jpg', '4', '6000', '3');

INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('3', 'Салат Столичный', '3', '6000', '3');

INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('4', 'Салат Оливье', '5', '5000', '3');

INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('5', 'Салат Винигрет', '2', '3800', '3');

INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('6', 'Сельдь под шубой', '1', '4800', '2');

INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('7', 'Курица запечёная целиком', '6', '32800', '2');

INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('8', 'Голубцы', '3', '8400', '2');

INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('9', 'Картофель отварной', '6', '1800', '2');

INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('10', 'Солянка мясная', '5', '6400', '2');

INSERT INTO dish (id, name, photo, dish_out, price, dish_category_id)
VALUES ('11', 'Мятное латте', 'latte-mint.jpg', '4', '6500', '4');

INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('12', 'Печенье "Хрустящие черепашки"', '3', '4300', '1');

INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('13', 'Чипсы из цукини ', '5', '3000', '1');

INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('14', 'Чизкейк', '5', '7500', '5');

INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('15', 'Тирамису', '5', '6500', '5');

INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('16', 'Сент-Эв', '5', '7000', '5');

INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('17', 'Шанте', '5', '8000', '5');

INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('18', 'Чай зеленый', '300', '5000', '4');
INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('19', 'Чай черный', '300', '7000', '4');
INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('20', 'Кофе', '200', '6000', '4');
INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('21', 'Мохито', '500', '7000', '4');
INSERT INTO dish (id, name, dish_out, price, dish_category_id)
VALUES ('22', 'Вода', '500', '3000', '4');

# employee
INSERT INTO employee (id, name, enabled) VALUES (1, 'Вася Пупкин', b'01');
INSERT INTO employee (id, name, enabled) VALUES (2, 'Петя Васин', b'01');
INSERT INTO employee (id, name, enabled) VALUES (3, 'Тетя Сима', b'01');

# discount
INSERT INTO discount (id, card_number, `discount_size`) VALUES ('1', 'kv - 123', '40');
INSERT INTO discount (id, card_number, `discount_size`) VALUES ('2', 'kv-1234', '30');
INSERT INTO discount (id, card_number, `discount_size`) VALUES ('3', 'kv-12345', '20');
INSERT INTO discount (id, card_number, `discount_size`) VALUES ('4', 'kv-123456', '10');