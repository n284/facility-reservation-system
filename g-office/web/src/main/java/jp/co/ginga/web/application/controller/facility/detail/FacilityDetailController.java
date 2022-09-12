package jp.co.ginga.web.application.controller.facility.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jp.co.ginga.web.application.util.session.facility.FacilitySession;
import jp.co.ginga.web.domain.service.facility.FacilityManagerDto;
import jp.co.ginga.web.domain.service.facility.FacilityManagerService;
import jp.co.ginga.web.domain.service.facilityType.FacilityTypeManagerService;

@Controller
public class FacilityDetailController {

	/**
	 * 施設詳細ヘルパー
	 */
	@Autowired
	FacilityDetailHelper facilityDetailHelper;

	@Autowired
	FacilityManagerService facilityManagerService;

	@Autowired
	FacilityTypeManagerService facilityTypeManagerService;

	@Autowired
	FacilitySession facilitySession;

	@GetMapping(path="facility/detail/{facilityId}")
	public String getFacilityDetail(ModelMap modelMap, @PathVariable int facilityId) {
		FacilityDetailForm facilityDetailForm = null;
		if(modelMap.containsKey("errors")) {
			facilityDetailForm = this.facilityDetailHelper.createFacilityDetailForm(facilitySession);
			modelMap.addAttribute("facilityDetailForm", facilityDetailForm);
			String key = BindingResult.MODEL_KEY_PREFIX + "facilityDetailForm";
			modelMap.addAttribute(key, modelMap.get("errors"));
		}else {
			this.facilitySession.clear();
			FacilityManagerDto facilityManagerDto = this.facilityManagerService.getFacilityDetailByFacilityId(facilityId);
			facilityDetailForm = this.facilityDetailHelper.createFacilityDetailForm(facilityManagerDto);
			this.facilitySession.setFacilityForm(facilityDetailForm.getFacilityForm());
			this.facilitySession.setFacilityTypeFormList(facilityDetailForm.getFacilityTypeFormList());
			this.facilitySession.setBeforeFacilityForm(facilityDetailForm.getFacilityForm());
			this.facilitySession.setOptimisticRockValue(facilityManagerDto.getOptimisticRockValue()	);
			modelMap.addAttribute("facilityDetailForm", facilityDetailForm);
		}
		return "facility/facility-detail";
	}

}
