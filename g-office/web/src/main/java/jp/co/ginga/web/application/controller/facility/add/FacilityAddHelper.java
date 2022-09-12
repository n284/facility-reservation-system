package jp.co.ginga.web.application.controller.facility.add;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.facilityType.FacilityTypeForm;
import jp.co.ginga.web.application.util.helper.facility.FacilityFormHelper;
import jp.co.ginga.web.application.util.session.facility.FacilitySession;
import jp.co.ginga.web.domain.service.facilityType.FacilityTypeManagerDto;
import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;

@Component
public class FacilityAddHelper {

	/*
	 * ヘルパー
	 */
	@Autowired
	FacilityFormHelper facilityFormHelper;

	/**
	 * FacilityTypeManagerDtoからFacilityAddFormを生成処理
	 * @param dto:FacilityTypeManagerDto
	 * @return FacilityAddForm
	 */
	public FacilityAddForm createFacilityAddForm(FacilityTypeManagerDto dto) {
		List<FacilityTypeDto> facilityTypeDtoList = dto.getFacilityTypeDtoList();
		List<FacilityTypeForm> facilityTypeFormList = new ArrayList<FacilityTypeForm>();

		for(FacilityTypeDto facilityTypeDto : facilityTypeDtoList) {
			FacilityTypeForm facilityTypeForm = FacilityTypeForm.getInstance();
			facilityTypeForm.setFacilityTypeId(facilityTypeDto.getFacilityTypeId());
			facilityTypeForm.setName(facilityTypeDto.getName());

			facilityTypeFormList.add(facilityTypeForm);
		}

		FacilityAddForm facilityAddForm = FacilityAddForm.getInstance();

		facilityAddForm.getFacilityForm().getFacilityTypeForm().setFacilityTypeId(facilityTypeFormList.get(0).getFacilityTypeId());
		facilityAddForm.setFacilityTypeFormList(facilityTypeFormList);

		return facilityAddForm;
	}

	/**
	 * FacilitySessionのFacilityFormをFacilityAddFormのFacilityFormに代入し返す
	 * @param dto:FacilityTypeManagerDto
	 * @return FacilityAddForm
	 */
	public FacilityAddForm createFacilityAddForm(FacilitySession session) {
		FacilityAddForm facilityAddForm = FacilityAddForm.getInstance();
		facilityAddForm.setFacilityForm(session.getFacilityForm());
		facilityAddForm.setFacilityTypeFormList(session.getFacilityTypeFormList());

		return facilityAddForm;
	}

}
