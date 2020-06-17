CREATE TABLE `tasks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `messages` TEXT NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `status` varchar(32) NOT NULL DEFAULT 'TODO',
  `updated_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX `index_parent` ON `tasks` (`parent_id`);
