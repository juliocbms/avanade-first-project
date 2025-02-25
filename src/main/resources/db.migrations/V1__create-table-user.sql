CREATE TABLE tb_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    account_id BIGINT,
    card_id BIGINT,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'USER'),
);