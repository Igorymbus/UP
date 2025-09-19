-- Тестовые данные для магазина гитар
-- Вставляются автоматически при запуске приложения

-- Бренды
INSERT INTO brand (name, country) VALUES 
('Fender', 'USA'),
('Gibson', 'USA'),
('Martin', 'USA'),
('Taylor', 'USA'),
('Ibanez', 'Japan'),
('Yamaha', 'Japan');

-- Категории
INSERT INTO category (name, description) VALUES 
('Electric', 'Электрогитары'),
('Acoustic', 'Акустические гитары'),
('Bass', 'Бас-гитары'),
('Classical', 'Классические гитары');

-- Гитары
INSERT INTO guitar (model, price, stock, color, brand_id, category_id) VALUES 
('Stratocaster', 1200.00, 5, 'Sunburst', 1, 1),
('Telecaster', 1100.00, 3, 'Black', 1, 1),
('Les Paul', 2500.00, 2, 'Cherry', 2, 1),
('SG', 1800.00, 4, 'Red', 2, 1),
('D-28', 3200.00, 1, 'Natural', 3, 2),
('D-18', 2800.00, 2, 'Natural', 3, 2),
('814ce', 3500.00, 1, 'Natural', 4, 2),
('RG550', 800.00, 6, 'Blue', 5, 1),
('FG800', 200.00, 10, 'Natural', 6, 2);

-- Клиенты
INSERT INTO customer (full_name, email) VALUES 
('John Doe', 'john@example.com'),
('Jane Smith', 'jane@example.com'),
('Mike Johnson', 'mike@example.com'),
('Sarah Wilson', 'sarah@example.com');

-- Адреса
INSERT INTO address (street, city, country, postal_code, customer_id) VALUES 
('123 Main St', 'New York', 'USA', '10001', 1),
('456 Oak Ave', 'Los Angeles', 'USA', '90210', 2),
('789 Pine Rd', 'Chicago', 'USA', '60601', 3),
('321 Elm St', 'Boston', 'USA', '02101', 4);

-- Поставщики
INSERT INTO supplier (name, contact_info) VALUES 
('Main Supplier', 'supplier@example.com'),
('Guitar World', 'orders@guitarworld.com'),
('Music Store', 'info@musicstore.com');

-- Связи поставщик-гитара
INSERT INTO supplier_guitar (supplier_id, guitar_id) VALUES 
(1, 1), (1, 2), (1, 3), (1, 4),
(2, 5), (2, 6), (2, 7),
(3, 8), (3, 9);

-- Пользователи системы
INSERT INTO app_user (username, email, password_hash, enabled) VALUES 
('admin', 'admin@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', true),
('manager', 'manager@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', true),
('customer', 'customer@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', true);

-- Роли пользователей
INSERT INTO user_roles (user_id, role) VALUES 
(1, 'ADMIN'),
(2, 'MANAGER'),
(3, 'CUSTOMER');

-- Заказы
INSERT INTO shop_order (customer_id, created_at) VALUES 
(1, '2024-01-15 10:30:00'),
(2, '2024-01-16 14:20:00'),
(3, '2024-01-17 09:15:00');

-- Позиции заказов
INSERT INTO order_item (order_id, guitar_id, quantity, unit_price) VALUES 
(1, 1, 1, 1200.00),
(1, 3, 1, 2500.00),
(2, 5, 1, 3200.00),
(3, 8, 2, 800.00);

-- Платежи
INSERT INTO payment (order_id, amount, paid_at, method) VALUES 
(1, 3700.00, '2024-01-15 10:35:00', 'CARD'),
(2, 3200.00, '2024-01-16 14:25:00', 'TRANSFER'),
(3, 1600.00, '2024-01-17 09:20:00', 'CASH');

-- Отзывы
INSERT INTO review (guitar_id, customer_id, rating, comment, created_at) VALUES 
(1, 1, 5, 'Отличная гитара! Звук просто потрясающий.', '2024-01-20 16:00:00'),
(3, 1, 4, 'Хорошая гитара, но дороговата.', '2024-01-21 12:30:00'),
(5, 2, 5, 'Лучшая акустическая гитара, которую я когда-либо играл!', '2024-01-22 18:45:00'),
(8, 3, 3, 'Нормальная гитара за свои деньги.', '2024-01-23 11:15:00');
