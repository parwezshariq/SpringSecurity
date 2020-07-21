DROP TABLE IF EXISTS USERS ;
DROP TABLE IF EXISTS TRANSACTIONS;
DROP TABLE IF EXISTS AUTHORITIES;

create table IF NOT EXISTS users(
	username varchar_ignorecase(50) not null primary key,
	password varchar_ignorecase(50) not null,
	email varchar(100) not null,
	enabled boolean not null
);

create table IF NOT EXISTS authorities (
	username varchar_ignorecase(50) not null,
	authority varchar_ignorecase(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index IF NOT EXISTS ix_auth_username on authorities (username,authority);
CREATE TABLE IF NOT EXISTS TRANSACTIONS (ID INT PRIMARY KEY, USERNAME VARCHAR(30), COIN VARCHAR(3),TYPE VARCHAR(5), QUANTITY DECIMAL, PRICE DECIMAL );
