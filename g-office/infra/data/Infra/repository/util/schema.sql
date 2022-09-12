--テスト用SQL文

--ユーザーテーブル
CREATE TABLE IF NOT EXISTS m_user(
	user_id				VARCHAR(20)	NOT NULL	PRIMARY KEY,
	user_name			VARCHAR(20),
	password			VARCHAR(255),
	pass_update_date	TIMESTAMP,
	gender				VARCHAR(50),
	birthday			VARCHAR(50),
	contact				VARCHAR(50),
	mail_address		VARCHAR(50),
	login_miss_times	INT,
	unlock				boolean,
	tenant_id			VARCHAR(50),
	enabled				BOOLEAN,
	user_due_date		TIMESTAMP,
	insert_date			TIMESTAMP,
	update_date			TIMESTAMP
);

--権限種別テーブル
CREATE TABLE IF NOT EXISTS m_role(
	role_id		VARCHAR(50) PRIMARY KEY,
	role_name	VARCHAR(50)
);

--ユーザー権限IDシーケンス
CREATE SEQUENCE IF NOT EXISTS t_user_role_id_seq START WITH 7;

--ユーザー権限テーブル
CREATE TABLE IF NOT EXISTS t_user_role(
	id			INT
				NOT NULL
				DEFAULT nextval('t_user_role_id_seq')
				PRIMARY KEY,
	user_id		VARCHAR(20),
	role_id		VARCHAR(50)
);