/*users*/
INSERT INTO users (id,address, birthday, email, name, password, phonenumber, subscription) VALUES (10000,'asdfdsgthhtnbgf', '2017-05-10 00:00:00.000000', 'admin@gmail.com', 'Admin Admin', '$2a$10$9/tP3GSlh4YyXWFqaJ79xOMPiA4SV6u32iC98dJT/2/wk/TSJIjq2', '+375293756683', true);
INSERT INTO users (id,address, birthday, email, name, password, phonenumber, subscription) VALUES (10001,'asdfdsgthhtnbgfbr', '2017-05-10 00:00:00.000000', 'user@user.com', 'User User', '$2a$10$9/tP3GSlh4YyXWFqaJ79xOMPiA4SV6u32iC98dJT/2/wk/TSJIjq2', '+375293756686', true);
/*zones*/
INSERT INTO zones (description, image, title) VALUES ('Descend into a tropical getaway in Mayfair as you escape into a world of South Sea dining, at Trader Vic''s. Start with traditional finger food, pick up a salad or a soup, and for main opt for a Trader Vic favourite such as Beef & Reef or Scottish lobster.', 'https://cdn.londonandpartners.com/visit/london-organisations/trader-vics/89678-640x360-trader_vic_640.jpg', 'Trader Vic’s');
INSERT INTO zones (description, image, title) VALUES ('Based on hit film Forrest Gump, this Southern American eatery is themed on its main characters and its Alabama location. It’s all about the shrimp at Bubba Gump Shrimp Co., with lots of crustacean creations.', 'https://cdn.londonandpartners.com/visit/london-organisations/bubba-gump-shrimp/93646-640x360-bubbagumpshrimp640.jpg', 'Bubba Gump Shrim Co.');
INSERT INTO zones (description, image, title) VALUES ('Food and beverage is an important part of life. This is why we want each and every meal to be a culinary experience regardless if you are travelling or just popping in at a nearby hotel for breakfast, brunch, lunch or dinner! ', 'https://cdn.londonandpartners.com/assets/attractions/eat/25273-640x360-beach-blanket-babylon-640.jpg', 'Beach Blanket Babylon');
INSERT INTO zones (description, image, title) VALUES ('Based on hit film Forrest Gump, this Southern American eatery is themed on its main characters and its Alabama location. ', 'https://cdn.londonandpartners.com/visit/london-organisations/brunswick-house-cafe/70201-640x360-640-brunswick.jpg', 'Brunswick House Cafe');
INSERT INTO zones (description, image, title) VALUES ('This Asian fusion restaurant in Covent Garden (with a sister eatery in Soho) boasts a pioneering interactive ordering system projected onto the table. At Inamo, you can place orders via a 3D menu, choose from a selection of virtual tablecloths.', 'https://cdn.londonandpartners.com/visit/london-organisations/inamo-covent-garden/93645-640x360-inamo_table.jpg', 'Inamo');
INSERT INTO zones (description, image, title) VALUES ('If you''ve ever pondered what it would be like to wine (or vodka) and dine at Grandma''s house in old Russia, then Mari Vanna will answer all your desires. A remarkably authentic slice of Soviet era cuisine and comradery.', 'https://cdn.londonandpartners.com/visit/london-organisations/mari-vanna/89504-640x360-mari-vanna-restaurant-640.jpg', 'Mari Vanna');
INSERT INTO zones (description, image, title) VALUES ('A restaurant that is anything but quiet, Sarastro is "the show after the show" thanks to the musical entertainment played out live amongst the smiling diners. The drama continues on your plate with Turkish.', 'https://cdn.londonandpartners.com/visit/london-organisations/sarastro/89505-640x360-sarastro-restaurant-640.jpg', 'Sarastro');
INSERT INTO zones (description, image, title) VALUES ('Get a taste of South Africa at Shaka Zulu, a lively restaurant, bar and nightclub in Camden. The South African flare for exotic grilled meats, unusual cocktails, ostentatious décor, and infectious toe-tapping music fills this Camden Market.', 'https://cdn.londonandpartners.com/visit/london-organisations/shaka-zulu/70209-640x360-640-shaka-zulu.jpg', 'Shaka Zulu');
INSERT INTO zones (description, image, title) VALUES ('Sketch turns eating and drinking into a whole new experience. The hardest part is choosing between the elegant, two Michelin-starred restaurant, Lecture Room; the woodland-themed lunch venue, Glade; the brasserie-style Gallery.', 'https://cdn.londonandpartners.com/visit/london-organisations/sketch/84245-640x360-sketch-restaurant-david-shrigley-640.jpg', 'Sketch');
/*roles*/
INSERT INTO roles (id,authority) VALUES (10000,'ADMIN');
/*user_role*/
INSERT INTO user_role (user_id, role_id) VALUES (10000,10000);
/*categories*/
INSERT INTO category (id,name) VALUES (10000,'Холодные закуски');
INSERT INTO category (id,name) VALUES (10001,'Горячие закуски');
INSERT INTO category (id,name) VALUES (10002,'Салаты');
INSERT INTO category (id,name) VALUES (10003,'Супы');
INSERT INTO category (id,name) VALUES (10004,'Гарниры');
INSERT INTO category (id,name) VALUES (10005,'Десерты');
INSERT INTO category (id,name) VALUES (10006,'Алкоголь');
INSERT INTO category (id,name) VALUES (10007,'Горячие блюда из рыбы');
INSERT INTO category (id,name) VALUES (10008,'Горячие блюда из мяса');
/*dishes*/
INSERT INTO dish (id,description, image, mass, name, price, category_id) VALUES (10000,'без описания', 'http://grebeshok.by/cms/menu/%D0%9C%D0%B8%D0%BD%D1%8C%D0%BE%D0%BD_%D1%81_%D0%B5%D0%B6%D0%B5%D0%B2%D0%B8%D0%BA%D0%BE%D0%B9.jpg', 54, 'Миньон с ежевикой (песочная корзинка с сыром Филадельфия и свежей ежевикой) ', 4.4, 10006);
INSERT INTO dish (id,description, image, mass, name, price, category_id) VALUES (10001,'без описания', 'http://grebeshok.by/cms/menu/%D0%A2%D0%B8%D1%80%D0%B0%D0%BC%D0%B8%D1%81%D1%83.jpg', 100, 'Тирамису', 6.2, 10006);
INSERT INTO dish (id,description, image, mass, name, price, category_id) VALUES (10002,'без описания', 'http://grebeshok.by/cms/menu/%D0%A2%D1%83%D0%BD%D0%B5%D1%86,_%D0%BE%D0%B1%D0%B6%D0%B0%D1%80%D0%B5%D0%BD%D0%BD%D1%8B%D0%B9_%D0%B2_%D0%BA%D1%83%D0%BD%D0%B6%D1%83%D1%82%D0%B5,_%D1%81_%D1%86%D0%B8%D1%82%D1%80%D1%83%D1%81%D0%BE%D0%B2%D1%8B%D0%BC_%D0%BF%D0%B0%D0%BD%D0%B0%D1%88%D0%B5_%D1%82_%D0%BA%D0%BB%D0%B5%D0%BD%D0%BE%D0%B2%D1%8B%D0%BC_%D1%81%D0%B8%D1%80%D0%BE%D0%BF%D0%BE%D0%BC.jpg', 226, 'Тунец, обжаренный в кунжуте, с цитрусовым панаше и кленовым соусом', 33, 10001);
INSERT INTO dish (id,description, image, mass, name, price, category_id) VALUES (10003,'без описания', 'http://grebeshok.by/cms/menu/%D0%9B%D0%BE%D1%81%D0%BE%D1%81%D1%8C-%D0%BC%D0%B8%D0%BD%D1%83%D1%82%D0%BA%D0%B0,_%D0%BB%D1%83%D0%BA-%D1%84%D1%80%D0%B8_%D0%B8_%D0%BC%D0%B8%D0%BA%D1%81_%D1%81%D0%B0%D0%BB%D0%B0%D1%82.jpg', 260, 'Лосось-минутка, лук фри и хлебцы с анисом ', 27, 10001);
INSERT INTO dish (id,description, image, mass, name, price, category_id) VALUES (10004,'без описания', 'http://grebeshok.by/cms/menu/IMG_3466.jpg', 150, 'Хамон с розмарином', 45, 10001);
INSERT INTO dish (id,description, image, mass, name, price, category_id) VALUES (10005,'без описания', 'http://grebeshok.by/cms/menu/%D0%A4%D1%83%D0%B0-%D0%B3%D1%80%D0%B0-%D1%81-%D1%8D%D0%BA%D0%B7%D0%BE%D1%82%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%BC-%D0%B6%D1%8E%D0%BB%D1%8C%D0%B5%D0%BD%D0%BE%D0%BC-%D0%B8%D0%B7-%D0%BC%D0%B0%D0%BD%D0%B3%D0%BE,-%D0%BA%D0%BB%D1%83%D0%B1%D0%BD%D0%B8%D0%BA%D0%B0-%D1%81-%D0%BF%D1%80%D1%8F%D0%BD%D0%BE%D1%81%D1%82%D1%8F%D0%BC%D0%B8.jpg', 160, 'Фуа-гра утиная с экзотическим жульеном из манго и клубникой с пряностями', 35, 10002);
INSERT INTO dish (id,description, image, mass, name, price, category_id) VALUES (10006,'без описания', 'http://grebeshok.by/cms/menu/%D0%A1%D0%BB%D0%B5%D0%B3%D0%BA%D0%B0-%D0%BE%D0%B1%D0%B6%D0%B0%D1%80%D0%B5%D0%BD%D0%BD%D1%8B%D0%B5-%D0%BC%D0%BE%D1%80%D1%81%D0%BA%D0%B8%D0%B5-%D0%B3%D1%80%D0%B5%D0%B1%D0%B5%D1%88%D0%BA%D0%B8-%D1%81-%D1%80%D1%83%D0%BA%D0%BA%D0%BE%D0%BB%D0%BE%D0%B9-%D0%B8-%D0%B1%D1%80%D0%BE%D0%BA%D0%BA%D0%BE%D0%BB%D0%B8.jpg', 148, 'Слегка обжаренные морские гребешки с рукколой и брокколи. ', 30, 10002);
INSERT INTO dish (id,description, image, mass, name, price, category_id) VALUES (10007,'без описания', 'http://grebeshok.by/cms/menu/%D0%A7%D0%B8%D0%BB%D0%B8%D0%B9%D1%81%D0%BA%D0%B8%D0%B9_%D1%81%D0%B8%D0%B1%D0%B0%D1%81_%D1%81_%D0%BE%D0%B2%D0%BE%D1%89%D0%B0%D0%BC%D0%B8.jpg', 240, 'Палтус с овощами ', 35, 10007);
INSERT INTO dish (id,description, image, mass, name, price, category_id) VALUES (10008,'без описания', 'http://cs9.pikabu.ru/post_img/big/2017/01/13/9/1484319841167059830.jpg', 398, 'Стейк из говядины с перцовым соусом, картофелем "Шато" и спаржей', 34, 10005);
INSERT INTO dish (id,description, image, mass, name, price, category_id) VALUES (10009,'Вид американского виски. Выпускается в винокурнях города Линчберг, штат Теннесси, США, с XIX века. Напиток изготавливается из 80 % кукурузы, 8 % ячменя и 12 % ржи на основе чистой ключевой воды и в итоге содержит около 40 % алкоголя', 'https://img2.goodfon.ru/wallpaper/big/b/33/jack-daniels-viski-butylka.jpg', 700, 'Jack Daniel’s', 45, 10008);
/*orders*/
INSERT INTO orders (id,date_time, user_id) VALUES (10000,'2017-05-09 16:39:22.919000', 10000);
INSERT INTO orders (id,date_time, user_id) VALUES (10001,'2017-05-09 16:46:20.214000', 10000);
/*order_dish*/
INSERT INTO order_dish (order_id, dish_id) VALUES (10000, 10000);
INSERT INTO order_dish (order_id, dish_id) VALUES (10000, 10001);
INSERT INTO order_dish (order_id, dish_id) VALUES (10000, 10002);
INSERT INTO order_dish (order_id, dish_id) VALUES (10000, 10003);
INSERT INTO order_dish (order_id, dish_id) VALUES (10000, 10004);
INSERT INTO order_dish (order_id, dish_id) VALUES (10000, 10005);
INSERT INTO order_dish (order_id, dish_id) VALUES (10000, 10006);
INSERT INTO order_dish (order_id, dish_id) VALUES (10000, 10007);
INSERT INTO order_dish (order_id, dish_id) VALUES (10000, 10008);
INSERT INTO order_dish (order_id, dish_id) VALUES (10000, 10009);
INSERT INTO order_dish (order_id, dish_id) VALUES (10001, 10008);