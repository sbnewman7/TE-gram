ROLLBACK;
BEGIN TRANSACTION;

DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS photo_likes;
DROP TABLE IF EXISTS photo_feed;
DROP TABLE IF EXISTS users;



CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	email varchar(100) NOT NULL,
	profile_pic_url VARCHAR(300) NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE photo_feed (
	photo_id SERIAL PRIMARY KEY,
	user_id INT  NOT NULL,
	date_time timestamp DEFAULT current_timestamp,
	caption varchar(50) NOT NULL,
	pic_url varchar(300) NOT NULL,
	-- private varchar(10) DEFAULT 'public'
	
	FOREIGN KEY (user_id) REFERENCES users(user_id)
	
);

CREATE TABLE photo_likes (
    photo_id int NOT NULL,
    user_id int NOT NULL,

    FOREIGN KEY (photo_id) REFERENCES photo_feed(photo_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE comments (
	comment_id serial PRIMARY KEY, 
	user_id int NOT NULL,
	photo_id int NOT NULL,
	date_time timestamp DEFAULT current_timestamp,
	comment_text varchar(200),
	
	FOREIGN KEY (user_id) REFERENCES users(user_id),
	FOREIGN KEY (photo_id) REFERENCES photo_feed(photo_id)
);
	
	


COMMIT TRANSACTION;
