package jp.co.ginga.web.application.validation.user;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import jp.co.ginga.web.application.validation.user.impl.UserBirthdayValidatorImpl;

@Target(ElementType.FIELD) // フィールドが適用対象
@Retention(RetentionPolicy.RUNTIME) // 実行時までアノテーション情報を保持する
@Constraint(validatedBy = { UserBirthdayValidatorImpl.class }) // バリデータクラスを登録
@ReportAsSingleViolation
public @interface UserBirthdayValidator {

	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}