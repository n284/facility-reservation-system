package jp.co.ginga.web.application.util.helper.facilityType;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.facilityType.FacilityTypeForm;
import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;

@Component
public class FacilityTypeFormHelper {

	@Autowired
	ModelMapper modelMapper;


	/**
	 *
	 * @param facilityTypeDto:FacilityTypeDto
	 * @return  FacilityTypeForm
	 */
	public FacilityTypeForm convertFacilityTypeForm(FacilityTypeDto dto) {
		FacilityTypeForm facilityTypeForm = new FacilityTypeForm(
			dto.getFacilityTypeId(),
			dto.getName()
		);

		return facilityTypeForm;
	}

	/**
	 * FacilityTypeDtoリストからFacilityTypeForm
	 * @param facilityTypeDtoList:List<FacilityTypeDto>
	 * @return List<FacilityTypeForm>
	 */
	public List<FacilityTypeForm> convertFacilityTypeFormList(List<FacilityTypeDto> list){
		List<FacilityTypeForm> facilityTypeFormList = new ArrayList<FacilityTypeForm>();

		for(FacilityTypeDto dto : list) {
			facilityTypeFormList.add(this.convertFacilityTypeForm(dto));
		}

		return facilityTypeFormList;
	}

	/**
	 *
	 * @param facilityTypeForm:FacilityTypeForm
	 * @return  FacilityTypeDto
	 */
	public FacilityTypeDto convertFacilityTypeDto(FacilityTypeForm form) {
		FacilityTypeDto facilityTypeDto = new FacilityTypeDto(
			form.getFacilityTypeId(),
			form.getName()
		);

		return facilityTypeDto;
	}


	/**
	 * FacilityTypeFormリストからFacilityTypeDto
	 * @param facilityTypeFormList:List<FacilityTypeForm>
	 * @return List<FacilityTypeDto>
	 */
	public List<FacilityTypeDto> convertFacilityTypeDtoList(List<FacilityTypeForm> list){
		List<FacilityTypeDto> facilityTypeDtoList = new ArrayList<FacilityTypeDto>();

		for(FacilityTypeForm form : list) {
			facilityTypeDtoList.add(this.convertFacilityTypeDto(form));
		}

		return facilityTypeDtoList;
	}





}
