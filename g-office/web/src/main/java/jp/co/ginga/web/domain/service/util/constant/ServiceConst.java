package jp.co.ginga.web.domain.service.util.constant;

/**
 * 
 * @author akizuki
 * 定数を扱うクラス
 *
 */
public class ServiceConst {

	/**
	 * 処理失敗
	 */
	public static final int ERROR = -1;

	/**
	 * 楽観的ロックによる失敗
	 */
	public static final int OPTIMISTIC_ROCK_ERROR = -2;

	/**
	 * 処理成功
	 */
	public static final int OK = 0;

	/**
	 * データあり
	 */
	public static final int THERE_IS_DATA = 1;

	/**
	 * データなし
	 */
	public static final int NO_DATA = 2;
}
