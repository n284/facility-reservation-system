package jp.co.ginga.web.application.controller.user.complete;

import lombok.Data;

@Data
public class UserCompleteForm {

	private String sysMsg;

	private String link;

	private String btnName;

	public static UserCompleteForm getInstance() {
		return new UserCompleteForm();
	}

}