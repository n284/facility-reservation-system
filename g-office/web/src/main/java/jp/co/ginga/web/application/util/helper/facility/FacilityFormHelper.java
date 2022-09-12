package jp.co.ginga.web.application.util.helper.facility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.facility.FacilityForm;
import jp.co.ginga.web.application.util.helper.facilityType.FacilityTypeFormHelper;
import jp.co.ginga.web.application.validation.ValidationItem;
import jp.co.ginga.web.domain.service.util.dto.facility.FacilityDto;

@Component
public class FacilityFormHelper {

	@Autowired
	FacilityTypeFormHelper facilityTypeFormHelper;

	@Autowired
	ValidationItem item;

	/**
	 *FacilityDtoリストをFacilityFormリストに変換
	 * @param facilityDtoList:List<FacilityDto>
	 * @return List<FacilityForm>
	 */
	public List<FacilityForm> convertFacilityFormList(List<FacilityDto> list) {
		List<FacilityForm> facilityFormList = new ArrayList<FacilityForm>();

		for (FacilityDto facilityDto : list) {
			facilityFormList.add(this.convertFacilityForm(facilityDto));
		}

		return facilityFormList;
	}

	/**
	 * FacilityDtoをFacilityFormに変換
	 * @param facilityDto
	 * @return FacilityForm
	 */
	public FacilityForm convertFacilityForm(FacilityDto dto) {
//		FacilityForm facilityForm = FacilityForm.getInstance();
//		facilityForm.setFacilityId(facilityDto.getFacilityId());
//		facilityForm.setName(facilityDto.getName());
//		facilityForm.setCapacity(String.valueOf(facilityDto.getCapacity()));
//		FacilityTypeDto dto = facilityDto.getFacilityTypeDto();
//		FacilityTypeForm form = this.facilityTypeFormHelper.convertFacilityTypeForm(dto);
//		facilityForm.setFacilityTypeForm(form);

		FacilityForm facilityForm = new FacilityForm(
				dto.getFacilityId(),
				dto.getName(),
				String.valueOf(dto.getCapacity()),
				this.facilityTypeFormHelper.convertFacilityTypeForm(dto.getFacilityTypeDto())
			);

		return facilityForm;
	}

	/**
	 *FacilityFormリストをFacilityDtoリストに変換
	 * @param facilityFormList:List<FacilityForm>
	 * @return List<FacilityDto>
	 */
	public List<FacilityDto> convertFacilityDtoList(List<FacilityForm> list) {
		List<FacilityDto> facilityDtoList = new ArrayList<FacilityDto>();

		for (FacilityForm facilityForm : list) {
			facilityDtoList.add(this.convertFacilityDto(facilityForm));
		}

		return facilityDtoList;
	}



	/**
	 * FacilityFormをFacilityDtoに変換
	 * @param facilityForm:FacilityForm
	 * @return FacilityDto
	 */
	public FacilityDto convertFacilityDto(FacilityForm form) {
		int cap= 0;
		String capacity= form.getCapacity();

		if(!item.isNull(capacity) && !item.isEmpty(capacity) && item.isInteger(capacity)) {
			cap=Integer.parseInt(capacity);
		}

		FacilityDto facilityDto = new FacilityDto(
				form.getFacilityId(),
				form.getName(),
//				Integer.parseInt(facilityForm.getCapacity()),
				cap,
				this.facilityTypeFormHelper.convertFacilityTypeDto(form.getFacilityTypeForm()),
				null
				);
		return facilityDto;
	}

}
