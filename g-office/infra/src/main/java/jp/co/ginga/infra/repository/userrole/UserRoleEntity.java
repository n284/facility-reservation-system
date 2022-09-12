package jp.co.ginga.infra.repository.userrole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRoleEntity {

	// t_user_role テーブルのid
	//private int id;

	private String userId;

	private String roleId;

}
