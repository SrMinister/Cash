CREATE TABLE IF NOT EXISTS `hyren_cash` (
    uuid CHAR(36) NOT NULL PRIMARY KEY,
    username VARCHAR(64),
    cash DOUBLE
);