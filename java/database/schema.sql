ROLLBACK;
BEGIN TRANSACTION;

DROP TABLE IF EXISTS photo_feed;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS photo_feed;


CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
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

-- CREATE TABLE transfers (
-- 	transfer_id SERIAL PRIMARY KEY,
-- 	pending boolean DEFAULT true,
-- 	denied boolean DEFAULT false,
-- 	amount_transferred decimal(10,2) NOT NULL,
-- 	sender_id int NOT NULL,
-- 	receiver_id int NOT NULL,
-- 	date_time timestamp DEFAULT current_timestamp,
	
-- 	FOREIGN KEY (sender_id) REFERENCES accounts(account_id),
--     FOREIGN KEY (receiver_id) REFERENCES accounts(account_id)
-- );

COMMIT TRANSACTION;
