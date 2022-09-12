package jp.co.ginga.web.application.controller.user.confirm;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ginga.web.application.controller.user.add.UserAddForm;
import jp.co.ginga.web.application.controller.user.detail.UserDetailForm;
import jp.co.ginga.web.application.util.session.user.UserSession;
import jp.co.ginga.web.domain.service.user.UserManagerDto;
import jp.co.ginga.web.domain.service.user.UserManagerService;
import lombok.Data;

@Controller
@Data
public class UserConfirmController {

	/**
	 * メッセージプロパティ
	 */
	@Autowired
	MessageSource msg;

	/**
	 * ユーザー情報セッション
	 */
	@Autowired
	UserSession userSession;

	/**
	 * ユーザー情報確認ヘルパー
	 */
	@Autowired
	UserConfirmHelper userConfirmHelper;

	/**
	 * ユーザー情報管理サービス
	 */
	@Autowired
	UserManagerService userService;

	/**
	 * 新規登録操作からの確認リクエスト処理
	 * 
	 * @param inputForm
	 * @param result
	 * @param ra
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/user/confirm", params = "add")
	public String postUserConfirmAdd(@ModelAttribute @Validated UserAddForm inputForm, BindingResult result,
			RedirectAttributes ra, Model model) {

		// エラー判定フラグ
		boolean errFlag = false;

		// ユーザー情報をセッションにセット
		userSession.setUserForm(inputForm.getUserForm());
		userSession.setRoleFormList(inputForm.getRoleFormList());
		userSession.setUserRoleFormList(userConfirmHelper.convertRoleList(inputForm.getRoleFormList()));
		// バリデータエラー判定
		if (result.hasErrors()) {

			errFlag = true;
		}

		// FormからDtoデータの取得
		UserManagerDto dto = userConfirmHelper.createUserManagerServiceDto(inputForm);

		// userIdの重複チェック
		UserManagerDto resultDto = userService.getUserListByUserId(dto.getUserDto().getUserId());

		// ユーザー名称が重複していた場合
		if (resultDto.getUserDtoList().size() != 0) {
			// エラーメッセージの設定
			// msg
			result.rejectValue("userForm.userId", null, msg.getMessage("User.duplicate", null, Locale.getDefault()));
			errFlag = true;
		}

		if (!(inputForm.getUserForm().getPassword().equals(inputForm.getUserForm().getRetypePassword()))) {
			// エラーメッセージの設定
			// msg
			result.rejectValue("userForm.password", null,
					msg.getMessage("User.match", new String[] { "パスワード" }, Locale.getDefault()));

			errFlag = true;

		} else if (!(inputForm.getUserForm().getMailAddress().equals(inputForm.getUserForm().getRetypeMailAddress()))) {
			// エラーメッセージの設定
			// msg
			result.rejectValue("userForm.mailAddress", null,
					msg.getMessage("User.match", new String[] { "メールアドレス" }, Locale.getDefault()));

			errFlag = true;

		}

		// セッションからFormへの出力情報の取得
		UserConfirmForm form = userConfirmHelper.createUserConfirmForm(userSession);

		form.setBtnName(msg.getMessage("label.add", null, Locale.getDefault()));
		form.setBtnAction(msg.getMessage("action.add", null, Locale.getDefault()));

		// リダイレクト処理
		if (errFlag == true) {
			ra.addFlashAttribute("errors", result);
			return "redirect:/user/add";
		}

		// ビューの設定
		model.addAttribute("userConfirmForm", form);

		return "user/user-confirm";
	}

	/**
	 * 更新操作からの確認リクエスト処理
	 * 
	 * @param inputForm
	 * @param result
	 * @param ra
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/user/confirm", params = "update")
	public String postUserConfirmUpdate(@ModelAttribute @Validated UserDetailForm inputForm, BindingResult result,
			RedirectAttributes ra, Model model) {

		// エラー判定フラグ
		boolean errFlag = false;

		// バリデータエラー判定
		if (result.hasErrors()) {
			errFlag = true;
		}

		// 編集前とどこが修正箇所なのかのチェック
		int checkValue = userConfirmHelper.checkUpdated(inputForm, userSession.getBeforeUserForm());

		switch (checkValue) {

		case UserConfirmHelper.NO_UPDATE:

			// 何も更新されていないので、詳細画面にリダイレクト
			// msg.getMessage("User.notChange", null, Locale.getDefault())
			result.rejectValue("sysMsg", null, msg.getMessage("User.notChange", null, Locale.getDefault()));
			errFlag = true;
			break;

		default:
			break;
		}

		if (!(inputForm.getUserForm().getPassword().equals(inputForm.getUserForm().getRetypePassword()))) {
			// エラーメッセージの設定
			// msg
			result.rejectValue("userForm.password", null,
					msg.getMessage("User.match", new String[] { "パスワード" }, Locale.getDefault()));

			errFlag = true;

		} else if (!(inputForm.getUserForm().getMailAddress().equals(inputForm.getUserForm().getRetypeMailAddress()))) {
			// エラーメッセージの設定
			// msg
			result.rejectValue("userForm.mailAddress", null,
					msg.getMessage("User.match", new String[] { "メールアドレス" }, Locale.getDefault()));

			errFlag = true;

		}

		userSession.setUserRoleFormList(userConfirmHelper.convertRoleList(inputForm.getRoleFormList()));
		userSession.setRoleFormList(inputForm.getRoleFormList());
		userSession.setUserForm(inputForm.getUserForm());

		// セッションからFormへの出力情報の取得
		UserConfirmForm form = userConfirmHelper.createUserConfirmForm(userSession);

		form.setBtnName(msg.getMessage("label.update", null, Locale.getDefault()));
		form.setBtnAction(msg.getMessage("action.update", null, Locale.getDefault()));
		// リダイレクト処理
		if (errFlag == true) {
			ra.addFlashAttribute("errors", result);
			return "redirect:/user/detail/" + userSession.getUserForm().getUserId();
		}

		// ビューの設定
		model.addAttribute("userConfirmForm", form);

		return "user/user-confirm";
	}

	/**
	 * 削除操作からの確認リクエスト処理
	 * 
	 * @param inputForm
	 * @param result
	 * @param ra
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/user/confirm", params = "delete")
	public String postUserConfirmDelete(@ModelAttribute UserDetailForm inputForm, BindingResult result,
			RedirectAttributes ra, Model model) {

		// 現在のデータを取得
		UserManagerDto fmDto = userService.getUser(userSession.getBeforeUserForm().getUserId());


		// 楽観ロックだとユーザーIdが変わっていたら比べることができないので悲観ロック？？
		// データが更新されていないかのチェック
		int checkValue = userConfirmHelper.checkUpdated(fmDto, userSession.getBeforeUserForm());

		// エラー判定フラグ
		boolean errFlag = false;

		if (checkValue != UserConfirmHelper.NO_UPDATE) {

			result.rejectValue("sysMsg", null, "データが更新されている可能性があります");
			errFlag = true;

		}

		// Formへ出力情報の取得
		UserConfirmForm form = userConfirmHelper.createUserConfirmForm(fmDto);

		form.setBtnName(msg.getMessage("label.delete", null, Locale.getDefault()));
		form.setBtnAction(msg.getMessage("action.delete", null, Locale.getDefault()));

		// DBのデータをセッションに設定
		userSession.setUserForm(form.getUserForm());
		userSession.setUserRoleFormList(form.getUserRoleFormList());
		userSession.setRoleFormList(form.getRoleFormList());
		// リダイレクト処理
		if (errFlag == true) {
			ra.addFlashAttribute("errors", result);
			return "redirect:/user/detail/" + userSession.getUserForm().getUserId();
		}

		// ビューの設定
		model.addAttribute("userConfirmForm", form);

		return "user/user-confirm";

	}
}
