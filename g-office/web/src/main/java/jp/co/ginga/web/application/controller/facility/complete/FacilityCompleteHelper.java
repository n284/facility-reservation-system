package jp.co.ginga.web.application.controller.facility.complete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.helper.facility.FacilityFormHelper;
import jp.co.ginga.web.application.util.helper.facilityType.FacilityTypeFormHelper;
import jp.co.ginga.web.application.util.session.facility.FacilitySession;
import jp.co.ginga.web.domain.service.facility.FacilityManagerDto;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;

@Component
public class FacilityCompleteHelper {
	@Autowired
	FacilityFormHelper facilityFormHelper;

	@Autowired
	FacilityTypeFormHelper facilityTypeFormHelper;

	/**
	 * FacilitySessionをFacilityManagerDtoに変換処理
	 * @param facilitySession
	 * @return
	 */
	public FacilityManagerDto convertFacilityManagerDto(FacilitySession facilitySession) {
		FacilityManagerDto facilityManagerDto = FacilityManagerDto.getInstance();

		if(facilitySession.getFacilityForm()  != null) {
			facilityManagerDto.setFacilityDto(this.facilityFormHelper.convertFacilityDto(facilitySession.getFacilityForm()));
		}

		if(facilitySession.getOptimisticRockValue() != null	) {
			facilityManagerDto.setOptimisticRockValue(facilitySession.getOptimisticRockValue());
		}

		facilityManagerDto.getFacilityDto().setUserDto(UserDto.getInstance());

		return facilityManagerDto;
	}

	public FacilityCompleteForm createFacilityCompleteForm(FacilityManagerDto facilityManagerDto) {
		FacilityCompleteForm facilityCompleteForm = FacilityCompleteForm.getInstance();

//		if(facilityManagerDto.getResult() ==ServiceConst.THERE_IS_DATA || facilityManagerDto.getResult() ==ServiceConst.NO_DATA) {
//			facilityCompleteForm.setSysMsg(null);
//		}
		return facilityCompleteForm;
	}

}
