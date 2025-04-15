CREATE TABLE products (
    id_product SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    stock INTEGER NOT NULL,
    description TEXT NOT NULL
);