-- b2

CREATE TABLE roles(
    id         INTEGER NOT NULL PRIMARY KEY,
	name_roles VARCHAR(10) NOT NULL UNIQUE        
);

INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'client');

CREATE TABLE accesses_users(
    id   INTEGER NOT NULL PRIMARY KEY,
	name_accesses_users VARCHAR(10) NOT NULL UNIQUE 
);

INSERT INTO accesses_users VALUES(0, 'unlocked');
INSERT INTO accesses_users VALUES(1, 'blocked');


-- Пользователи --
CREATE TABLE users (
    id                INTEGER NOT NULL PRIMARY KEY auto_increment,
    login             VARCHAR(10) NOT NULL UNIQUE,
    password          VARCHAR(10) NOT NULL,
    first_name        VARCHAR(20) NOT NULL,
    last_name         VARCHAR(20) NOT NULL,
    first_name_ru     VARCHAR(20) NOT NULL,
    last_name_ru      VARCHAR(20) NOT NULL,
	role_id           INTEGER NOT NULL REFERENCES roles (id)
                      ON DELETE CASCADE ON UPDATE CASCADE,
                      -- ON DELETE CASCADE ON UPDATE RESTRICT;
	accesses_users_id INTEGER NOT NULL REFERENCES accesses_users (id)
                      ON DELETE CASCADE ON UPDATE CASCADE
);

-- id = 1
INSERT INTO users VALUES(DEFAULT, 'admin', 'admin', 'Arthur', 'Arthurov', 'Артур', 'Артуров', 0, 0);
-- id = 2
INSERT INTO users VALUES(DEFAULT, 'client', 'client', 'Vasya', 'Vasilev', 'Вася', 'Васильев', 1, 0);
-- id = 3
INSERT INTO users VALUES(DEFAULT, 'petrov', 'petrov', 'Petr', 'Petrov', 'Петр', 'Петров', 1, 0);
-- id = 4
INSERT INTO users VALUES(DEFAULT, 'pupkin', 'pupkin', 'Vasya', 'Pupkin', 'Вася', 'Пупкин', 1, 1);
-- id = 5
INSERT INTO users VALUES(DEFAULT, 'ivanov', 'ivanov', 'Ivan', 'Ivanov', 'Иван', 'Иванов' , 1, 0);


-- --------------------------------------------------------------------------------------------- --
-- ТАБЛИЦЫ (КРЕДИТНЫЕ КАРТЫ)
-- --------------------------------------------------------------------------------------------- --

-- Доступ к кредитной карте --
CREATE TABLE accesses_accounts(
    id                     INTEGER NOT NULL PRIMARY KEY,
	name_accesses_accounts VARCHAR(10) NOT NULL UNIQUE               
);

INSERT INTO accesses_accounts VALUES(0, 'unlocked');
INSERT INTO accesses_accounts VALUES(1, 'blocked');

-- Запрос на разблокировку кредитной карты --                          
CREATE TABLE unlock_request(
    id                       INTEGER NOT NULL PRIMARY KEY,
	name_unlock_request      VARCHAR(20) NOT NULL UNIQUE               -- ! 
);

INSERT INTO unlock_request VALUES(0, 'doesnotexist');
INSERT INTO unlock_request VALUES(1, 'exists');

-- Кредитные карты --
CREATE TABLE credit_account (
    id                   INTEGER NOT NULL PRIMARY KEY auto_increment,       -- 01
    numbers              INTEGER NOT NULL,                                  -- 02
	name                 VARCHAR(20) NOT NULL,                              -- 03
	balance              INTEGER NOT NULL,                                  -- 04
	accesses_accounts_id INTEGER NOT NULL REFERENCES accesses_accounts (id) -- 05
	                     ON DELETE CASCADE ON UPDATE CASCADE,
	user_id              INTEGER NOT NULL REFERENCES users (id)             -- 06
	                     ON DELETE CASCADE ON UPDATE CASCADE,
	unlock_request_id	 INTEGER NOT NULL REFERENCES lock_request (id)      -- 07    (-- !) 
	                     ON DELETE CASCADE ON UPDATE CASCADE			 
);

