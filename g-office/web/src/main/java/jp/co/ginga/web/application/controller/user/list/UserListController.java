package jp.co.ginga.web.application.controller.user.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.ginga.web.domain.service.user.UserManagerDto;
import jp.co.ginga.web.domain.service.user.UserManagerHelper;
import jp.co.ginga.web.domain.service.user.UserManagerService;

@Controller
public class UserListController {

	/**
	 * ユーザー一覧ヘルパー
	 */
	@Autowired
	UserManagerHelper userManagerHelper;

	@Autowired
	UserListHelper userListHelper;

	@Autowired
	UserManagerService userManagerService;

	/**
	 * ユーザーサービス
	 *
	 */

	@GetMapping("/user/list")
	public String createUserList(Model model) {

		UserManagerDto userManagerDto = userManagerService.getUserList();

		UserListForm userListForm = userListHelper.createUserListForm(userManagerDto);

		model.addAttribute("userListForm", userListForm.getUserFormList());
		model.addAttribute("roleList", userListForm.getUserRoleFormList());


		return "user/user-list";

	}
}
