ROLLBACK;
BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (1, 'caption here', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/220px-Image_created_with_a_mobile_phone.png');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (2, 'caption here', 'https://www.recordnet.com/gcdn/presto/2021/03/22/NRCD/9d9dd9e4-e84a-402e-ba8f-daa659e6e6c5-PhotoWord_003.JPG?width=660&height=425&fit=crop&format=pjpg&auto=webp');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (2, 'caption here', 'https://media.istockphoto.com/id/1750478284/photo/diverse-students-making-banners-for-climate-change-protest.jpg?s=2048x2048&w=is&k=20&c=2vI5m9IFYOhdsAQsI8iT7sPoGTXH8TuWWzPiRTv1aHA=');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (1, 'caption here', 'https://media.istockphoto.com/id/1665614059/photo/paddle-board-entering-the-water-with-the-board-in-hand.jpg?s=2048x2048&w=is&k=20&c=944HSaTRZhusK9WT44dZpgAp5ew2CEgmWKWusESqPPQ=');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (1, 'caption here', 'https://i0.wp.com/picjumbo.com/wp-content/uploads/woman-enjoying-the-sunset-by-the-sea-free-photo.jpg?w=600&quality=80');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (1, 'caption here', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/220px-Image_created_with_a_mobile_phone.png');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (2, 'caption here', 'https://www.recordnet.com/gcdn/presto/2021/03/22/NRCD/9d9dd9e4-e84a-402e-ba8f-daa659e6e6c5-PhotoWord_003.JPG?width=660&height=425&fit=crop&format=pjpg&auto=webp');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (2, 'caption here', 'https://media.istockphoto.com/id/1750478284/photo/diverse-students-making-banners-for-climate-change-protest.jpg?s=2048x2048&w=is&k=20&c=2vI5m9IFYOhdsAQsI8iT7sPoGTXH8TuWWzPiRTv1aHA=');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (1, 'caption here', 'https://media.istockphoto.com/id/1665614059/photo/paddle-board-entering-the-water-with-the-board-in-hand.jpg?s=2048x2048&w=is&k=20&c=944HSaTRZhusK9WT44dZpgAp5ew2CEgmWKWusESqPPQ=');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (1, 'caption here', 'https://i0.wp.com/picjumbo.com/wp-content/uploads/woman-enjoying-the-sunset-by-the-sea-free-photo.jpg?w=600&quality=80');



COMMIT TRANSACTION;
