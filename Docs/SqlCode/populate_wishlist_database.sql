-- Insert Users
INSERT INTO Users (name, email, password) VALUES
                                              ('Alice Johnson', 'alice@example.com', 'password123'),
                                              ('Bob Smith', 'bob@example.com', 'securepass'),
                                              ('Charlie Davis', 'charlie@example.com', 'charlie123');

-- Insert Wishlists
INSERT INTO WishList (name, userID) VALUES
                                        ('Alices Birthday List', 1),
                                        ('Bobs Christmas List', 2),
                                        ('Charlies New Year List', 3);

-- Insert Wishes
INSERT INTO Wishes (name, description, productLink, imageLink, price, wishlistID, reserved, reserveeID) VALUES
                                                                                                            ('Smartphone', 'Latest model with OLED display', 'https://example.com/smartphone', 'https://example.com/smartphone.jpg', 999, 1, FALSE, NULL),
                                                                                                            ('Gaming Laptop', 'High-end gaming laptop with RTX 4080', 'https://example.com/gaming-laptop', 'https://example.com/gaming-laptop.jpg', 2000, 1, TRUE, 2),
                                                                                                            ('Headphones', 'Noise-canceling over-ear headphones', 'https://example.com/headphones', 'https://example.com/headphones.jpg', 300, 2, FALSE, NULL),
                                                                                                            ('Smartwatch', 'Waterproof smartwatch with GPS', 'https://example.com/smartwatch', 'https://example.com/smartwatch.jpg', 250, 2, TRUE, 1),
                                                                                                            ('E-Reader', 'Lightweight e-reader with backlit display', 'https://example.com/ereader', 'https://example.com/ereader.jpg', 150, 3, FALSE, NULL),
                                                                                                            ('Backpack', 'Durable travel backpack with USB charging port', 'https://example.com/backpack', 'https://example.com/backpack.jpg', 80, 3, TRUE, 2);
