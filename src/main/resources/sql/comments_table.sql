CREATE TABLE comments (
    comment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    dish_id BIGINT,
    content TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (dish_id) REFERENCES dish(dish_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;