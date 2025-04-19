CREATE TABLE cart_item (
    id SERIAL PRIMARY KEY,
    cart_id BIGINT,
    product_id BIGINT,
    quantity INTEGER NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES cart(cart_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);