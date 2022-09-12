package jp.co.ginga.web.application.validation;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class ValidationItem {
	/**
	 * null判定
	 * 
	 * @param obj
	 * @return
	 */
	public boolean isNull(Object obj) {
		return obj == null;
	}

	/**
	 * 文字列が空かチェック
	 * 
	 * @param value
	 * @return
	 */
	public boolean isEmpty(String value) {
		return value.isEmpty();
	}

	/**
	 * パターンと一致するかチェック
	 * 
	 * @param value
	 * @param regex
	 * @return
	 */
	public boolean matchPattern(String value, String regex) {
		return value.matches(regex);
	}

	/**
	 * 空白のみかチェック
	 * 
	 * @param value
	 * @return
	 */
	public boolean isBlank(String value) {
		return matchPattern(value, "^[ 　\\t]+$");
	}

	/**
	 * 先頭が空文字かチェック
	 * 
	 * @param value
	 * @return
	 */
	public boolean isFirstSpace(String value) {
		return value.charAt(0) == ' ' || value.charAt(0) == '　';
	}

	public boolean isFirstSpace2(String value) {
		return value.charAt(0) == '0';
	}

	/**
	 * 末尾が空文字かチェック
	 * 
	 * @param value
	 * @return
	 */
	public boolean isLastSpace(String value) {
		return value.charAt(value.length() - 1) == ' ' || value.charAt(value.length() - 1) == '　';
	}

	/**
	 * ASCII文字に分類される半角英数字記号かチェック
	 * 
	 * @param value
	 * @return
	 */
	public boolean isAscii(String value) {
		return matchPattern(value, "^\\p{ASCII}+$");
	}

	/**
	 * 半角英数字のみかチェック
	 * 
	 * @param value
	 * @return
	 */
	public boolean isAlphaDigit(String value) {
		return matchPattern(value, "^\\p{Alnum}+$");
	}

	/**
	 * 半角数字のみかチェック
	 * 
	 * @param value
	 * @return
	 */
	public boolean isInteger(String value) {
		return matchPattern(value, "^[0-9]+$");
	}

	/**
	 * 全角数字を含むか表示する
	 * 
	 * @param value
	 * @return
	 */
	public boolean hasFullInt(String value) {
		return matchPattern(value, "^[^０-９]+$");
	}

	/**
	 * 日付形式かチェック（yyyy/mm/dd）
	 * 
	 * @param value
	 * @return
	 */
	public boolean isDateFormat(String value) {
		String delim = "-"; // 区切り文字
		return matchPattern(value, "^(" + "[0-9]{4}" + delim + "[0-9]{2}" + delim + "[0-9]{2}" + delim + ")+$");
	}

	/**
	 * 日付が実在する数字かチェック
	 * 
	 * @param value
	 * @return
	 */
	public boolean isExistDate(String value) {
		int year = Integer.parseInt(value.substring(0, 4));
		int month = Integer.parseInt(value.substring(5, 7));
		int day = Integer.parseInt(value.substring(8, 10));
		return !(year > 2038 || month > 12 || day > 31);
	}

	/**
	 * 過去の日付であるかチェック
	 * 
	 * @param value
	 * @return
	 */
	public boolean isPastDate(String value) {
		Date d = new Date();

		SimpleDateFormat y1 = new SimpleDateFormat("yyyy");
		String y2 = y1.format(d);

		SimpleDateFormat m1 = new SimpleDateFormat("MM");
		String m2 = m1.format(d);

		SimpleDateFormat d1 = new SimpleDateFormat("dd");
		String d2 = d1.format(d);

		int thisYear = Integer.parseInt(y2);
		int thisMonth = Integer.parseInt(m2);
		int today = Integer.parseInt(d2);

		int year = Integer.parseInt(value.substring(0, 4));
		int month = Integer.parseInt(value.substring(5, 7));
		int day = Integer.parseInt(value.substring(8, 10));
		return (year == thisYear && month <= thisMonth && day <= today) || (year < thisYear);
	}

	/**
	 * 登録チェック
	 * 
	 * @param startTime,startMin,endTime,endMin
	 * @return
	 */
	public boolean canReserve(String startTime, String startMin, String endTime, String endMin) {
		boolean flag = false;

		int start = Integer.parseInt(startTime + startMin);
		int end = Integer.parseInt(endTime + endMin);
		if (start >= end) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 日時予約可能チェック
	 * 
	 * @param value
	 * @return
	 */
	public boolean pastReserve(String year, String month, String day) {
		boolean flag;

		LocalDate nowDate = LocalDate.now();

		LocalDate reserveDate = LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));

		if (reserveDate.isBefore(nowDate)) {
			return true;
		}
		return false;
	}

	/**
	 * Email形式かチェック
	 * 
	 * @param str
	 * @return
	 */
	public boolean isEmail(String str) {

		String email = "^([a-zA-Z0-9])+([a-zA-Z0-9\\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\\._-]+)+$";
		// "^([a-zA-Z0-9])+([a-zA-Z0-9\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\._-]+)+$"

		return matchPattern(str, email);
	}

	/**
	 * 固定電話形式かチェック
	 * 
	 * @param str
	 * @return
	 */
	public boolean isPhoneNumber(String str) {

		String tel = "0\\d{1,4}-\\d{1,4}-\\d{4}";
		return matchPattern(str, tel);
	}

	/**
	 * 携帯電話形式かチェック
	 * 
	 * @param str
	 * @return
	 */
	public boolean isMobilePhoneNumber(String str) {
		String mobile = "^(050|070|080|090)-\\d{4}-\\d{4}$";
		return matchPattern(str, mobile);
	}

	/**
	 * ハイフンを含むかチェック
	 * 
	 * @param str
	 * @return
	 */
	public boolean hasHyphen(String str) {
		String hyphen = "^[^\\-]+$";
		return matchPattern(str, hyphen);

	}

	/**
	 * 数字とハイフンが半角かチェック
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNumHyp(String str) {
		String hyphen = "[0-9０-９\\-]+$";
		return matchPattern(str, hyphen);

	}

	/**
	 * 数字とハイフンが半角かチェック
	 * 
	 * @param str
	 * @return
	 */
	public boolean isHalf(String str) {
		String hyphen = "^[\\d\\-]+$";
		return matchPattern(str, hyphen);

	}

	/**
	 * 文字数が指定範囲内かチェック
	 * 
	 * @param value
	 * @param min
	 * @param max
	 * @return
	 */
	public boolean isBetween(String value, int min, int max) {
		String length = String.valueOf(value.length());
		return isRange(length, min, max);
	}

	/**
	 * 数値が指定範囲内にあるかチェック
	 * 
	 * @param value
	 * @param min
	 * @param max
	 * @return
	 */
	public boolean isRange(String value, int min, int max) {
		int num = Integer.parseInt(value);
		return min <= num && num <= max;
	}

	// public boolean isTimeFormat(String str) {}
	// public boolean isExistTime(String str) {}
	// public boolean isZipCode(String str) {}

	public boolean isSame(String str1, String str2) {
		return str1.equals(str2);
	}

	/**
	 * バリデーションエラー生成処理
	 * 
	 * @param msg     エラーメッセージ
	 * @param context バリデータ情報
	 * @return 常にfalseを返す
	 */
	public boolean createErrorWithMsg(String msg, ConstraintValidatorContext context) {
		// デフォルトのエラーメッセージを無効化
		context.disableDefaultConstraintViolation();
		// バリデーションの独自エラーメッセージ設定
		context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		return false;
	}
}