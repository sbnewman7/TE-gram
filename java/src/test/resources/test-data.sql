BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,email,profile_pic_url,role) VALUES ('user1','user1', 'userEmail', '','ROLE_USER');
INSERT INTO users (username,password_hash,email,profile_pic_url,role) VALUES ('user2','user2', 'userEmail2', '','ROLE_USER');
INSERT INTO users (username,password_hash,email,profile_pic_url,role) VALUES ('user3','user3', 'userEmail3', '','ROLE_USER');

COMMIT TRANSACTION;
