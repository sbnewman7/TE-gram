BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,email,profile_pic_url,role) VALUES ('user1','user1', 'userEmail', '','ROLE_USER');
INSERT INTO users (username,password_hash,email,profile_pic_url,role) VALUES ('user2','user2', 'userEmail2', '','ROLE_USER');
INSERT INTO users (username,password_hash,email,profile_pic_url,role) VALUES ('user3','user3', 'userEmail3', '','ROLE_USER');

INSERT INTO photo_feed (user_id, date_time, caption, pic_url) VALUES (1,'2023-12-07 12:00:00','caption1','url1');
INSERT INTO photo_likes (user_id, photo_id) VALUES (1,1);
INSERT INTO photo_favorites (user_id, photo_id) VALUES (1,1);

INSERT INTO comments (user_id, photo_id, date_time, comment_text)
            	VALUES (1, 1, '2023-01-01 12:00:00', 'sample');

COMMIT TRANSACTION;
