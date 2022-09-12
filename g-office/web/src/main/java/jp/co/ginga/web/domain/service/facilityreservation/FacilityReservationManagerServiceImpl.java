package jp.co.ginga.web.domain.service.facilityreservation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ginga.infra.repository.facility.FacilityEntity;
import jp.co.ginga.infra.repository.facility.FacilityRepository;
import jp.co.ginga.infra.repository.facilityType.FacilityTypeEntity;
import jp.co.ginga.infra.repository.facilityType.FacilityTypeRepository;
import jp.co.ginga.infra.repository.reservation.ReservationEntity;
import jp.co.ginga.infra.repository.reservation.ReservationRepository;
import jp.co.ginga.web.domain.service.util.dto.calendar.CalendarDayDto;
import jp.co.ginga.web.domain.service.util.dto.calendar.CalendarMonthDto;
import jp.co.ginga.web.domain.service.util.dto.facilityreservation.FacilityReservationDto;

@Service
@Transactional
public class FacilityReservationManagerServiceImpl implements FacilityReservationManagerService{

	@Autowired
	FacilityRepository facilityRepository;

	@Autowired
	FacilityTypeRepository facilityTypeRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	FacilityReservationManagerHelper facilityReservationManagerHelper;


	@Override
	@Transactional(readOnly = true)
	public FacilityReservationManagerDto getFacility() {
		List<FacilityEntity> facilityEntityList = this.facilityRepository.findAll();
		List<FacilityTypeEntity> facilityTypeEntityList = this.facilityTypeRepository.findAll();
		FacilityReservationManagerDto facilityReservationManagerDto = this.facilityReservationManagerHelper.createFacilityReservationServiceDto(facilityEntityList,facilityTypeEntityList);

		return facilityReservationManagerDto;
	}

	@Override
	@Transactional(readOnly = true)
	public FacilityReservationManagerDto getFacilityByFacilityTypeId(int facilityTypeId) {
		List<FacilityEntity> facilityEntityList = this.facilityRepository.findByFacilityTypeId(facilityTypeId);
		List<FacilityTypeEntity> facilityTypeEntityList = this.facilityTypeRepository.findAll();
		FacilityReservationManagerDto facilityReservationManagerDto = this.facilityReservationManagerHelper.createFacilityReservationServiceDto(facilityEntityList, facilityTypeEntityList);

		return facilityReservationManagerDto;
	}

	@Override
	@Transactional(readOnly = true)
	public FacilityReservationManagerDto getFacilityReservationByCalendar(int facilityId, Calendar calendar) {
		final int year = calendar.get(Calendar.YEAR);
		final int month = calendar.get(Calendar.MONTH);
		final int date = calendar.get(Calendar.DATE);

		int firstDay;
		int lastDate;
		Calendar firstDateOfMonth = Calendar.getInstance();
		Calendar firstDateOfNextMonth = Calendar.getInstance();
		firstDateOfMonth.clear();
		firstDateOfNextMonth.clear();
		firstDateOfMonth.set(year, month, 1);
		firstDateOfNextMonth.set(year, month+1, 1);

		FacilityEntity facilityEntity = this.facilityRepository.findByFacilityId(facilityId);
		List<ReservationEntity> facilityReservationEntityList = this.reservationRepository.findByYearAndMonth(facilityId, year, month);
		FacilityReservationManagerDto facilityReservationManagerDto = this.facilityReservationManagerHelper.createFacilityReservationServiceDto(facilityReservationEntityList, facilityEntity);
		CalendarMonthDto calendarMonthDto = CalendarMonthDto.getInstance();
		List<CalendarDayDto> calendarDayDtoList = new ArrayList<CalendarDayDto>();
		for(int i = 0; i < 6*7; i++) {
			calendarDayDtoList.add(CalendarDayDto.getInstance());
		}
		calendar.clear();
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		calendar.set(year, month, 1);
		firstDay = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.MONTH, 1);//Calendar.DATEで0日の値を取得すると月最終日が取得されるが一月前を取得するため
		calendar.add(Calendar.DATE, -1);//0日にすると先月の最終日を取得できる
		lastDate = calendar.get(Calendar.DATE);

		for(int i = 0; i < lastDate; i++) {
			CalendarDayDto calendarDayDto = calendarDayDtoList.get(i + firstDay-1);
			calendarDayDto.setDate(i+1);
			switch((i+firstDay)%7) {
			case 0:
				calendarDayDto.setDay("土");
				break;
			case 1:
				calendarDayDto.setDay("日");
				break;
			case 2:
				calendarDayDto.setDay("月");
				break;
			case 3:
				calendarDayDto.setDay("火");
				break;
			case 4:
				calendarDayDto.setDay("水");
				break;
			case 5:
				calendarDayDto.setDay("木");
				break;
			case 6:
				calendarDayDto.setDay("金");
				break;
			}
		}

		for(FacilityReservationDto facilityReservationDto : facilityReservationManagerDto.getFacilityReservationDtoList()) {
			Calendar startTime = Calendar.getInstance();
			startTime.setTimeInMillis(facilityReservationDto.getStartTime().getTime());
			calendarDayDtoList.get(startTime.get(Calendar.DATE)+firstDay-2).getFacilityReservationDtoList().add(facilityReservationDto);
		}
		calendarMonthDto.setCalendarDayDtoList(calendarDayDtoList);
		facilityReservationManagerDto.setCalendarMonthDto(calendarMonthDto);
		return facilityReservationManagerDto;
	}

	public FacilityReservationManagerDto getFacilityReservationDetailByFacilityReservationId(int facilityReservationId) {
		ReservationEntity reservationEntity = this.reservationRepository.findByFacilityReservationId(facilityReservationId);
		FacilityReservationManagerDto facilityReservationManagerDto = this.facilityReservationManagerHelper.createFacilityReservationServiceDto(reservationEntity);
		facilityReservationManagerDto.setOptimisticRockValue(reservationEntity.toString());
		return facilityReservationManagerDto;
	}

	public FacilityReservationManagerDto getFacilityReservationList(int facilityId, int year, int month, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month, date);

		List<ReservationEntity> reservationEntityList = this.reservationRepository.findBydate(facilityId, year, month, date);
		FacilityEntity facilityEntity = this.facilityRepository.findByFacilityId(facilityId);
		FacilityReservationManagerDto facilityReservationManagerDto = this.facilityReservationManagerHelper.createFacilityReservationServiceDto(reservationEntityList, facilityEntity);
		return facilityReservationManagerDto;
	}


	/*
	 * テスト
	 */
	@Override
	public void setFacilityRepository(FacilityRepository facilityRepository) {
		this.facilityRepository = facilityRepository;
	}

	@Override
	public void setFacilityTypeRepository(FacilityTypeRepository facilityTypeRepository) {
		this.facilityTypeRepository = facilityTypeRepository;
	}

}
