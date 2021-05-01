CREATE TABLE Storage
(
    id   INT          NOT NULL,
    type ENUM('FILE') NOT NULL,
    path VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE UQ_Storage_path(path)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;