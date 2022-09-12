/**
 *
 */
package jp.co.ginga.web.util.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ginga.web.application.controller.error.ErrorForm;

/**
 * SpringMVC 例外ハンドラークラス
 *
 * @author yoshi
 *
 */
@ControllerAdvice
public class GlobalHandlerException implements HandlerExceptionResolver {

	/**
	 * SpringMVC 例外ハンドラー処理
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		ModelAndView mv = new ModelAndView();
		ErrorForm ef = new ErrorForm();

		mv.setViewName("redirect:/error/error ");
		//例外のエラー毎にメッセージを設定する
		if (ex instanceof IllegalArgumentException) {
			ef.setErrorMessage("エラーサンプル");
		} else if (ex instanceof MyBatisSystemException) {

		} else {

		}
		return mv;
	}

}
