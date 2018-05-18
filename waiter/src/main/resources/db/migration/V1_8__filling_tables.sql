# cabare
INSERT INTO cabare (id, name) VALUES ('1', 'Ресторан-1');
INSERT INTO cabare (id, name) VALUES ('2', 'Ресторан-2');
INSERT INTO cabare (id, name) VALUES ('3', 'Ресторан-3');

# zones
INSERT INTO zone (id, name, cabare_id) VALUES ('1', 'Кухня', 1);
INSERT INTO zone (id, name, cabare_id) VALUES ('2', 'Бар', 1);

# dish categories
INSERT INTO dish_category (id, name, photo, zone_id, cabare_id)
VALUES ('1', 'Закуски', 'snacks.jpg', '1', 1);
INSERT INTO dish_category (id, name, photo, zone_id, cabare_id)
VALUES ('2', 'Основные блюда', 'main.jpg', '1', 1);
INSERT INTO dish_category (id, name, photo, zone_id, cabare_id)
VALUES ('3', 'Салаты', 'salads.jpg', '1', 1);
INSERT INTO dish_category (id, name, photo, zone_id, cabare_id)
VALUES ('4', 'Напитки', 'beverages.jpg', '2', 1);
INSERT INTO dish_category (id, name, photo, zone_id, cabare_id)
VALUES ('5', 'Десерты', 'deserts.jpg', '1', 1);
# dishes
INSERT INTO dish (id, name, photo, dish_out, price, dish_category_id, cabare_id)
VALUES ('1', 'Салат Весна', 'spring-salad.jpg', '5', '5000', '3', 1);

INSERT INTO dish (id, name, photo, dish_out, price, dish_category_id, cabare_id)
VALUES ('2', 'Салат Греческий', 'greek-salad.jpg', '4', '6000', '3', 1);

INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('3', 'Салат Столичный', '3', '6000', '3', 1);

INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('4', 'Салат Оливье', '5', '5000', '3', 1);

INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('5', 'Салат Винигрет', '2', '3800', '3', 1);

INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('6', 'Сельдь под шубой', '1', '4800', '2', 1);

INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('7', 'Курица запечёная целиком', '6', '32800', '2', 1);

INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('8', 'Голубцы', '3', '8400', '2', 1);

INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('9', 'Картофель отварной', '6', '1800', '2', 1);

INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('10', 'Солянка мясная', '5', '6400', '2', 1);

INSERT INTO dish (id, name, photo, dish_out, price, dish_category_id, cabare_id)
VALUES ('11', 'Мятное латте', 'latte-mint.jpg', '4', '6500', '4', 1);

INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('12', 'Печенье "Хрустящие черепашки"', '3', '4300', '1', 1);

INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('13', 'Чипсы из цукини ', '5', '3000', '1', 1);

INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('14', 'Чизкейк', '5', '7500', '5', 1);

INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('15', 'Тирамису', '5', '6500', '5', 1);

INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('16', 'Сент-Эв', '5', '7000', '5', 1);

INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('17', 'Шанте', '5', '8000', '5', 1);

INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('18', 'Чай зеленый', '300', '5000', '4', 1);
INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('19', 'Чай черный', '300', '7000', '4', 1);
INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('20', 'Кофе', '200', '6000', '4', 1);
INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('21', 'Мохито', '500', '7000', '4', 1);
INSERT INTO dish (id, name, dish_out, price, dish_category_id, cabare_id)
VALUES ('22', 'Вода', '500', '3000', '4', 1);

# employee
INSERT INTO employee (id, name, enabled, email, password, cabare_id)
VALUES (1, 'Вася Пупкин', b'01', 'vasya@gmail.com', '111111', 1);
INSERT INTO employee (id, name, enabled, email, password, cabare_id)
VALUES (2, 'Петя Васин', b'01', 'petya@gmail.com', '222222', 1);
INSERT INTO employee (id, name, enabled, email, password, cabare_id)
VALUES (3, 'Тетя Сима', b'01', 'sima@gmail.com', '333333', 2);

# discount
INSERT INTO discount (id, card_number, discount_size, cabare_id) VALUES ('1', 'kv - 123', '40', 1);
INSERT INTO discount (id, card_number, discount_size, cabare_id) VALUES ('2', 'kv-1234', '30', 1);
INSERT INTO discount (id, card_number, discount_size, cabare_id) VALUES ('3', 'kv-12345', '20', 1);
INSERT INTO discount (id, card_number, discount_size, cabare_id) VALUES ('4', 'kv-123456', '10', 1);

#measure
INSERT INTO measure (id, name, abbreviation) VALUES ('1', 'Килограммы', 'кг');
INSERT INTO measure (id, name, abbreviation) VALUES ('2', 'Граммы'    , 'гр');
INSERT INTO measure (id, name, abbreviation) VALUES ('3', 'Литры'     , 'л');

# ingredient
INSERT INTO ingredient (id, name, measure_id, cabare_id) VALUES ('1', 'Молоко', 3, 1);
INSERT INTO ingredient (id, name, measure_id, cabare_id) VALUES ('2', 'Мука'  , 2, 1);

# calculation
INSERT INTO calculation (id, dish_id, ingredient_id, quantity) VALUES ('1', '1', '1', '20.5');
INSERT INTO calculation (id, dish_id, ingredient_id, quantity) VALUES ('2', '1', '2', '30.5');
