USE wishListDatabase;

-- Insert test data into Persons
INSERT INTO Persons (name, email, password) VALUES
('Alice Johnson', 'alice@example.com', 'password123'),
('Bob Smith', 'bob@example.com', 'securepass'),
('Charlie Brown', 'charlie@example.com', 'charliepass');

-- Insert test data into WishList
INSERT INTO WishList (name, personID) VALUES
('Alice\'s Birthday List', 1),
('Bob\'s Tech Wishlist', 2),
('Charlie\'s Holiday Wishlist', 3);


-- Insert test data into Wishes
INSERT INTO Wishes (name, description, productLink, imageLink, wishlistID) VALUES
('Smartphone', 'Latest model with best camera', 'https://example.com/smartphone', 'https://example.com/images/smartphone.jpg', 1),
('Laptop', 'Powerful laptop for gaming and work', 'https://example.com/laptop', 'https://example.com/images/laptop.jpg', 1),
('Headphones', 'Noise-cancelling over-ear headphones', 'https://example.com/headphones', 'https://example.com/images/headphones.jpg', 2),
('Smartwatch', 'Fitness tracking and notifications on wrist', 'https://example.com/smartwatch', 'https://example.com/images/smartwatch.jpg', 2),
('Backpack', 'Stylish and durable travel backpack', 'https://example.com/backpack', 'https://example.com/images/backpack.jpg', 3),
('Camera', 'Mirrorless camera for photography', 'https://example.com/camera', 'https://example.com/images/camera.jpg', 3);

-- SELECT * FROM wishes;

-- DELETE FROM Persons WHERE personID = 1;


-- SELECT * FROM wishes; 