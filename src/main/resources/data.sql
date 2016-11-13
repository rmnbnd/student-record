-- authorities
INSERT INTO authority VALUES('ROLE_ADMIN');
INSERT INTO authority VALUES('ROLE_USER');

-- users
INSERT INTO user VALUES(1,'teacher','teacher');
INSERT INTO user VALUES(2,'student','student');

-- authorities
INSERT INTO user_authority VALUES(1, 'ROLE_ADMIN');
INSERT INTO user_authority VALUES(1, 'ROLE_USER');
INSERT INTO user_authority VALUES(2, 'ROLE_USER');

-- students
INSERT INTO student VALUES(1, 'Student 1', 2);

-- links
INSERT INTO link VALUES(1, 'https://www.youtube.com/embed/vwTo3UxJkiY');
INSERT INTO link VALUES(2, 'https://www.youtube.com/embed/vwTo3UxJkiY');
INSERT INTO link VALUES(3, 'https://www.youtube.com/embed/vwTo3UxJkiY');
INSERT INTO link VALUES(4, 'https://www.youtube.com/embed/vwTo3UxJkiY');

-- student links
INSERT INTO student_link VALUES(1, 1);
INSERT INTO student_link VALUES(1, 2);
INSERT INTO student_link VALUES(1, 3);
INSERT INTO student_link VALUES(1, 4);