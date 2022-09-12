--ユーザーデータ
INSERT INTO m_user (user_id, user_name, password, pass_update_date, gender
	, birthday, contact, mail_address, login_miss_times, unlock, tenant_id, enabled, user_due_date, insert_date)
VALUES
	('system', 'システム管理者', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '2099-12-31 23:59:59', '男'
	, '2099-12-31', '090-1234-5678', 'system@xxx.co.jp', 0, true, 'tenant', true, '2099-12-31 23:59:59', '2021-04-01 12:00:00')
ON CONFLICT (user_id) DO NOTHING;

INSERT INTO m_user (user_id, user_name, password, pass_update_date, gender
	, birthday, contact, mail_address, login_miss_times, unlock, tenant_id, enabled, user_due_date, insert_date)
VALUES
	('user', '一般ユーザー', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '2099-12-31 23:59:59','男'
	,'2099-12-31','090-1234-5678', 'user@xxx.co.jp', 0, true, 'tenant', true, '2099-12-31 23:59:59', '2021-04-01 13:00:00')
ON CONFLICT (user_id) DO NOTHING;

INSERT INTO m_user (user_id, user_name, password, pass_update_date, gender
	, birthday, contact, mail_address, login_miss_times, unlock, tenant_id, enabled, user_due_date, insert_date)
VALUES
	('sample1', 'ユーザー1', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '1999-12-31 23:59:59','女'
	,'2099-12-31','090-1234-5678', 'user1@xxx.co.jp', 0, true, 'tenant', true, '2099-12-31 23:59:59', '2021-04-01 14:00:00')
ON CONFLICT (user_id) DO NOTHING;

INSERT INTO m_user (user_id, user_name, password, pass_update_date, gender
	, birthday, contact, mail_address, login_miss_times, unlock, tenant_id, enabled, user_due_date, insert_date)
VALUES
	('sample2', 'ユーザー2', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '2099-12-31 23:59:59','女'
	,'2099-12-31','090-1234-5678', 'user2@xxx.co.jp', 0, false, 'tenant', true, '2099-12-31 23:59:59', '2021-04-01 15:00:00')
ON CONFLICT (user_id) DO NOTHING;

INSERT INTO m_user (user_id, user_name, password, pass_update_date, gender
	, birthday, contact, mail_address, login_miss_times, unlock, tenant_id, enabled, user_due_date, insert_date)
VALUES
	('sample3', 'ユーザー3', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '2099-12-31 23:59:59','どちらでもない'
	,'2099-12-31','090-1234-5678', 'user3@xxx.co.jp', 0, true, 'tenant', false, '2099-12-31 23:59:59', '2021-04-01 16:00:00')
ON CONFLICT (user_id) DO NOTHING;

INSERT INTO m_user (user_id, user_name, password, pass_update_date, gender
	, birthday, contact, mail_address, login_miss_times, unlock, tenant_id, enabled, user_due_date, insert_date)
VALUES
	('sample4', 'ユーザー4', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '2099-12-31 23:59:59','どちらでもない'
	,'2099-12-31','090-1234-5678', 'user4@xxx.co.jp', 0, true, 'tenant', true, '1999-12-31 23:59:59', '2021-04-01 17:00:00')
ON CONFLICT (user_id) DO NOTHING;


--権限種別データ
INSERT INTO m_role (role_id, role_name)
	VALUES ('admin', 'ROLE_ADMIN') ON CONFLICT (role_id) DO NOTHING;
INSERT INTO m_role (role_id, role_name)
	VALUES ('general', 'ROLE_GENERAL') ON CONFLICT (role_id) DO NOTHING;


--ユーザー権限データ
INSERT INTO t_user_role (id, user_id, role_id)
	VALUES (1, 'system', 'admin') ON CONFLICT (id) DO NOTHING;
INSERT INTO t_user_role (id, user_id, role_id)
	VALUES (2, 'sample1', 'general') ON CONFLICT (id) DO NOTHING;
INSERT INTO t_user_role (id, user_id, role_id)
	VALUES (3, 'sample2', 'general') ON CONFLICT (id) DO NOTHING;
INSERT INTO t_user_role (id, user_id, role_id)
	VALUES (4, 'sample3', 'general') ON CONFLICT (id) DO NOTHING;
INSERT INTO t_user_role (id, user_id, role_id)
	VALUES (5, 'sample4', 'general') ON CONFLICT (id) DO NOTHING;
INSERT INTO t_user_role (id, user_id, role_id)
	VALUES (6, 'user', 'general') ON CONFLICT (id) DO NOTHING;
INSERT INTO t_user_role (id, user_id, role_id)
	VALUES (7, 'system', 'general') ON CONFLICT (id) DO NOTHING;

--施設データ
INSERT INTO m_facility (facility_id, name, capacity, facility_type_id, insert_date, user_id)
	VALUES (1, '会議室A', '10', '1', '2010-12-01 12:12:12', 'sample1') ON CONFLICT (facility_id) DO NOTHING;
INSERT INTO m_facility (facility_id, name, capacity, facility_type_id, insert_date, user_id)
	VALUES (2, '会議室B', '20', '1', '2011-12-02 12:12:12', 'sample1') ON CONFLICT (facility_id) DO NOTHING;
