--category 데이터 생성
INSERT INTO category (categoryname) VALUES
  ('electronics'),
  ('jewelery'),
  ('men''s clothing'),
  ('women''s clothing');

-- product 데이터 생성
INSERT INTO product (title, price, description, category_id, image, rate, count) VALUES
(
  'Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops',
  109.95,
  'Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday',
  1,
  'https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg',
  3.9,
  120
),
(
  'Mens Casual Premium Slim Fit T-Shirts',
  22.3,
  'Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.',
  1,
  'https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg',
  4.1,
  259
),
(
  'Mens Cotton Jacket',
  55.99,
  'great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.',
  1,
  'https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg',
  4.7,
  500
),
(
  'Mens Casual Slim Fit',
  15.99,
  'The color could be slightly different between on the screen and in practice. / Please note that body builds vary by person, therefore, detailed size information should be reviewed below on the product description.',
  1,
  'https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_.jpg',
  2.1,
  430
),
(
  'John Hardy Women''s Legends Naga Gold & Silver Dragon Station Chain Bracelet',
  695,
  'From our Legends Collection, the Naga was inspired by the mythical water dragon that protects the ocean''s pearl. Wear facing inward to be bestowed with love and abundance, or outward for protection.',
  2,
  'https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg',
  4.6,
  400
);

-- member 데이터 생성
INSERT INTO member (
    email, username, password, phone,
    firstname, lastname,
    city, street, number, zipcode,
    lat, longitude
) VALUES
('john@gmail.com', 'johnd', 'm38rmF$', '1-570-236-7033', 'john', 'doe', 'kilcoole', 'new road', 7682, '12926-3874', '-37.3159', '81.1496'),
('morrison@gmail.com', 'mor_2314', '83r5^_', '1-570-236-7033', 'david', 'morrison', 'kilcoole', 'Lovers Ln', 7267, '12926-3874', '-37.3159', '81.1496'),
('kevin@gmail.com', 'kevinryan', 'kev02937@', '1-567-094-1345', 'kevin', 'ryan', 'Cullman', 'Frances Ct', 86, '29567-1452', '40.3467', '-30.1310'),
('don@gmail.com', 'donero', 'ewedon', '1-765-789-6734', 'don', 'romer', 'San Antonio', 'Hunters Creek Dr', 6454, '98234-1734', '50.3467', '-20.1310'),
('derek@gmail.com', 'derek', 'jklg*_56', '1-956-001-1945', 'derek', 'powell', 'san Antonio', 'adams St', 245, '80796-1234', '40.3467', '-40.1310'),
('david_r@gmail.com', 'david_r', '3478*#54', '1-678-345-9856', 'david', 'russell', 'el paso', 'prospect st', 124, '12346-0456', '20.1677', '-10.6789'),
('miriam@gmail.com', 'snyder', 'f238&@*$', '1-123-943-0563', 'miriam', 'snyder', 'fresno', 'saddle st', 1342, '96378-0245', '10.3456', '20.6419'),
('william@gmail.com', 'hopkins', 'William56$hj', '1-478-001-0890', 'william', 'hopkins', 'mesa', 'vally view ln', 1342, '96378-0245', '50.3456', '10.6419'),
('kate@gmail.com', 'kate_h', 'kfejk@*_', '1-678-456-1934', 'kate', 'hale', 'miami', 'avondale ave', 345, '96378-0245', '40.12456', '20.5419'),
('jimmie@gmail.com', 'jimmie_k', 'klein*#%*', '1-104-001-4567', 'jimmie', 'klein', 'fort wayne', 'oak lawn ave', 526, '10256-4532', '30.24788', '-20.545419');

-- cart 데이터 생성
INSERT INTO cart (cart_id, user_id, cart_date) VALUES
(1, 1, '2020-03-02'),
(2, 1, '2020-01-02'),
(3, 2, '2020-03-01'),
(4, 3, '2020-01-01'),
(5, 3, '2020-03-01'),
(6, 4, '2020-03-01'),
(7, 8, '2020-03-01');

-- cart_item 데이터 생성
INSERT INTO cart_item (quantity, cart_id, product_id) VALUES
(4, 1, 1),
(1, 1, 2),
(6, 1, 3),
(4, 2, 2),
(10, 2, 1),
(2, 2, 5),
(2, 3, 1),
(1, 3, 9),
(4, 4, 1),
(1, 5, 7),
(1, 5, 8),
(2, 6, 10),
(3, 6, 12),
(1, 7, 18);
