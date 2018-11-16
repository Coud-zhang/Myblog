-- auto-generated definition
CREATE TABLE blog
(
  id      INT(10) AUTO_INCREMENT PRIMARY KEY,
  title   VARCHAR(50)   NOT NULL,
  article LONGTEXT      NULL,
  data    DATE          NULL,
  zan     INT(4)        NULL,
  label   VARCHAR(50)   NULL
)
  ENGINE = InnoDB;