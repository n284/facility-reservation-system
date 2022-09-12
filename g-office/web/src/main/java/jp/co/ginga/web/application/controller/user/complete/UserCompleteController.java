package jp.co.ginga.web.application.controller.user.complete;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ginga.web.application.util.helper.userrole.UserRoleFormHelper;
import jp.co.ginga.web.application.util.session.user.UserSession;
import jp.co.ginga.web.domain.service.user.UserManagerDto;
import jp.co.ginga.web.domain.service.user.UserManagerService;
import jp.co.ginga.web.domain.service.util.constant.ServiceConst;
import jp.co.ginga.web.util.security.domain.service.security.UserDetailsImpl;

@Controller
public class UserCompleteController {

	/**
	 * メッセージプロパティ
	 */
	@Autowired
	MessageSource msg;

	/**
	 * ユーザー情報セッションオブジェクト
	 */
	@Autowired
	UserSession userSession;

	/**
	 * ユーザー情報管理サービス
	 */
	@Autowired
	UserManagerService userManagerService;

	/**
	 * ユーザー情報完了ヘルパー
	 */
	@Autowired
	UserCompleteHelper userCompleteHelper;

	@Autowired
	UserRoleFormHelper userRoleFormHelper;

	/**
	 * ユーザー情報操作完了リクエスト処理
	 *
	 * @param modelMap
	 * @return
	 */
	@GetMapping(path = "/user/complete")
	public String createUserConmplete(ModelMap modelMap) {

		UserCompleteForm form = UserCompleteForm.getInstance();

		if (modelMap.get("data") == null) {
			return "/error/error";
		}

		if (modelMap.containsKey("data")) {
			// ビューの設定
			modelMap.addAttribute("userComplete", modelMap.get("data"));

		} else {
			form.setSysMsg(msg.getMessage("Already.completed", null, Locale.getDefault()));
			modelMap.addAttribute("userComplete", form);

		}

		return "user/user-complete";

	}

	/**
	 * ユーザー情報登録完了リクエスト処理
	 *
	 * @param ra
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/user/complete", params = "add")
	public String createUserCompleteAdd(RedirectAttributes ra, Model model,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {

		UserCompleteForm form = UserCompleteForm.getInstance();

		if (userSession.getUserForm().getUserId() == null) {
			form.setSysMsg("セッションが破棄されております。もう一度作業をし直してください");
			ra.addFlashAttribute("data", form);
			return "redirect:/user/complete";
		}

		UserManagerDto dto = userCompleteHelper.convertUserManagerDto(userSession);
		dto.setUserRoleDtoList(
				userRoleFormHelper.createUserRoleDtoList(userSession.getUserRoleFormList(), userSession.getUserForm()));

		UserManagerDto resultDto = userManagerService.add(dto);

		form = userCompleteHelper.creatUserCompleteForm(resultDto);

		form.setSysMsg("登録が完了しました");
		form.setBtnName(msg.getMessage("label.add", null, Locale.getDefault()));

		ra.addFlashAttribute("data", form);

		// セッションクリア
		userSession.clear();

		System.out.println("comlete-User-Clear" + userSession.getUserForm());

		return "redirect:/user/complete";
	}

	/**
	 * ユーザー情報更新完了リクエスト処理
	 *
	 * @param ra
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/user/complete", params = "update")
	public String createUserCompleteUpdate(RedirectAttributes ra, Model model) {

		UserCompleteForm form = new UserCompleteForm();

		// セッションから情報を取得
		UserManagerDto userDto = userCompleteHelper.convertUserManagerDto(userSession);

		userDto.setUserRoleDtoList(
				userRoleFormHelper.createUserRoleDtoList(userSession.getUserRoleFormList(), userSession.getUserForm()));

		System.out.println("ユーザー名称：" + userDto.getUserDto().getUserName());
		// ユーザー情報更新処理
		UserManagerDto r_userDto = userManagerService.update(userDto);

		switch (r_userDto.getResult()) {

		case ServiceConst.OK:
			form.setBtnName(msg.getMessage("label.update", null, Locale.getDefault()));
			break;
		case ServiceConst.OPTIMISTIC_ROCK_ERROR:
			form.setSysMsg(msg.getMessage("User.optimistic", null, Locale.getDefault()));
			break;
		default:
			return "redirect:/error/error";
		}

		System.out.println("");
		ra.addFlashAttribute("data", form);

		userSession.clear();

		return "redirect:/user/complete";
	}

	/**
	 * ユーザー情報削除完了リクエスト処理
	 *
	 * @param ra
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/user/complete", params = "delete")
	public String createUserCompleteDelete(RedirectAttributes ra, Model model) {

		UserCompleteForm form = new UserCompleteForm();

		// セッションから情報を取得
		UserManagerDto userDto = userCompleteHelper.convertUserManagerDto(userSession);

		UserManagerDto result = userManagerService.delete(userDto);

		if (result.getResult() == ServiceConst.ERROR) {
			return "redirect:/error/error";
		}

		form.setBtnName(msg.getMessage("label.delete", null, Locale.getDefault()));
		ra.addFlashAttribute("data", form);

		userSession.clear();

		return "redirect:/user/complete";
	}
}