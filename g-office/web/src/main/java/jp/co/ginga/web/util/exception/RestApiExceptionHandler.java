/**
 *
 */
package jp.co.ginga.web.util.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * RestAPI用の例外ハンドラー
 * @author yoshi
 *
 */
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {


	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}



}
