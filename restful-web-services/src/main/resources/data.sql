INSERT INTO user_details(id, birth_date, name) VALUES (10001, current_date(), 'Karan');

INSERT INTO user_details(id, birth_date, name) VALUES (10002, current_date(), 'Ravi');

INSERT INTO user_details(id, birth_date, name) VALUES (10003, current_date(), 'Sathish');

INSERT INTO user_details(id, birth_date, name) VALUES (10004, current_date(), 'Ranga');

INSERT INTO post(id, user_id, description) VALUES (10001, 10001, 'Learn AWS');
INSERT INTO post(id, user_id, description) VALUES (10002, 10001, 'Learn DevOps');

INSERT INTO post(id, user_id, description) VALUES (10003, 10002, 'Learn AWS');
INSERT INTO post(id, user_id, description) VALUES (10004, 10002, 'Learn DevOps');

INSERT INTO post(id, user_id, description) VALUES (10005, 10003, 'Learn AWS');
INSERT INTO post(id, user_id, description) VALUES (10006, 10003, 'Learn DevOps');