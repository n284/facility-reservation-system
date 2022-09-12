package jp.co.ginga.web.application.util.session.facilityreservation;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.facility.FacilityForm;
import jp.co.ginga.web.application.util.form.facilityType.FacilityTypeForm;
import jp.co.ginga.web.application.util.form.facilityreservation.FacilityReservationForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacilityReservationSession implements Serializable{
	private Calendar calendar;
	private List<FacilityForm> facilityFormList;
	private FacilityForm facilityForm;
	private List<FacilityTypeForm> facilityTypeFormList;
	private List<FacilityReservationForm> facilityReservationFormList;
	private FacilityReservationForm facilityReservationForm;
	private FacilityReservationForm beforeFacilityReservationForm;
	private String optimisticRockValue;

	public void clear() {
		this.calendar = null;
		this.facilityFormList = null;
		this.facilityForm = null;
		this.facilityTypeFormList = null;
		this.facilityReservationForm = null;
		this.beforeFacilityReservationForm = null;
		this.optimisticRockValue = null;
	}
}
