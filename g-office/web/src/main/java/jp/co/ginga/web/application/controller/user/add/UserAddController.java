package jp.co.ginga.web.application.controller.user.add;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.ginga.web.application.util.helper.role.RoleFormHelper;
import jp.co.ginga.web.application.util.session.user.UserSession;
import jp.co.ginga.web.domain.service.user.UserManagerDto;
import jp.co.ginga.web.domain.service.user.UserManagerService;

@Controller
public class UserAddController {

	/**
	 * ユーザー情報登録ヘルパー
	 */
	@Autowired
	UserAddHelper userAddHelper;
	
	/**
	 * 権限情報登録ヘルパー
	 */
	@Autowired
	RoleFormHelper roleHelper;

	/**
	 * ユーザー情報サービス
	 */
	@Autowired
	UserManagerService userManagerService;

	/**
	 * 施設情報セッション
	 */
	@Autowired
	UserSession userSession;

	@GetMapping("/user/add")
	public String createUserAdd(ModelMap mdMap) {

		// ユーザー情報登録フォーム
		UserAddForm form = null;

		// リダイレクト時の処理
		if (mdMap.containsKey("errors")) {

			// sessionオブジェクトからFormオブジェクトの生成
			form = userAddHelper.createUserAddForm(userSession);

			// ModelMapオブジェクトにFormオブジェクトをセット
			mdMap.addAttribute("userAddForm", form);

			// keyの生成
			String key = BindingResult.MODEL_KEY_PREFIX + "userAddForm";

			// バリデータチェック結果を設定
			mdMap.addAttribute(key, mdMap.get("errors"));

		} else {

			// ユーザー情報セッションをクリア
			userSession.clear();

			UserManagerDto dto = userManagerService.getRoleList();

			form = userAddHelper.createUserAddForm(dto);

			userSession.setRoleFormList(form.getRoleFormList());

			mdMap.addAttribute("userAddForm", form);

		}

		return "user/user-add";
	}

}
