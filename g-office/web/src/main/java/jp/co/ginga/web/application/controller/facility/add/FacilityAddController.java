package jp.co.ginga.web.application.controller.facility.add;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.ginga.web.application.util.session.facility.FacilitySession;
import jp.co.ginga.web.domain.service.facilityType.FacilityTypeManagerDto;
import jp.co.ginga.web.domain.service.facilityType.FacilityTypeManagerService;

@Controller
public class FacilityAddController {
	/*
	 * ヘルパー
	 */
	@Autowired
	FacilityAddHelper facilityAddHelper;

	/*
	 * サービス
	 */
	@Autowired
	FacilityTypeManagerService facilityTypeManagerService;

	/*
	 * セッション
	 */
	@Autowired
	FacilitySession facilitySession;

	@GetMapping("/facility/add")
	public String createFacilityAdd(ModelMap mdMap) {
		FacilityAddForm facilityAddForm = null;

		if(mdMap.containsKey("errors")) {
			facilityAddForm = this.facilityAddHelper.createFacilityAddForm(this.facilitySession);
			mdMap.addAttribute("facilityAddForm", facilityAddForm);
			String key = BindingResult.MODEL_KEY_PREFIX + "facilityAddForm";
			mdMap.addAttribute(key, mdMap.get("errors"));
		}else {
			this.facilitySession.clear();
			FacilityTypeManagerDto facilityTypeManagerDto = this.facilityTypeManagerService.getFacilityTypeList();
			facilityAddForm = this.facilityAddHelper.createFacilityAddForm(facilityTypeManagerDto);
			this.facilitySession.setFacilityTypeFormList(facilityAddForm.getFacilityTypeFormList());
			mdMap.addAttribute("facilityAddForm", facilityAddForm);
		}

		return "/facility/facility-add";

	}
}
