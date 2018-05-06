CREATE TABLE t_friend
(
  owner_id  INT NOT NULL,
  friend_id INT NOT NULL,
  PRIMARY KEY (owner_id, friend_id)
)
  ENGINE = InnoDB;

CREATE INDEX fk_friend_friendId_user_id
  ON t_friend (friend_id);

CREATE TABLE t_group
(
  id           INT AUTO_INCREMENT
    PRIMARY KEY,
  name         VARCHAR(64)  NOT NULL,
  harem_master INT          NOT NULL,
  announcement VARCHAR(256) NULL
)
  ENGINE = InnoDB;

CREATE INDEX fk_group_haremMaster_user_id
  ON t_group (harem_master);

CREATE TABLE t_message
(
  id        INT          NOT NULL,
  from_id   INT          NOT NULL,
  to_id     INT          NOT NULL,
  send_time DATETIME     NOT NULL,
  content   VARCHAR(256) NOT NULL,
  PRIMARY KEY (id, from_id, to_id)
)
  ENGINE = InnoDB;

CREATE INDEX fk_message_fromId_user_id
  ON t_message (from_id);

CREATE INDEX fk_message_toId_user_id
  ON t_message (to_id);

CREATE TABLE t_password
(
  user_id  INT         NOT NULL
    PRIMARY KEY,
  password VARCHAR(64) NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE t_user
(
  id      INT AUTO_INCREMENT
    PRIMARY KEY,
  account VARCHAR(32) NOT NULL,
  name    VARCHAR(32) NULL,
  CONSTRAINT unindex_user_id
  UNIQUE (id),
  CONSTRAINT unindex_user_account
  UNIQUE (account)
)
  ENGINE = InnoDB;

ALTER TABLE t_friend
  ADD CONSTRAINT fk_friend_ownerId_user_id
FOREIGN KEY (owner_id) REFERENCES t_user (id);

ALTER TABLE t_friend
  ADD CONSTRAINT fk_friend_friendId_user_id
FOREIGN KEY (friend_id) REFERENCES t_user (id);

ALTER TABLE t_group
  ADD CONSTRAINT fk_group_haremMaster_user_id
FOREIGN KEY (harem_master) REFERENCES t_user (id);

ALTER TABLE t_message
  ADD CONSTRAINT fk_message_fromId_user_id
FOREIGN KEY (from_id) REFERENCES t_user (id);

ALTER TABLE t_message
  ADD CONSTRAINT fk_message_toId_user_id
FOREIGN KEY (to_id) REFERENCES t_user (id);

ALTER TABLE t_password
  ADD CONSTRAINT fk_password_userId_user_id
FOREIGN KEY (user_id) REFERENCES t_user (id)
  ON UPDATE CASCADE
  ON DELETE CASCADE;

CREATE TABLE t_user_group
(
  user_id  INT NOT NULL,
  group_id INT NOT NULL,
  PRIMARY KEY (user_id, group_id),
  CONSTRAINT fk_userGroup_userId_user_id
  FOREIGN KEY (user_id) REFERENCES t_user (id),
  CONSTRAINT fk_userGroup_groupId_user_id
  FOREIGN KEY (group_id) REFERENCES t_group (id)
)
  ENGINE = InnoDB;

CREATE INDEX fk_userGroup_groupId_user_id
  ON t_user_group (group_id);

CREATE TABLE t_user_information
(
  user_id       INT          NOT NULL
    PRIMARY KEY,
  gender        VARCHAR(8)   NULL,
  age           INT          NULL,
  birth         DATE         NULL,
  address       VARCHAR(256) NULL,
  head_portrait VARCHAR(256) NULL,
  e_mail        VARCHAR(128) NULL,
  phone         VARCHAR(11)  NULL,
  CONSTRAINT fk_userInformation_userId_user_id
  FOREIGN KEY (user_id) REFERENCES t_user (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
)
  ENGINE = InnoDB;

