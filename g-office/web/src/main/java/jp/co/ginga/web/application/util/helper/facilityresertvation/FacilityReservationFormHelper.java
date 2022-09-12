package jp.co.ginga.web.application.util.helper.facilityresertvation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.facility.FacilityForm;
import jp.co.ginga.web.application.util.form.facilityreservation.FacilityReservationForm;
import jp.co.ginga.web.domain.service.util.dto.facilityreservation.FacilityReservationDto;

@Component
public class FacilityReservationFormHelper {
	@Autowired
	ModelMapper modelMapper;

	public FacilityReservationDto convertFacilityReservationDto(FacilityReservationForm facilityReservationForm) {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
		FacilityReservationDto facilityReservationDto = this.modelMapper.map(facilityReservationForm, FacilityReservationDto.class);

		return facilityReservationDto;
	}

	public List<FacilityReservationDto> convertFacilityReservationDtoList(List<FacilityReservationForm> facilityReservationFormList){
		List<FacilityReservationDto> facilityReservationDtoList = new ArrayList<FacilityReservationDto>();
		for(FacilityReservationForm facilityReservationForm : facilityReservationFormList) {
			facilityReservationDtoList.add(this.convertFacilityReservationDto(facilityReservationForm));
		}

		return facilityReservationDtoList;
	}

	public FacilityReservationForm convertFacilityReservationForm(FacilityReservationDto facilityReservationDto) {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
		FacilityReservationForm facilityReservationForm = this.modelMapper.map(facilityReservationDto, FacilityReservationForm.class);
		if(facilityReservationDto.getFacilityDto() != null) {
			FacilityForm facilityForm = this.modelMapper.map(facilityReservationDto.getFacilityDto(), FacilityForm.class);
			facilityReservationForm.setFacilityForm(facilityForm);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTimeInMillis(facilityReservationDto.getStartTime().getTime());
		facilityReservationForm.setYear(String.format("%02d",calendar.get(Calendar.YEAR)));
		facilityReservationForm.setMonth(String.format("%02d",calendar.get(Calendar.MONTH)+1));
		facilityReservationForm.setDate(String.format("%02d",calendar.get(Calendar.DATE)));
		facilityReservationForm.setStartTimeHour(String.format("%02d",calendar.get(Calendar.HOUR_OF_DAY)));
		facilityReservationForm.setStartTimeMinute(String.format("%02d",calendar.get(Calendar.MINUTE)));
		calendar.clear();
		calendar.setTimeInMillis(facilityReservationDto.getEndTime().getTime());
		facilityReservationForm.setEndTimeHour(String.format("%02d",calendar.get(Calendar.HOUR_OF_DAY)));
		facilityReservationForm.setEndTimeMinute(String.format("%02d",calendar.get(Calendar.MINUTE)));
		return facilityReservationForm;
	}

	public List<FacilityReservationForm> convertFacilityReservationFormList(List<FacilityReservationDto> facilityReservationDtoList){
		List<FacilityReservationForm> facilityReservationFormList = new ArrayList<FacilityReservationForm>();
		for(FacilityReservationDto facilityReservationDto : facilityReservationDtoList) {
			facilityReservationFormList.add(this.convertFacilityReservationForm(facilityReservationDto));
		}

		return facilityReservationFormList;
	}

}
