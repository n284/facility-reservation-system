package jp.co.ginga.web.application.controller.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import jp.co.ginga.web.application.controller.user.list.UserListController;
import jp.co.ginga.web.application.util.form.user.UserForm;
import jp.co.ginga.web.application.util.form.userRole.UserRoleForm;

/**
 * @author souken
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserListContorollerTest {

	private MockMvc mockMvc;

	@Autowired
	UserListController userListController;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(userListController).build();
	}

	@Test
	public void getUserListContoroller() throws Exception {
		System.out.println("getUserListContoroller");

//		UserManagerDto userManagerDto = userManagerService.getUserList();
//
//		UserListForm userListForm = userListHelper.createUserListForm(userManagerDto);

		MvcResult result = mockMvc.perform(get("/user/list"))
				// HTTPステータスがOKであることを確認
				.andExpect(status().isOk())
				// 次画面の遷移先がindex.htmlであることを確認
				.andExpect(view().name("user/user-list"))
				// Modelオブジェクトにエラーが無いことを確認
				.andExpect(model().hasNoErrors())
//				.andExpect(model().attribute("userListForm", userListForm.getUserFormList()))
//				.andExpect(model().attribute("roleList", userListForm.getUserRoleFormList()))

				.andReturn();

		// modelに正しい値が詰められているか確認
		@SuppressWarnings("unchecked")
		List<UserForm> userListForm = (List<UserForm>) result.getModelAndView().getModel().get("userListForm");
		@SuppressWarnings("unchecked")
		List<UserRoleForm> roleList = (List<UserRoleForm>) result.getModelAndView().getModel().get("roleList");

		assertEquals(6, userListForm.size());
		assertEquals(7, roleList.size());
	}

}