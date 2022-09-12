package jp.co.ginga.web.application.controller.user.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jp.co.ginga.web.application.util.helper.role.RoleFormHelper;
import jp.co.ginga.web.application.util.session.user.UserSession;
import jp.co.ginga.web.domain.service.user.UserManagerDto;
import jp.co.ginga.web.domain.service.user.UserManagerService;

@Controller
public class UserDetailController {

	/**
	 * ユーザー情報登録ヘルパー
	 */
	@Autowired
	UserDetailHelper userDetailHelper;

	/**
	 * ユーザー情報サービス
	 */
	@Autowired
	UserManagerService userManagerService;

	@Autowired
	RoleFormHelper roleHelper;

	/**
	 * ユーザー情報セッション
	 */
	@Autowired
	UserSession userSession;

	/**
	 * ユーザー情報登録リクエスト処理
	 *
	 * @param mdMap
	 * @return
	 */
	@GetMapping("/user/detail/{userId}")
	public String getUserDetail(@PathVariable String userId, ModelMap mdMap) {

		// ユーザー情報登録フォーム
		UserDetailForm form = null;

		// リダイレクト時の処理
		if (mdMap.containsKey("errors")) {

			// sessionオブジェクトから入力内容の取得
			form = userDetailHelper.getUserDetailForm(userSession);

			// モデルマップオブジェクトにformオブジェクトをセット

			mdMap.addAttribute("userDetailForm", form);

			// keyの生成
			String key = BindingResult.MODEL_KEY_PREFIX + "userDetailForm";
			mdMap.addAttribute(key, mdMap.get("errors"));

		} else {

			// ユーザー情報セッションをクリア
			userSession.clear();

			// ユーザー情報取得処理
			UserManagerDto userManagerDto = userManagerService.getUser(userId);


			// ユーザー情報登録フォーム生成
			form = userDetailHelper.createUserDetailForm(userManagerDto);

			// ユーザー情報セッションへユーザー情報、ユーザー権限リスト情報、ロック情報をセット
			userSession.setUserForm(form.getUserForm());
			userSession.setUserRoleFormList(form.getUserRoleFormList());
			userSession.setBeforeUserForm(form.getUserForm());
			userSession.setOptimisticRockValue(userManagerDto.getOptimisticRockValue());

			// モデルマップオブジェクトにformオブジェクトをセット
			mdMap.addAttribute("userDetailForm", form);

		}

		return "user/user-detail";

	}
}
