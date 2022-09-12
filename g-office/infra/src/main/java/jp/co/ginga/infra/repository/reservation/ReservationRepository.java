package jp.co.ginga.infra.repository.reservation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import jp.co.ginga.infra.repository.facility.FacilityEntity;
import jp.co.ginga.infra.repository.facilityType.FacilityTypeEntity;

@Repository
@Mapper
public interface ReservationRepository {
	public List<FacilityEntity> findAllFacility();

	public List<FacilityTypeEntity> findAllFacilityType();

	public List<ReservationEntity> findByYearAndMonth(@Param("facilityId") int facilityId, @Param("year") int year, @Param("month") int month);

	public ReservationEntity findByFacilityReservationId(int facilityReservationId);

	public List<ReservationEntity> findBydate(@Param("facilityId") int facilityId, @Param("year") int year, @Param("month") int month, @Param("date") int date);
}
