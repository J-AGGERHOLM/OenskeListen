DROP TABLE IF EXISTS USERS CASCADE;
CREATE TABLE USERS
(
    userID   INT          NOT NULL AUTO_INCREMENT,
    name     VARCHAR(200) NOT NULL,
    email    VARCHAR(200),
    password VARCHAR(200),
    PRIMARY KEY (userID)
);

DROP TABLE IF EXISTS WISHLIST CASCADE;
CREATE TABLE WISHLIST
(
    wishlistID INT NOT NULL AUTO_INCREMENT,
    name       VARCHAR(200),
    userID     INT NOT NULL,
    PRIMARY KEY (wishlistID),
    FOREIGN KEY (userID) REFERENCES Users (userID) ON DELETE CASCADE

);

DROP TABLE IF EXISTS WISHES CASCADE;
CREATE TABLE WISHES
(
    wishID      INT NOT NULL AUTO_INCREMENT,
    name        VARCHAR(200),
    description VARCHAR(2000),
    productLink VARCHAR(2000),
    imageLink   VARCHAR(2000),
    price       INT,
    wishlistID  INT NOT NULL,
    reserved    BOOLEAN,
    reserveeID  INT NULL,
    PRIMARY KEY (wishID),
    FOREIGN KEY (wishlistID) REFERENCES WishList (wishlistID) ON DELETE CASCADE,
    FOREIGN KEY (reserveeID) REFERENCES Users (userID) ON DELETE SET NULL
);

-- Insert Users
INSERT INTO USERS (name, email, password)
VALUES ('Alice Johnson', 'alice@example.com', 'password123'),
       ('Bob Smith', 'bob@example.com', 'securepass'),
       ('Charlie Davis', 'charlie@example.com', 'charlie123');

-- Insert Wishlists
INSERT INTO WISHLIST (name, userID)
VALUES ('Alices Birthday List', 1),
       ('Bobs Christmas List', 2),
       ('Charlies New Year List', 3);

-- Insert Wishes
INSERT INTO WISHES (name, description, productLink, imageLink, price, wishlistID, reserved, reserveeID)
VALUES ('Smartphone', 'Latest model with OLED display', 'https://example.com/smartphone',
        'https://example.com/smartphone.jpg', 999, 1, FALSE, NULL),
       ('Gaming Laptop', 'High-end gaming laptop with RTX 4080', 'https://example.com/gaming-laptop',
        'https://example.com/gaming-laptop.jpg', 2000, 1, TRUE, 2),
       ('Headphones', 'Noise-canceling over-ear headphones', 'https://example.com/headphones',
        'https://example.com/headphones.jpg', 300, 2, FALSE, NULL),
       ('Smartwatch', 'Waterproof smartwatch with GPS', 'https://example.com/smartwatch',
        'https://example.com/smartwatch.jpg', 250, 2, TRUE, 1),
       ('E-Reader', 'Lightweight e-reader with backlit display', 'https://example.com/ereader',
        'https://example.com/ereader.jpg', 150, 3, FALSE, NULL),
       ('Backpack', 'Durable travel backpack with USB charging port', 'https://example.com/backpack',
        'https://example.com/backpack.jpg', 80, 3, TRUE, 2);
