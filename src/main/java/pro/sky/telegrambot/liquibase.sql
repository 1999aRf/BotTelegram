CREATE TABLE notification_tasks (
    id BIGSERIAL PRIMARY KEY,
    chat_id BIGINT NOT NULL,
    message_text VARCHAR(255) NOT NULL,
    notification_time TIMESTAMP NOT NULL,
);