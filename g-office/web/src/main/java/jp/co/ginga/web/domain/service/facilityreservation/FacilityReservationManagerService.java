package jp.co.ginga.web.domain.service.facilityreservation;

import java.util.Calendar;

import jp.co.ginga.infra.repository.facility.FacilityRepository;
import jp.co.ginga.infra.repository.facilityType.FacilityTypeRepository;

public interface FacilityReservationManagerService {
	public FacilityReservationManagerDto getFacility();
	public FacilityReservationManagerDto getFacilityByFacilityTypeId(int facilityTypeId);
	public FacilityReservationManagerDto getFacilityReservationByCalendar(int facilityId, Calendar calendar);
	public FacilityReservationManagerDto getFacilityReservationDetailByFacilityReservationId(int facilityReservationId);
	public FacilityReservationManagerDto getFacilityReservationList(int facilityId, int year, int month, int date);
	/*
	 * テスト
	 */
	public void setFacilityRepository(FacilityRepository facilityRepository);

	public void setFacilityTypeRepository(FacilityTypeRepository facilityTypeRepository);
}
