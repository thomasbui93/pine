CREATE TABLE `tasks` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `messages` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `status` varchar(32) DEFAULT 'TODO',
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