INSERT INTO m_facility (facility_id, name, capacity, facility_type_id, insert_date, user_id)
	VALUES (3, '応接室A', '30', '2', '2012-12-03 12:12:12', 'sample2') ON CONFLICT (facility_id) DO NOTHING;
INSERT INTO m_facility (facility_id, name, capacity, facility_type_id, insert_date, user_id)
	VALUES (4, '応接室B', '40', '2', '2013-12-04 12:12:12', 'sample2') ON CONFLICT (facility_id) DO NOTHING;
INSERT INTO m_facility (facility_id, name, capacity, facility_type_id, insert_date, user_id)
	VALUES (5, '保養施設A', '50', '3', '2014-12-05 12:12:12', 'sample3') ON CONFLICT (facility_id) DO NOTHING;


--施設種別データ
INSERT INTO m_facility_type (facility_type_id, name, insert_date, user_id, fg_delete)
	VALUES (1, '会議室', '2021-04-01 12:12:12', 'sample1', 0) ON CONFLICT (facility_type_id) DO NOTHING ;
INSERT INTO m_facility_type (facility_type_id, name, insert_date, user_id, fg_delete)
	VALUES (2, '応接室', '2021-04-02 12:12:12', 'sample2', 0) ON CONFLICT (facility_type_id) DO NOTHING ;
INSERT INTO m_facility_type (facility_type_id, name, insert_date, user_id, fg_delete)
	VALUES (3, '保養施設', '2021-04-03 12:12:12', 'sample3', 0) ON CONFLICT (facility_type_id) DO NOTHING ;


--予約データ
INSERT INTO m_reservation (reservation_id, start_time, end_time, facility_id, user_id, insert_date)
	VALUES (1, '2021-04-05 09:00', '2021-04-05 12:00', 2, 'sample1', '2021-04-01 12:00:00')
		ON CONFLICT (reservation_id) DO NOTHING;
INSERT INTO m_reservation (reservation_id, start_time, end_time, facility_id, user_id, insert_date)
	VALUES (2, '2021-04-05 10:00', '2021-04-05 12:00', 1, 'sample3', '2021-04-01 12:00:00')
		ON CONFLICT (reservation_id) DO NOTHING;
INSERT INTO m_reservation (reservation_id, start_time, end_time, facility_id, user_id, insert_date)
	VALUES (3, '2021-04-06 14:00', '2021-04-06 21:00', 4, 'sample2', '2021-04-01 12:00:00')
		ON CONFLICT (reservation_id) DO NOTHING;
INSERT INTO m_reservation (reservation_id, start_time, end_time, facility_id, user_id, insert_date)
	VALUES (4, '2021-04-07 12:00', '2021-04-07 13:00', 1, 'sample3', '2021-04-01 12:00:00')
		ON CONFLICT (reservation_id) DO NOTHING;
INSERT INTO m_reservation (reservation_id, start_time, end_time, facility_id, user_id, insert_date)
	VALUES (5, '2021-07-03 13:00', '2021-07-03 18:00', 3, 'sample1', '2021-04-01 12:00:00')
		ON CONFLICT (reservation_id) DO NOTHING;
INSERT INTO m_reservation (reservation_id, start_time, end_time, facility_id, user_id, insert_date)
	VALUES (6, '2021-09-12 00:00', '2021-09-12 23:59', 6, 'sample2', '2021-04-01 12:00:00')
		ON CONFLICT (reservation_id) DO NOTHING;
INSERT INTO m_reservation (reservation_id, start_time, end_time, facility_id, user_id, insert_date)
	VALUES (7, '2021-10-01 13:00', '2021-10-01 15:00', 1, 'sample1', '2021-04-01 12:00:00')
		ON CONFLICT (reservation_id) DO NOTHING;
INSERT INTO m_reservation (reservation_id, start_time, end_time, facility_id, user_id, insert_date)
	VALUES (8, '2021-10-02 12:00', '2021-10-02 15:00', 1, 'sample2', '2021-04-01 12:00:00')
		ON CONFLICT (reservation_id) DO NOTHING;
INSERT INTO m_reservation (reservation_id, start_time, end_time, facility_id, user_id, insert_date)
	VALUES (9, '2021-02-14 12:00', '2021-02-14 16:00', 1, 'sample3', '2021-04-01 12:00:00')
		ON CONFLICT (reservation_id) DO NOTHING;
INSERT INTO m_reservation (reservation_id, start_time, end_time, facility_id, user_id, insert_date)
	VALUES (10, '2021-05-05 12:00', '2021-05-05 13:00', 3, 'sample3', '2021-04-01 12:00:00')
		ON CONFLICT (reservation_id) DO NOTHING;
INSERT INTO m_reservation (reservation_id, start_time, end_time, facility_id, user_id, insert_date)
	VALUES (11, '2021-08-27 09:00', '2021-08-31 12:00', 5, 'sample4', '2021-04-01 12:00:00')
		ON CONFLICT (reservation_id) DO NOTHING;
INSERT INTO m_reservation (reservation_id, start_time, end_time, facility_id, user_id, insert_date)
	VALUES (12, '2021-04-05 13:00', '2021-04-05 14:00', 1, 'sample3', '2021-04-01 12:00:00')
		ON CONFLICT (reservation_id) DO NOTHING;

