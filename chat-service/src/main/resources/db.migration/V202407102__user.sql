CREATE TABLE _users (
                        id SERIAL PRIMARY KEY,
                        email VARCHAR(255) NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        active BOOLEAN,
                        registration_date TIMESTAMP
);
CREATE INDEX idx_active ON _users (active);
INSERT INTO _users (email, password, active, registration_date) VALUES
        ('user@gmail.com', '123', true, '2024-07-10 08:30:00'),
        ('asdf@gmail.com', 'asdf', true, '2024-07-08 11:00:00');
