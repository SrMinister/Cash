INSERT INTO `hyren_cash` (`uuid`, `username`, `cash`) VALUES (?, ?, ?)
 ON DUPLICATE KEY UPDATE `cash` = VALUE(`cash`)