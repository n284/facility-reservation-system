package jp.co.ginga.web.application.controller.facility.list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.facility.FacilityForm;
import jp.co.ginga.web.application.util.helper.facility.FacilityFormHelper;
import jp.co.ginga.web.domain.service.facility.FacilityManagerDto;
import jp.co.ginga.web.domain.service.util.constant.ServiceConst;
import jp.co.ginga.web.domain.service.util.dto.facility.FacilityDto;

@Component
public class FacilityListHelper {
	@Autowired
	FacilityFormHelper facilityFormHelper;

	/**
	 * FacilityManagerDtoからFacilityListFormへ変換
	 * ページ上で扱えるデータに変換する
	 * Dtoにデータがない場合、formにエラーメッセージを与えて返す。
	 * @param dto:FacilityManagerDto
	 * @return FacilityListForm
	 */
	public FacilityListForm createFacilityListForm(FacilityManagerDto dto) {
		FacilityListForm form = FacilityListForm.getInstance();
		List<FacilityDto> facilityDtoList = dto.getFacilityDtoList();
		List<FacilityForm> facilityFormList = this.facilityFormHelper.convertFacilityFormList(facilityDtoList);

		form.setFacilityFormList(facilityFormList);

		if(dto.getResult() == ServiceConst.NO_DATA) {
			form.setSysMsg("0件です");
		}

		return form;
	}
}
