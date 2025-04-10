CREATE TABLE `favorites` (
  `favorite_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `dish_id` BIGINT NOT NULL,
  PRIMARY KEY (`favorite_id`),
  INDEX `idx_user_id` (`user_id`),
  INDEX `idx_dish_id` (`dish_id`),
  CONSTRAINT `fk_favorites_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_favorites_dish` FOREIGN KEY (`dish_id`) REFERENCES `dish` (`dish_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;