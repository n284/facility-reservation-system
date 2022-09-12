package jp.co.ginga.web.application.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ErrorController {

	/**
	 * エラーリクエスト処理
	 */
	@GetMapping("/error/error")
	public String getError() {

		return "/error/error";
	}

	/**
	 * エラーリクエスト処理
	 */
	@PostMapping("/error/error")
	public String postError() {

		return "/error/error";
	}

	/**
	 * Accsessエラーリクエスト処理
	 */
	@GetMapping("/error/accsess")
	public String getErrorAccess() {
		return "/error/access-denied";
	}

	/**
	 * Accsessエラーリクエスト処理
	 */
	@PostMapping("/error/accsess")
	public String postErrorAccess() {
		return "/error/access-denied";
	}

	@GetMapping("/facility/confirm")
	public String getFacilityError() {

		return "/error/error";
	}

	@GetMapping("/user/confirm")
	public String getUserError() {

		return "/error/error";
	}
}
