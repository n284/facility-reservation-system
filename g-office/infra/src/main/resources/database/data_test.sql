--ユーザーデータ
--INSERT INTO m_user (user_id, user_name, password, pass_update_date, gender
--	, birthday, contact, mail_address, login_miss_times, unlock, tenant_id, enabled, user_due_date, insert_date)
--VALUES
--	('system', 'システム管理者', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '2099-12-31 23:59:59', '女'
--	, '2099-12-31', '090-1234-5678', 'system@xxx.co.jp', 0, true, 'tenant', true, '2099-12-31 23:59:59', '2021-04-01 12:00:00')
--ON CONFLICT (user_id) DO NOTHING;
-- AfterEachで破棄されないデータ（updateのキー重複テスト用）
INSERT INTO m_user (user_id, user_name, password, pass_update_date, gender
	, birthday, contact, mail_address, login_miss_times, unlock, tenant_id, enabled, user_due_date, insert_date)
VALUES
	('user', '一般ユーザー', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '2099-12-31 23:59:59', '男'
	, '2099-12-31', '090-1234-5678', 'system@xxx.co.jp', 0, true, 'tenant', true, '2099-12-31 23:59:59', '2021-04-01 12:00:00')
ON CONFLICT (user_id) DO NOTHING;



--権限種別データ
INSERT INTO m_role (role_id, role_name)
	VALUES ('admin', 'ROLE_ADMIN') ON CONFLICT (role_id) DO NOTHING;
INSERT INTO m_role (role_id, role_name)
	VALUES ('general', 'ROLE_GENERAL') ON CONFLICT (role_id) DO NOTHING;



--ユーザー権限データ
--INSERT INTO t_user_role (id, user_id, role_id)
--	VALUES (1, 'system', 'admin') ON CONFLICT (id) DO NOTHING;
-- AfterEachで破棄されないデータ
INSERT INTO t_user_role (id, user_id, role_id)
	VALUES (1, 'user', 'general') ON CONFLICT (id) DO NOTHING;



--施設データ
--INSERT INTO m_facility (facility_id, name, capacity, facility_type_id, insert_date, user_id)
--	VALUES (1, '会議室A', '10', '1', '2010-12-01 12:12:12', 'sample1') ON CONFLICT (facility_id) DO NOTHING;
--INSERT INTO m_facility (facility_id, name, capacity, facility_type_id, insert_date, user_id)
--	VALUES (3, '応接室A', '30', '2', '2012-12-03 12:12:12', 'sample2') ON CONFLICT (facility_id) DO NOTHING;
--INSERT INTO m_facility (facility_id, name, capacity, facility_type_id, insert_date, user_id)
--	VALUES (5, '保養施設A', '50', '3', '2014-12-05 12:12:12', 'sample3') ON CONFLICT (facility_id) DO NOTHING;



--施設種別データ
INSERT INTO m_facility_type (facility_type_id, name, insert_date, user_id, fg_delete)
	VALUES (1, '会議室', '2021-04-01 12:12:12', 'sample1', 0) ON CONFLICT (facility_type_id) DO NOTHING ;
INSERT INTO m_facility_type (facility_type_id, name, insert_date, user_id, fg_delete)
	VALUES (2, '応接室', '2021-04-02 12:12:12', 'sample2', 0) ON CONFLICT (facility_type_id) DO NOTHING ;
INSERT INTO m_facility_type (facility_type_id, name, insert_date, user_id, fg_delete)
	VALUES (3, '保養施設', '2021-04-03 12:12:12', 'sample3', 0) ON CONFLICT (facility_type_id) DO NOTHING ;
