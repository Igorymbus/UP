-- Схема базы данных для магазина гитар
-- Создается автоматически через JPA, но можно использовать для ручного создания

-- Таблица брендов
CREATE TABLE IF NOT EXISTS brand (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    country VARCHAR(255)
);

-- Таблица категорий
CREATE TABLE IF NOT EXISTS category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255)
);

-- Таблица гитар
CREATE TABLE IF NOT EXISTS guitar (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(150) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock INTEGER NOT NULL,
    color VARCHAR(255),
    brand_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES brand(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

-- Таблица клиентов
CREATE TABLE IF NOT EXISTS customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- Таблица адресов
CREATE TABLE IF NOT EXISTS address (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(200) NOT NULL,
    city VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    postal_code VARCHAR(20),
    customer_id BIGINT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- Таблица заказов
CREATE TABLE IF NOT EXISTS shop_order (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- Таблица позиций заказа
CREATE TABLE IF NOT EXISTS order_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    guitar_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES shop_order(id),
    FOREIGN KEY (guitar_id) REFERENCES guitar(id)
);

-- Таблица платежей
CREATE TABLE IF NOT EXISTS payment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL UNIQUE,
    amount DECIMAL(10,2) NOT NULL,
    paid_at TIMESTAMP NOT NULL,
    method VARCHAR(20) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES shop_order(id)
);

-- Таблица поставщиков
CREATE TABLE IF NOT EXISTS supplier (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    contact_info VARCHAR(255)
);

-- Связующая таблица поставщик-гитара
CREATE TABLE IF NOT EXISTS supplier_guitar (
    supplier_id BIGINT NOT NULL,
    guitar_id BIGINT NOT NULL,
    PRIMARY KEY (supplier_id, guitar_id),
    FOREIGN KEY (supplier_id) REFERENCES supplier(id),
    FOREIGN KEY (guitar_id) REFERENCES guitar(id)
);

-- Таблица отзывов
CREATE TABLE IF NOT EXISTS review (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    guitar_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    rating INTEGER NOT NULL,
    comment VARCHAR(500),
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (guitar_id) REFERENCES guitar(id),
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- Таблица пользователей системы
CREATE TABLE IF NOT EXISTS app_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE
);

-- Таблица ролей пользователей
CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id, role),
    FOREIGN KEY (user_id) REFERENCES app_user(id)
);
