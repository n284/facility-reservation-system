package jp.co.ginga.web.application.controller.facilityreservation.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jp.co.ginga.web.application.util.session.facilityreservation.FacilityReservationSession;
import jp.co.ginga.web.domain.service.facilityreservation.FacilityReservationManagerDto;
import jp.co.ginga.web.domain.service.facilityreservation.FacilityReservationManagerService;

@Controller
public class FacilityReservationDetailController {
	@Autowired
	FacilityReservationSession facilityReservationSession;

	@Autowired
	FacilityReservationDetailHelper facilityReservationDetailHelper;

	@Autowired
	FacilityReservationManagerService facilityReservationManagerService;

	@GetMapping("facilityreservation/detail/{facilityReservationId}")
	public String getFacilityReservationDetail(ModelMap modelMap, @PathVariable int facilityReservationId) {
		FacilityReservationDetailForm facilityReservationDetailForm = null;
		if(modelMap.containsKey("error")) {
			facilityReservationDetailForm = this.facilityReservationDetailHelper.createFacilityReservationDetailForm(facilityReservationSession);
			modelMap.addAttribute("facilityReservationDetailHelper", facilityReservationDetailForm);
			String key = BindingResult.MODEL_KEY_PREFIX + "facilityReservationDetailForm";
			modelMap.addAttribute(key, modelMap.get("error"));
		}else {
			this.facilityReservationSession.clear();
			FacilityReservationManagerDto facilityReservationManagerDto = this.facilityReservationManagerService.getFacilityReservationDetailByFacilityReservationId(facilityReservationId);
			facilityReservationDetailForm = this.facilityReservationDetailHelper.createFacilityReservationDetailForm(facilityReservationManagerDto);
			this.facilityReservationSession.setFacilityReservationForm(facilityReservationDetailForm.getFacilityReservationForm());
			this.facilityReservationSession.setBeforeFacilityReservationForm(facilityReservationDetailForm.getFacilityReservationForm());
			this.facilityReservationSession.setOptimisticRockValue(facilityReservationManagerDto.getOptimisticRockValue());
			modelMap.addAttribute("facilityReservationDetailForm", facilityReservationDetailForm);
		}
		return "facilityreservation/facilityreservation-detail";

	}
}
