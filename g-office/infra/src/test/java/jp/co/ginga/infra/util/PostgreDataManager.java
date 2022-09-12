package jp.co.ginga.infra.util;

import java.io.File;
import java.sql.Date;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

public class PostgreDataManager {


	//test
	/**
	 * DB接続情報
	 */
	private static final String JDBC_DRIVER = org.postgresql.Driver.class.getName();
	private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/spring_test";
	private static final String USER = "spring";
	private static final String PASSWORD = "spring";

	/** DBUnitのテスター */
	private static IDatabaseTester databaseTester;

	public static void dataSet(String fileName,DatabaseOperation ope) throws Exception {

		databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File(fileName));

		ReplacementDataSet repDataSet = PostgreDataManager.replaceDataset(dataSet);

		databaseTester.setDataSet(repDataSet);

		// DELETE→INSERTで事前準備データを用意する
		databaseTester.setSetUpOperation(ope);

		databaseTester.onSetup();
	}

	private static ReplacementDataSet replaceDataset(IDataSet dataSet) {
		ReplacementDataSet repDataSet = new ReplacementDataSet(dataSet);

		//現在日時から加減をセット
		repDataSet.addReplacementObject("[SYSDATE]", new Date(System.currentTimeMillis()));
		repDataSet.addReplacementObject("[SYSDATE+1]", new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));
		repDataSet.addReplacementObject("[SYSDATE+2]", new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 48));

		return repDataSet;
	}

}
