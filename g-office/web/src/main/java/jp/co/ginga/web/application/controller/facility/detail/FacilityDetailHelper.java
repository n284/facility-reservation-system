package jp.co.ginga.web.application.controller.facility.detail;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.facility.FacilityForm;
import jp.co.ginga.web.application.util.form.facilityType.FacilityTypeForm;
import jp.co.ginga.web.application.util.helper.facility.FacilityFormHelper;
import jp.co.ginga.web.application.util.helper.facilityType.FacilityTypeFormHelper;
import jp.co.ginga.web.application.util.session.facility.FacilitySession;
import jp.co.ginga.web.domain.service.facility.FacilityManagerDto;
import jp.co.ginga.web.domain.service.facilityType.FacilityTypeManagerDto;
import jp.co.ginga.web.domain.service.util.dto.facility.FacilityDto;
import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;

@Component
public class FacilityDetailHelper {
	@Autowired
	FacilityFormHelper facilityFormHelper;

	@Autowired
	FacilityTypeFormHelper facilityTypeFormHelper;

	/**
	 * FacilitySessionからFacilityDetailFormを作成
	 * @param facilitySession:FacilitySession
	 * @return　FacilityDetailForm
	 */
	public FacilityDetailForm createFacilityDetailForm(FacilitySession facilitySession) {
		FacilityDetailForm facilityDetailForm = FacilityDetailForm.getInstance();

		facilityDetailForm.setFacilityForm(facilitySession.getFacilityForm());
		facilityDetailForm.setFacilityTypeFormList(facilitySession.getFacilityTypeFormList());

		return facilityDetailForm;
	}

	/**
	 * FacilityTypeManagerDtoからFacilityDetailFormを作成
	 * @param facilityTypeManagerDto:FacilityTypeManagerDtoFacilitySession
	 * @return　FacilityDetailForm
	 */
	public FacilityDetailForm createFacilityDetailForm(FacilityTypeManagerDto facilityTypeManagerDto) {
		List<FacilityTypeDto> facilityTypeDtoList = facilityTypeManagerDto.getFacilityTypeDtoList();
		List<FacilityTypeForm> facilityTypeFormList = new ArrayList<FacilityTypeForm>();

		for(FacilityTypeDto facilityTypeDto : facilityTypeDtoList) {
			FacilityTypeForm facilityTypeForm = FacilityTypeForm.getInstance();
			facilityTypeForm.setFacilityTypeId(facilityTypeDto.getFacilityTypeId());
			facilityTypeForm.setName(facilityTypeDto.getName());
			facilityTypeFormList.add(facilityTypeForm);
		}
		FacilityDetailForm facilityDetailForm = FacilityDetailForm.getInstance();
		facilityDetailForm.getFacilityForm().getFacilityTypeForm().setFacilityTypeId(facilityTypeFormList.get(0).getFacilityTypeId());
		facilityDetailForm.setFacilityTypeFormList(facilityTypeFormList);

		return facilityDetailForm;
	}

	/**
	 * FacilityManagerDtoからFacilityDetailFormを作成
	 * @param facilityManagerDto:FacilityManagerDto
	 * @return FacilityDetailForm
	 */
	public FacilityDetailForm createFacilityDetailForm(FacilityManagerDto facilityManagerDto) {
		FacilityDto facilityDto = facilityManagerDto.getFacilityDto();
		List<FacilityTypeDto> facilityTypeDtoList = facilityManagerDto.getFacilityTypeDtoList();
		FacilityDetailForm facilityDetailForm = FacilityDetailForm.getInstance();
		FacilityForm facilityForm = this.facilityFormHelper.convertFacilityForm(facilityDto);
		List<FacilityTypeForm> facilityTypeFormList = this.facilityTypeFormHelper.convertFacilityTypeFormList(facilityTypeDtoList);

		facilityDetailForm.setFacilityForm(facilityForm);
		facilityDetailForm.setFacilityTypeFormList(facilityTypeFormList);

		return facilityDetailForm;
	}

}
