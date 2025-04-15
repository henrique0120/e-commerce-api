CREATE TABLE cart (
    cart_id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id_user) ON DELETE CASCADE
);