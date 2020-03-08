-- permission --
create table if not exists permission
(
    pid  int(11)      not null AUTO_INCREMENT,
    name varchar(255) not null default '',
    url  varchar(255)          default '',
    PRIMARY KEY (pid)
) ENGINE InnoDB
  DEFAULT charset = utf8;

INSERT INTO permission
VALUES ('1', 'add', '');
INSERT INTO permission
VALUES ('2', 'delete', '');
INSERT INTO permission
VALUES ('3', 'edit', '');
INSERT INTO permission
VALUES ('4', 'query', '');

-- --
create table if not exists user
(
    uid      int(11)      not null auto_increment,
    username varchar(255) not null default '',
    password varchar(255) not null default '',
    primary key (uid)
) ENGINE InnoDB
  DEFAULT charset = utf8;

INSERT INTO user
VALUES ('1', 'admin', '123');
INSERT INTO user
VALUES ('2', 'demo', '123');

-- --
create table if not exists role
(
    rid   int(11)      not null auto_increment,
    rname varchar(255) not null default '',
    primary key (rid)
) ENGINE InnoDB
  DEFAULT charset = utf8;

INSERT INTO role
VALUES ('1', 'admin');
INSERT INTO role
VALUES ('2', 'customer');

-- --

create table permission_role
(
    rid int(11) not null,
    pid int(11) not null,
    key idx_rid (rid),
    key idx_pid (pid)
) ENGINE InnoDB
  DEFAULT charset = utf8;

INSERT INTO permission_role
VALUES ('1', '1');
INSERT INTO permission_role
VALUES ('1', '2');
INSERT INTO permission_role
VALUES ('1', '3');
INSERT INTO permission_role
VALUES ('1', '4');
INSERT INTO permission_role
VALUES ('2', '1');
INSERT INTO permission_role
VALUES ('2', '4');

-- --
create table user_role
(
    uid int(11) not null,
    rid int(11) not null,
    key idx_uid (uid),
    key idx_rid (rid)
) ENGINE InnoDB
  DEFAULT charset = utf8;

INSERT INTO user_role
VALUES (1, 1);
INSERT INTO user_role
VALUES (2, 2);


