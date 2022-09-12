package jp.co.ginga.web.application.controller.facilityreservation.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jp.co.ginga.web.application.util.session.facilityreservation.FacilityReservationSession;
import jp.co.ginga.web.domain.service.facilityreservation.FacilityReservationManagerDto;
import jp.co.ginga.web.domain.service.facilityreservation.FacilityReservationManagerService;

@Controller
public class FacilityReservationListController {
	@Autowired
	FacilityReservationManagerService facilityReservationManagerService;

	@Autowired
	FacilityReservationListHelper facilityReservationListHelper;

	@Autowired
	FacilityReservationSession facilityReservationSession;


	@GetMapping("facilityreservation/list")
	public String getFacilityReservationList(ModelMap modelMap) {
		this.facilityReservationSession.clear();
		FacilityReservationManagerDto facilityReservationManagerDto = this.facilityReservationManagerService.getFacility();
		FacilityReservationListForm facilityReservationListForm = this.facilityReservationListHelper.createFacilityReservationListForm(facilityReservationManagerDto);

		modelMap.addAttribute("facilityReservationListForm", facilityReservationListForm);

		return "facilityreservation/facilityreservation-list";
	}

	@GetMapping("facilityreservation/remake/{facilityTypeId}")
	public String updateFacilityReservationList(ModelMap modelMap, @PathVariable int facilityTypeId) {
		FacilityReservationManagerDto facilityReservationManagerDto;

		if(facilityTypeId >0) {
			facilityReservationManagerDto = this.facilityReservationManagerService.getFacilityByFacilityTypeId(facilityTypeId);
		}else{
			facilityReservationManagerDto = this.facilityReservationManagerService.getFacility();
		}

		FacilityReservationListForm facilityReservationListForm = this.facilityReservationListHelper.createFacilityReservationListForm(facilityReservationManagerDto);
		modelMap.addAttribute("facilityReservationListForm", facilityReservationListForm);

		return "facilityreservation/facilityreservation-list";
	}

// JSON適用
//	@ResponseBody
//	@GetMapping("facilityreservation/remake/{facilityTypeId}")
//	public FacilityReservationListForm updateFacilityReservationList(ModelMap modelMap, @PathVariable int facilityTypeId) {
//		FacilityReservationManagerDto facilityReservationManagerDto;
//
//		if(facilityTypeId >0) {
//			facilityReservationManagerDto = this.facilityReservationManagerService.getFacilityByFacilityTypeId(facilityTypeId);
//		}else{
//			facilityReservationManagerDto = this.facilityReservationManagerService.getFacility();
//		}
//
//		FacilityReservationListForm facilityReservationListForm = this.facilityReservationListHelper.createFacilityReservationListForm(facilityReservationManagerDto);
//		modelMap.addAttribute("facilityReservationListForm", facilityReservationListForm);
//
//		return facilityReservationListForm;
//	}
}
