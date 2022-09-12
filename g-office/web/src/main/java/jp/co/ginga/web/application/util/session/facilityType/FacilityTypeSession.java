package jp.co.ginga.web.application.util.session.facilityType;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.facilityType.FacilityTypeForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacilityTypeSession implements Serializable{
	private FacilityTypeForm facilityTypeForm;

	private FacilityTypeForm beforeFacilityTypeForm;

	private String optimisticRockValue;

	public FacilityTypeForm getFacilityTypeForm() {
		if(this.facilityTypeForm == null) {
			this.facilityTypeForm = FacilityTypeForm.getInstance();
		}
		return this.facilityTypeForm;
	}

	public void clear() {
		System.out.println("FacilityType:session-clear");
		this.facilityTypeForm = null;
		this.beforeFacilityTypeForm = null;
		this.optimisticRockValue = null;
	}
}