-- id = 1
--                                01       02  03         04    05  06  07 
INSERT INTO credit_account VALUES(DEFAULT,  1, 'card-01', 1000,  0,  2,  0);
-- id = 2
INSERT INTO credit_account VALUES(DEFAULT,  2, 'card-02', 10000, 0,  2,  0);
-- id = 3
INSERT INTO credit_account VALUES(DEFAULT,  3, 'card-07', 20000, 1,  2,  0);
-- id = 4
INSERT INTO credit_account VALUES(DEFAULT,  4, 'card-08', 5000,  1,  2,  1);
-- id = 5
INSERT INTO credit_account VALUES(DEFAULT,  5, 'card-05', 70000, 1,  2,  1);
-- id = 6
INSERT INTO credit_account VALUES(DEFAULT, 35, 'card-01', 10, 0, 3, 0);
-- id = 7
INSERT INTO credit_account VALUES(DEFAULT, 77, 'card-02', 1000, 1, 3, 1);
-- id = 8
INSERT INTO credit_account VALUES(DEFAULT, 1, 'card-01-pupkin', 10000, 0, 4, 0);
-- id = 9
INSERT INTO credit_account VALUES(DEFAULT, 2, 'card-02-pupkin', 1000, 1, 4, 1);
-- id = 10
INSERT INTO credit_account VALUES(DEFAULT, 1, 'card', 10000, 1, 5, 0);

-- --------------------------------------------------------------------------------------------- --

CREATE TABLE status_pay(
    id              INTEGER NOT NULL PRIMARY KEY,
	name_status_pay VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO status_pay VALUES(0, 'prepared');
INSERT INTO status_pay VALUES(1, 'sent');

-- Платежки --
CREATE TABLE pays (
    id                   INTEGER NOT NULL PRIMARY KEY auto_increment,
    numbers              INTEGER NOT NULL,
	-- datas                VARCHAR(25) NOT NULL,
	datas                DATE NOT NULL,
	sums                 INTEGER NOT NULL,
	user_id              INTEGER NOT NULL REFERENCES users (id)
	                     ON DELETE CASCADE ON UPDATE CASCADE,
	credit_account_id    INTEGER NOT NULL REFERENCES credit_account (id)
	                     ON DELETE CASCADE ON UPDATE CASCADE,
	status_pay_id 		 INTEGER NOT NULL REFERENCES status_pay (id)
	                     ON DELETE CASCADE ON UPDATE CASCADE
);

-- id = 1
INSERT INTO pays VALUES(DEFAULT, 1, '2017-01-01', 10, 2, 1, 0);

-- id = 2
INSERT INTO pays VALUES(DEFAULT, 2, '2017-02-02', 20, 2, 2, 0);
INSERT INTO pays VALUES(DEFAULT, 3, '2017-02-01', 25, 2, 2, 0);
INSERT INTO pays VALUES(DEFAULT, 4, '2017-02-02', 27, 2, 2, 0);
INSERT INTO pays VALUES(DEFAULT, 5, '2017-02-07', 55, 2, 2, 0);
INSERT INTO pays VALUES(DEFAULT, 7, '2017-02-08', 5,  2, 2, 1);


-- id = 3
INSERT INTO pays VALUES(DEFAULT, 3, '2017-03-05', 30, 3, 4, 0);
-- id = 4
INSERT INTO pays VALUES(DEFAULT, 4, '2017-04-07', 40, 4, 5, 1);
-- id = 5
INSERT INTO pays VALUES(DEFAULT, 5, '2017-05-08', 50, 4, 5, 0);
-- id = 6
INSERT INTO pays VALUES(DEFAULT, 8, '2017-08-10', 60, 5, 7, 1);
-- id = 7
INSERT INTO pays VALUES(DEFAULT, 7, '2017-07-17', 70, 5, 7, 0);
