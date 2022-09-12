package jp.co.ginga.web.util.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Pointcut("execution(public * jp.co.ginga..*(..))")
	private void publicLog() {
	}

	/**
	 * コントローラクラス ポイントカット
	 */
	@Pointcut("execution(public * jp.co.ginga.application..*Controller.*(..))")
	private void applicationLog() {
	}

	/**
	 * サービスクラス ポイントカット
	 */
	@Pointcut("execution(public * jp.co.ginga.domain.service..*ServiceImpl.*(..))")
	private void domainLog() {
	}

	/**
	 * リポジトリクラス ポイントカット
	 */
	@Pointcut("execution(public * jp.co.ginga.infra.repository..*Repository.*(..))")
	private void infraLog() {
	}

	/**
	 *
	 * @param jp
	 */
	@Before("(applicationLog() || domainLog() || infraLog())")
	public void invokeApiControllerBefore(JoinPoint jp) {
		Signature sig = jp.getSignature();
		String args = "無し";
		if (jp.getArgs() != null && jp.getArgs().length > 0) {
			args = Arrays.toString(jp.getArgs());
		}
		logger.info(
				"\n【操作開始】\n" + "メソッド：" + sig.getName() +
				"\nパッケージ：" + sig.getDeclaringTypeName() +
				"\n入力パラメータ：" + args+
				"\n"
				);
	}

	/**
	 * メソッド実行後のログ出力
	 * @param jp
	 * @param returnValue
	 */
	@AfterReturning(pointcut = "(applicationLog() || domainLog() || infraLog())", returning = "returnValue")
	public void invokeApiControllerAfter(JoinPoint jp, Object returnValue) {
		Signature sig = jp.getSignature();
		logger.debug(
				"\n【操作終了】\n" + "メソッド：" + sig.getName() +
				//						"\nパッケージ：" + sig.getDeclaringTypeName() +
						"\n戻り値：" + returnValue+
						"\n"
						);

	}

	/**
	 * エラーが発生した場合のログ出力
	 * @param jp
	 * @param ex
	 */
	@AfterThrowing(pointcut = "(applicationLog() || domainLog() || infraLog())", throwing = "ex")
	public void doAfterThrowing(JoinPoint jp, Exception ex) {
		Signature sig = jp.getSignature();
		StringWriter erMessage = new StringWriter();
		ex.printStackTrace(new PrintWriter(erMessage));
		logger.error(
				"\n【例外発生】\n" + "メソッド：" + sig.getName() +
//						"\nパッケージ：" + sig.getDeclaringTypeName() +
						"\n戻り値：" + erMessage.toString() +
						"\n"
						);

	}

}
