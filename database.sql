-- ==========================================
-- GreenCart Database Schema & Data
-- ==========================================

-- ------------------------------------------
-- 1. Create Tables
-- ------------------------------------------

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(120) NOT NULL,
    role VARCHAR(20)
);

CREATE TABLE plants (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(1000),
    price DOUBLE,
    image_url VARCHAR(255),
    water_needs VARCHAR(255),
    sunlight VARCHAR(255),
    is_beginner_friendly BOOLEAN,
    is_low_maintenance BOOLEAN
);

CREATE TABLE cart_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity INTEGER,
    plant_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (plant_id) REFERENCES plants(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_date TIMESTAMP,
    status VARCHAR(255),
    total_amount DOUBLE,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    price DOUBLE,
    quantity INTEGER,
    order_id BIGINT,
    plant_id BIGINT,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (plant_id) REFERENCES plants(id)
);

-- ------------------------------------------
-- 2. Insert Plant Data (25 Plants)
-- ------------------------------------------

INSERT INTO plants (name, description, price, image_url, water_needs, sunlight, is_beginner_friendly, is_low_maintenance) VALUES
('Monstera Deliciosa', 'A beautiful tropical plant known for its natural leaf holes. Perfect for adding a jungle vibe to any room.', 35.00, '/images/monstera.png', 'Water every 1-2 weeks', 'Bright, indirect sunlight', TRUE, TRUE),
('Snake Plant', 'One of the easiest plants to care for. It filters indoor air and looks highly architectural.', 22.50, '/images/snake_plant.png', 'Water every 2-3 weeks', 'Low to bright indirect light', TRUE, TRUE),
('Fiddle Leaf Fig', 'A stunning statement plant with large, violin-shaped leaves.', 45.00, '/images/fiddle_leaf.png', 'Water every 1-2 weeks', 'Bright, indirect sunlight', FALSE, FALSE),
('Peace Lily', 'A beautiful, easy-care plant with dark green leaves and elegant white flowers.', 28.00, '/images/peace_lily.png', 'Keep soil consistently moist', 'Low to medium indirect light', TRUE, FALSE),
('Aloe Vera', 'A succulent plant species of the genus Aloe. It has medicinal uses and is very easy to grow.', 15.00, '/images/snake_plant.png', 'Water every 3 weeks', 'Bright, indirect sunlight', TRUE, TRUE),
('Spider Plant', 'Considered one of the most adaptable of houseplants and the easiest to grow.', 18.00, '/images/peace_lily.png', 'Water every week', 'Bright, indirect sunlight', TRUE, TRUE),
('Pothos', 'An easy-care trailing plant that looks great in hanging baskets or on shelves.', 16.50, '/images/monstera.png', 'Water every 1-2 weeks', 'Low to bright indirect light', TRUE, TRUE),
('ZZ Plant', 'Characterized by its shiny, wide, oval-shaped leaves that shoot upward.', 25.00, '/images/snake_plant.png', 'Water every 2-3 weeks', 'Low to bright indirect light', TRUE, TRUE),
('Rubber Plant', 'A popular houseplant with thick, glossy leaves that can grow quite large.', 32.00, '/images/fiddle_leaf.png', 'Water every 1-2 weeks', 'Bright, indirect sunlight', TRUE, FALSE),
('Cast Iron Plant', 'Earns its name by surviving under conditions that would kill most other plants.', 24.00, '/images/peace_lily.png', 'Water every 2 weeks', 'Low to moderate light', TRUE, TRUE),
('Philodendron', 'A large genus of flowering plants known for their impressive foliage.', 20.00, '/images/monstera.png', 'Water every week', 'Bright, indirect sunlight', TRUE, TRUE),
('Chinese Evergreen', 'A highly decorative plant with several interesting varieties.', 22.00, '/images/snake_plant.png', 'Water every 1-2 weeks', 'Low to moderate light', TRUE, TRUE),
('Bird''s Nest Fern', 'A unique fern with large, undivided fronds that resemble banana leaves.', 26.00, '/images/peace_lily.png', 'Keep soil moist', 'Medium indirect light', FALSE, FALSE),
('Calathea', 'Known for their stunningly beautiful leaves with complex patterns.', 30.00, '/images/fiddle_leaf.png', 'Keep soil moist', 'Medium indirect light', FALSE, FALSE),
('Majesty Palm', 'A robust, tropical palm with graceful, feathery fronds.', 40.00, '/images/monstera.png', 'Water every week', 'Bright, indirect sunlight', FALSE, FALSE),
('Boston Fern', 'A classic houseplant known for its lush, drooping fronds.', 22.00, '/images/peace_lily.png', 'Keep soil moist', 'Bright, indirect sunlight', FALSE, FALSE),
('String of Pearls', 'A captivating succulent with pea-like leaves trailing down thin stems.', 18.00, '/images/snake_plant.png', 'Water every 2-3 weeks', 'Bright, indirect sunlight', FALSE, TRUE),
('Jade Plant', 'A popular succulent houseplant with fleshy, oval-shaped leaves.', 20.00, '/images/fiddle_leaf.png', 'Water every 2-3 weeks', 'Bright, indirect sunlight', TRUE, TRUE),
('English Ivy', 'A versatile evergreen vine that is easy to grow indoors.', 15.00, '/images/monstera.png', 'Water every week', 'Bright, indirect sunlight', TRUE, TRUE),
('Peperomia', 'A diverse group of small, easy-to-care-for houseplants.', 16.00, '/images/peace_lily.png', 'Water every 1-2 weeks', 'Medium to bright indirect light', TRUE, TRUE),
('Air Plant', 'Unique plants that don''t need soil to grow.', 12.00, '/images/snake_plant.png', 'Mist weekly or soak every 2 weeks', 'Bright, indirect sunlight', TRUE, TRUE),
('Bonsai Tree', 'A miniature tree cultivated for ornamental purposes.', 50.00, '/images/fiddle_leaf.png', 'Water when topsoil feels dry', 'Bright, indirect sunlight', FALSE, FALSE),
('Orchid', 'Elegant flowering plants known for their long-lasting blooms.', 35.00, '/images/peace_lily.png', 'Water weekly', 'Bright, indirect sunlight', FALSE, FALSE),
('Pilea Peperomioides', 'Also known as the Chinese money plant, featuring round, coin-like leaves.', 24.00, '/images/monstera.png', 'Water every week', 'Bright, indirect sunlight', TRUE, TRUE),
('Croton', 'A vibrant plant with wildly colorful, variegated leaves.', 28.00, '/images/fiddle_leaf.png', 'Keep soil evenly moist', 'Bright, indirect sunlight', FALSE, FALSE);
