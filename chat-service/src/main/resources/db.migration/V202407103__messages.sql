CREATE TABLE message_entity (
                                id SERIAL PRIMARY KEY,
                                sender VARCHAR(255) NOT NULL,
                                content TEXT,
                                chat_room VARCHAR(255),
                                sent_time TIMESTAMP,
                                type VARCHAR(50)
);
