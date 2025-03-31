DROP DATABASE IF EXISTS wishListDatabase;

CREATE DATABASE wishListDatabase;

USE wishListDatabase;

DROP TABLE IF EXISTS Users CASCADE;
CREATE TABLE Users(
                      userID INT NOT NULL AUTO_INCREMENT,
                      name VARCHAR(200) NOT NULL,
                      email VARCHAR(200),
                      password VARCHAR(200),
                      PRIMARY KEY(userID)
);

DROP TABLE IF EXISTS WishList CASCADE;
CREATE TABLE WishList(
                         wishlistID INT NOT NULL AUTO_INCREMENT,
                         name VARCHAR(200),
                         userID INT NOT NULL,
                         PRIMARY KEY(wishlistID),
                         FOREIGN KEY(userID) REFERENCES Users(userID) ON DELETE CASCADE

);

DROP TABLE IF EXISTS Wishes CASCADE;
CREATE TABLE Wishes(
                       wishID INT NOT NULL AUTO_INCREMENT,
                       name VARCHAR(200),
                       description VARCHAR(2000),
                       productLink VARCHAR(2000),
                       imageLink VARCHAR(2000),
                       price INT,
                       wishlistID INT NOT NULL,
                       reserved BOOLEAN,
                       reserveeID INT NULL,
                       PRIMARY KEY(wishID),
                       FOREIGN KEY(wishlistID) REFERENCES WishList(wishlistID) ON DELETE CASCADE,
                       FOREIGN KEY(reserveeID) REFERENCES Users(userID) ON DELETE SET NULL
);