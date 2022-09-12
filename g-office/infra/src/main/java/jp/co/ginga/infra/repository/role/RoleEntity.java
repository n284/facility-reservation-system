package jp.co.ginga.infra.repository.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleEntity {


	private String roleId;

	private String roleName;


}
