--ユーザー生成SQL文
--CREATE ROLE spring
--	WITH
--	LOGIN
--	SUPERUSER
--	CREATEDB
--	CREATEROLE
--	INHERIT
--	NOREPLICATION
--	CONNECTION LIMIT -1
--	PASSWORD 'spring';
--
--データベース生成SQL文
--CREATE DATABASE spring
--	WITH
--	OWNER = spring;


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
CREATE SEQUENCE IF NOT EXISTS t_user_role_id_seq START WITH 8;

--ユーザー権限テーブル
CREATE TABLE IF NOT EXISTS t_user_role(
	id			INT
				NOT NULL
				DEFAULT nextval('t_user_role_id_seq')
				PRIMARY KEY,
	user_id		VARCHAR(20),
	role_id		VARCHAR(50)
);

--施設IDシーケンス
CREATE SEQUENCE IF NOT EXISTS m_facility_facility_id_seq START WITH 6;

--施設テーブル
CREATE TABLE IF NOT EXISTS m_facility(
	facility_id			INT
						NOT NULL
						DEFAULT nextval('m_facility_facility_id_seq')
						PRIMARY KEY,
	name				VARCHAR(20),
	capacity			INT,
	facility_type_id	INT,
	insert_date			TIMESTAMP,
	user_id				VARCHAR(20),
	update_date			TIMESTAMP,
	update_user_id		VARCHAR(20)
);

--施設種別IDシーケンス
CREATE SEQUENCE IF NOT EXISTS m_facility_type_facility_type_id_seq START WITH 4;

--施設種別テーブル
CREATE TABLE IF NOT EXISTS m_facility_type(
	facility_type_id	INT
						NOT NULL
						DEFAULT nextval('m_facility_type_facility_type_id_seq')
						PRIMARY KEY,
	name				VARCHAR(20),
	insert_date			TIMESTAMP,
	user_id				VARCHAR(20),
	update_date			TIMESTAMP,
	update_user_id		VARCHAR(20),
	delete_date			TIMESTAMP,
	delete_user_id		VARCHAR(20),
	fg_delete			INT
);

--施設種別IDシーケンス
CREATE SEQUENCE IF NOT EXISTS m_reservation_reservation_id_seq START WITH 13;

--予約テーブル
CREATE TABLE IF NOT EXISTS m_reservation(
	reservation_id	INT
					NOT NULL
					DEFAULT nextval('m_reservation_reservation_id_seq')
					PRIMARY KEY,
	start_time		TIMESTAMP	NOT NULL,
	end_time		TIMESTAMP	NOT NULL,
	facility_id		INT,
	insert_date		TIMESTAMP,
	user_id			VARCHAR(20),
	update_date		TIMESTAMP,
	update_user_id	VARCHAR(20),
	CHECK (start_time < end_time)
);