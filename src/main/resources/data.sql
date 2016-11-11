-- authorities
INSERT INTO authority VALUES('ROLE_ADMIN');
INSERT INTO authority VALUES('ROLE_USER');

-- users
INSERT INTO user VALUES(1,'admin','admin');

-- authorities
INSERT INTO user_authority VALUES(1, 'ROLE_ADMIN');
INSERT INTO user_authority VALUES(1, 'ROLE_USER');
