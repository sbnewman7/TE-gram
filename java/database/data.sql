ROLLBACK;
BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,email,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','email1@gmail.com','ROLE_USER');
INSERT INTO users (username,password_hash,email,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','email2@gmail.com','ROLE_ADMIN');

INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (1, 'rainy days are the worst', 'https://images.pexels.com/photos/4194850/pexels-photo-4194850.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (1, 'Feeling a little goofy today!', 'https://img.freepik.com/free-photo/portrait-hesitant-man-purses-lips-looks-bewilderment-feels-doubt_273609-16785.jpg');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (2, 'Eating a healthy breakfast is good for you!', 'https://penntoday.upenn.edu/sites/default/files/2018-07/istock-476953638.jpg');

INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (1, 'I had to get my nokia out for this photo', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/220px-Image_created_with_a_mobile_phone.png');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (2, 'Just taking some photos during the sunset!', 'https://www.recordnet.com/gcdn/presto/2021/03/22/NRCD/9d9dd9e4-e84a-402e-ba8f-daa659e6e6c5-PhotoWord_003.JPG?width=660&height=425&fit=crop&format=pjpg&auto=webp');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (2, 'Worked on my group project today, got a lot done!', 'https://media.istockphoto.com/id/1750478284/photo/diverse-students-making-banners-for-climate-change-protest.jpg?s=2048x2048&w=is&k=20&c=2vI5m9IFYOhdsAQsI8iT7sPoGTXH8TuWWzPiRTv1aHA=');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (1, 'Surfing is a great stress reliever', 'https://media.istockphoto.com/id/1665614059/photo/paddle-board-entering-the-water-with-the-board-in-hand.jpg?s=2048x2048&w=is&k=20&c=944HSaTRZhusK9WT44dZpgAp5ew2CEgmWKWusESqPPQ=');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (1, 'A day at the beach!', 'https://i0.wp.com/picjumbo.com/wp-content/uploads/woman-enjoying-the-sunset-by-the-sea-free-photo.jpg?w=600&quality=80');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (1, 'The family went camping this weekend', 'https://www.wildnatureimages.com/images/xl/070620-014-The-Tetons.jpg');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (2, 'banana.', 'https://images.unsplash.com/photo-1481349518771-20055b2a7b24?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8cmFuZG9tfGVufDB8fDB8fHww');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (2, 'you cant come in unless you know the password!', 'https://www.thedesignwork.com/wp-content/uploads/2011/10/Random-Pictures-of-Conceptual-and-Creative-Ideas-02.jpg');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (1, 'brethren, we revolt at dawn!', 'https://i.redd.it/jeuusd992wd41.jpg');
INSERT INTO photo_feed (user_id, caption, pic_url) VALUES (1, 'Zoom!!!', 'https://media.istockphoto.com/id/157402340/photo/left-behind.jpg?s=612x612&w=0&k=20&c=ttF1xIAwRdL2v0UoL6Y_xdbpSQJzdVIokVdBBrM2I3Q=');
INSERT INTO comments (user_id, photo_id, comment_text) VALUES (1, 4, 'Wow! What beautiful scenery!');
INSERT INTO comments (user_id, photo_id, comment_text) VALUES (1, 5, 'That silhouette against the sunset looks so amazing... :)');
INSERT INTO comments (user_id, photo_id, comment_text) VALUES (1, 6, 'I had so much fun working on this project with you!');
INSERT INTO comments (user_id, photo_id, comment_text) VALUES (1, 9, 'Wish I was there!');
INSERT INTO comments (user_id, photo_id, comment_text) VALUES (1, 1, 'Omg they totally are...');
INSERT INTO comments (user_id, photo_id, comment_text) VALUES (1, 2, 'Arent we all?');
INSERT INTO comments (user_id, photo_id, comment_text) VALUES (1, 3, 'Save some for me!');
INSERT INTO comments (user_id, photo_id, comment_text) VALUES (2, 4, 'Take me with you on your next road trip!');
INSERT INTO comments (user_id, photo_id, comment_text) VALUES (2, 5, 'Sunsets are my faaaavooooriiiiite XD');
INSERT INTO comments (user_id, photo_id, comment_text) VALUES (1, 10, 'banana.');
INSERT INTO comments (user_id, photo_id, comment_text) VALUES (2, 10, 'banana.');
INSERT INTO comments (user_id, photo_id, comment_text) VALUES (1, 11, 'Is the password "girls have cooties"?');
INSERT INTO comments (user_id, photo_id, comment_text) VALUES (1, 12, 'ARISE!!!!');
INSERT INTO comments (user_id, photo_id, comment_text) VALUES (1, 13, 'I want to be that turtle so bad');




COMMIT TRANSACTION;
