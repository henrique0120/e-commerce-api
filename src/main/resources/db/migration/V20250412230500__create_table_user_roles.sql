CREATE TABLE tab_user_roles (
    id_user BIGINT NOT NULL,
    role_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE
);