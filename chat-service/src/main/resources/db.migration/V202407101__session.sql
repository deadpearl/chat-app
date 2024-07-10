CREATE TABLE session (
                         id SERIAL PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         token VARCHAR(255) NOT NULL,
                         token_create_date TIMESTAMP,
                         token_expire_date TIMESTAMP
);
