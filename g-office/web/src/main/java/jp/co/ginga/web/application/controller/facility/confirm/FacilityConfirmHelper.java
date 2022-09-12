package jp.co.ginga.web.application.controller.facility.confirm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.controller.facility.add.FacilityAddForm;
import jp.co.ginga.web.application.controller.facility.detail.FacilityDetailForm;
import jp.co.ginga.web.application.util.form.facility.FacilityForm;
import jp.co.ginga.web.application.util.form.facilityType.FacilityTypeForm;
import jp.co.ginga.web.application.util.helper.facility.FacilityFormHelper;
import jp.co.ginga.web.application.util.helper.facilityType.FacilityTypeFormHelper;
import jp.co.ginga.web.application.util.session.facility.FacilitySession;
import jp.co.ginga.web.domain.service.facility.FacilityManagerDto;
import jp.co.ginga.web.domain.service.util.dto.facility.FacilityDto;
import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;

@Component
public class FacilityConfirmHelper {

	public final int NO_UPDATE = 0;

	public final int NAME_UPDATE = 1;

	public final int FACILITY_TYPE_UPDATE = 10;

	public final int CAPACITY_UPDATE = 100;

	@Autowired
	FacilityFormHelper facilityFormHelper;

	@Autowired
	FacilityTypeFormHelper facilityTypeFormHelper;

	/**
	 * sessionから施設登録確認画面form生成処理
	 * @param session
	 * @return
	 */
	public FacilityConfirmForm createFacilityConfirmForm(FacilitySession facilitySession) {
		FacilityConfirmForm facilityConfirmForm = FacilityConfirmForm.getInstance();
		facilityConfirmForm.setFacilityForm(facilitySession.getFacilityForm());
		facilityConfirmForm.setFacilityTypeFormList(facilitySession.getFacilityTypeFormList());

		return facilityConfirmForm;
	}

	/**
	 * dtoから施設登録確認画面form生成処理
	 * @param facilityManagerDto
	 * @return
	 */
	public FacilityConfirmForm createFacilityConfirmForm(FacilityManagerDto facilityManagerDto) {
		FacilityDto facilityDto = facilityManagerDto.getFacilityDto();
		List<FacilityTypeDto> facilityTypeDtoList = facilityManagerDto.getFacilityTypeDtoList();
		FacilityConfirmForm facilityConfirmForm = FacilityConfirmForm.getInstance();
		if(facilityDto != null) {
			FacilityForm facilityForm = this.facilityFormHelper.convertFacilityForm(facilityDto);
			List<FacilityTypeForm> facilityTypeFormList = this.facilityTypeFormHelper.convertFacilityTypeFormList(facilityTypeDtoList);
			facilityConfirmForm.setFacilityForm(facilityForm);
			facilityConfirmForm.setFacilityTypeFormList(facilityTypeFormList);
		}

		return facilityConfirmForm;
	}

	/**
	 * 入力内容を転送
	 * @param inputForm:FacilityAddForm
	 * @return FacilityManagerDto
	 */
	public FacilityManagerDto createFacilityManagerServiceDto(FacilityAddForm inputForm) {
		FacilityForm facilityForm = inputForm.getFacilityForm();
		FacilityDto facilityDto = this.facilityFormHelper.convertFacilityDto(facilityForm);
		FacilityManagerDto facilityManagerDto = FacilityManagerDto.getInstance();
		facilityManagerDto.setFacilityDto(facilityDto);

		return facilityManagerDto;
	}

	public FacilityManagerDto getFacilityManagerServiceDto(FacilityDetailForm inputForm) {
		FacilityForm facilityForm = inputForm.getFacilityForm();
		FacilityDto facilityDto = this.facilityFormHelper.convertFacilityDto(facilityForm);
		FacilityManagerDto facilityManagerDto = FacilityManagerDto.getInstance();
		facilityManagerDto.setFacilityDto(facilityDto);

		return facilityManagerDto;
	}

	public int checkUpdated(FacilityDetailForm inputForm, FacilityForm beforeFacilityForm) {
		return this.checkUpdated(inputForm.getFacilityForm(), beforeFacilityForm);
	}

	public int checkUpdated(FacilityManagerDto facilityManagerDto, FacilityForm beforeFacilityForm) {
		return this.checkUpdated(this.facilityFormHelper.convertFacilityForm(facilityManagerDto.getFacilityDto()), beforeFacilityForm);
	}
	public int checkUpdated(FacilityForm facilityForm, FacilityForm beforFacilityForm) {
		//現在の内容
		String name = facilityForm.getName();
		int facilityTypeId = facilityForm.getFacilityTypeForm().getFacilityTypeId();
		String capacity = facilityForm.getCapacity();
		//変更前の内容
		String beforeName = beforFacilityForm.getName();
		int beforeFacilityTypeId = beforFacilityForm.getFacilityTypeForm().getFacilityTypeId();
		String beforeCapacity = beforFacilityForm.getCapacity();
		//変更状態を表す変数
		int updateState = this.NO_UPDATE;


		if(!name.equals(beforeName)) {
			updateState += this.NAME_UPDATE;
		}
		if(facilityTypeId != beforeFacilityTypeId) {
			updateState += this.FACILITY_TYPE_UPDATE;
		}
		if(!capacity.equals(beforeCapacity)) {
			updateState += this.CAPACITY_UPDATE;
		}

		return updateState;
	}
}
